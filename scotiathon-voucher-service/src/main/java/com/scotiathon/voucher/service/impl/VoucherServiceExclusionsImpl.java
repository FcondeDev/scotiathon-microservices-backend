package com.scotiathon.voucher.service.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.scotiathon.voucher.dto.ExclusionsDTO;
import com.scotiathon.voucher.dto.VoucherResponseDTO;
import com.scotiathon.voucher.error.ErrorEnum;
import com.scotiathon.voucher.exception.CustomException;
import com.scotiathon.voucher.repository.SuccessfulVoucherRepository;
import com.scotiathon.voucher.service.VoucherService;

import lombok.extern.java.Log;

@Log
@Service("VoucherServiceExclusions")
public class VoucherServiceExclusionsImpl implements VoucherService {

	@Autowired
	private SuccessfulVoucherRepository successfulVoucherRepository;

	private static final String EXCLUSIONS_MARK = "Canada_Accountable_EX";

	@Override
	@Transactional
	public VoucherResponseDTO storeAndValidateVouchers(MultipartFile exclusions, Long clientId) {

		if (!Optional.ofNullable(exclusions).isPresent())
			throw new CustomException(ErrorEnum.VOUCHERS_FILE_NOT_FOUND, HttpStatus.NOT_FOUND);

		log.info(String.format("-----Starting excluding vourchers for client id : %s----------", clientId));
		List<ExclusionsDTO> listOfExclusions = convertCSVToList(exclusions);
		List<String> buPlusVoucherId = new ArrayList<>();

		for (ExclusionsDTO exclusionsDTO : listOfExclusions) {
			markExclusionsAndValidateExclusionsFile(exclusionsDTO, buPlusVoucherId);
		}

		successfulVoucherRepository.updateExclusions(EXCLUSIONS_MARK, buPlusVoucherId);
		log.info("-----finishing excluding vourchers----------");
		return new VoucherResponseDTO(true);
	}

	private void markExclusionsAndValidateExclusionsFile(ExclusionsDTO exclusionsDTO, List<String> buPlusVoucherId) {
		buPlusVoucherId.add(exclusionsDTO.getBu() + exclusionsDTO.getVoucherId());

	}

	private List<ExclusionsDTO> convertCSVToList(MultipartFile exclusions) {
		try {

			HeaderColumnNameTranslateMappingStrategy<ExclusionsDTO> strategy = new HeaderColumnNameTranslateMappingStrategy<>();
			strategy.setType(ExclusionsDTO.class);
			strategy.setColumnMapping(mappingForCSV());

			Reader reader = new InputStreamReader(exclusions.getInputStream());
			CSVReader csvReader = new CSVReader(reader);
			CsvToBean<ExclusionsDTO> csv = new CsvToBean<>();
			csv.setCsvReader(csvReader);
			csv.setMappingStrategy(strategy);
			return csv.parse();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error while processing csv");
			throw new CustomException(ErrorEnum.ERROR_WHILE_VALIDATING_EXCEL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Map<String, String> mappingForCSV() {
		Map<String, String> mapping = new HashMap<>();
		mapping.put("Unit", "bu");
		mapping.put("Voucher", "voucherId");
		mapping.put("Invoice", "invoice");
		mapping.put("Date", "date");
		mapping.put("Supplier", "supplierId");
		mapping.put("Gross Amt", "grossAmt");
		mapping.put("GL Unit", "glUnit");
		mapping.put("Entered", "entered");
		mapping.put("Status", "status");
		mapping.put("Transit", "transit");
		return mapping;
	}

}

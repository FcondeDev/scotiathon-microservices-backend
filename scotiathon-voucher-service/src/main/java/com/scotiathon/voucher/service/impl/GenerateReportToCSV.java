package com.scotiathon.voucher.service.impl;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;

import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.scotiathon.voucher.entity.FailedVoucher;
import com.scotiathon.voucher.service.GenerateReport;

import lombok.extern.java.Log;

@Log
@Service
public class GenerateReportToCSV implements GenerateReport {

	@Override
	public void generateFailureReport(List<FailedVoucher> failedVouchers) {
		try (Writer writer = Files
				.newBufferedWriter(Paths.get("C:\\Users\\s5268554\\Desktop\\Failure_report\\report.csv"))) {
			ColumnPositionMappingStrategy<FailedVoucher> mappingStrategy = new ColumnPositionMappingStrategy<>();
			mappingStrategy.setType(FailedVoucher.class);
			StatefulBeanToCsvBuilder<FailedVoucher> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<FailedVoucher> beanToCsv = builder.build();
			beanToCsv.write(failedVouchers);
			log.info("Report for failed vouchers has been generated.");
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error while generating failure report", e);
		}

	}

}

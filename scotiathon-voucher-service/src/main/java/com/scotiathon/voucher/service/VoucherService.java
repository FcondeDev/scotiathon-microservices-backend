package com.scotiathon.voucher.service;

import org.springframework.web.multipart.MultipartFile;

import com.scotiathon.voucher.dto.VoucherResponseDTO;

public interface VoucherService {

	public VoucherResponseDTO storeAndValidateVouchers(MultipartFile file, Long clientId);

}

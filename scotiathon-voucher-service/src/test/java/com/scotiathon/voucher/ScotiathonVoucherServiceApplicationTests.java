package com.scotiathon.voucher;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import com.scotiathon.voucher.repository.FailedVoucherRepository;
import com.scotiathon.voucher.repository.SuccessfulVoucherRepository;
import com.scotiathon.voucher.service.VoucherService;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@ActiveProfiles("test")
class ScotiathonVoucherServiceApplicationTests {

	@Autowired
	@Qualifier("VoucherServiceLoadAndInitialValidation")
	private VoucherService voucherServiceLoadAndInitialValidation;

	@Autowired
	private SuccessfulVoucherRepository successfulVoucherRepository;

	@Autowired
	private FailedVoucherRepository failedVoucherRepository;

	@Test
	void validatePeopleSoftInsertionProcess() throws Exception {
		MockMultipartFile peopleSoft = new MockMultipartFile("file", "file", null,
				Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("Peoplesofttest.csv").toURI())));

		voucherServiceLoadAndInitialValidation.storeAndValidateVouchers(peopleSoft, 1L);

		Thread.sleep(20000);

		assertTrue(successfulVoucherRepository.findAll().size() > 0);
		assertTrue(failedVoucherRepository.findAll().size() > 0);

	}
}

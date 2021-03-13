package com.scotiathon.voucher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scotiathon.voucher.dto.AssignmentDTO;
import com.scotiathon.voucher.dto.EscalatedToDTO;
import com.scotiathon.voucher.dto.JsonDTO;
import com.scotiathon.voucher.dto.NotificationsDTO;
import com.scotiathon.voucher.dto.PassedVoucherDTO;
import com.scotiathon.voucher.dto.ReasonEscalationDTO;
import com.scotiathon.voucher.dto.StatusDTO;
import com.scotiathon.voucher.dto.VoucherResponseDTO;
import com.scotiathon.voucher.service.VoucherAssignmentAndClerkProcessing;
import com.scotiathon.voucher.service.VoucherService;

import io.swagger.annotations.ApiOperation;

@RestController
public class VoucherController {

	@Autowired
	@Qualifier("VoucherServiceLoadAndInitialValidation")
	private VoucherService voucherServiceLoadAndInitialValidation;

	@Autowired
	@Qualifier("VoucherServiceExclusions")
	private VoucherService voucherServiceExclusions;

	@Autowired
	private VoucherAssignmentAndClerkProcessing voucherAssignmentAndClerkProcessing;

	@ApiOperation(value = "Upload People soft file to be processed")
	@PostMapping("vouchers")
	public ResponseEntity<JsonDTO<VoucherResponseDTO>> store(@RequestParam MultipartFile file,
			@RequestParam Long clientId) {
		return new ResponseEntity<>(
				new JsonDTO<>(voucherServiceLoadAndInitialValidation.storeAndValidateVouchers(file, clientId)),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Upload exclusions file to be processed")
	@PostMapping("vouchers/exclusions")
	public ResponseEntity<JsonDTO<VoucherResponseDTO>> storeExclusions(@RequestParam MultipartFile file,
			@RequestParam Long clientId) {
		return new ResponseEntity<>(new JsonDTO<>(voucherServiceExclusions.storeAndValidateVouchers(file, clientId)),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Get all the passed vouchers after validations and exclusions")
	@GetMapping("vouchers")
	public ResponseEntity<JsonDTO<List<PassedVoucherDTO>>> index() {
		return new ResponseEntity<>(new JsonDTO<>(voucherAssignmentAndClerkProcessing.getPassedVouchers()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Assign voucher to an clerk")
	@PostMapping("vouchers/assignments")
	public ResponseEntity<JsonDTO<VoucherResponseDTO>> store(@RequestBody AssignmentDTO assignmentDTO) {
		return new ResponseEntity<>(
				new JsonDTO<>(voucherAssignmentAndClerkProcessing
						.voucherAssigment(assignmentDTO.getVouchersToBeAssigned(), assignmentDTO.getClientId())),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Get assigned voucher by clerk")
	@GetMapping("vouchers/clerk/assignments/{clientId}")
	public ResponseEntity<JsonDTO<List<PassedVoucherDTO>>> getVouchersByClerk(@PathVariable Long clientId) {
		return new ResponseEntity<>(
				new JsonDTO<>(voucherAssignmentAndClerkProcessing.getPassedVouchersByclerk(clientId)), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all the status")
	@GetMapping("vouchers/status")
	public ResponseEntity<JsonDTO<List<StatusDTO>>> getStatuses() {
		return new ResponseEntity<>(new JsonDTO<>(voucherAssignmentAndClerkProcessing.getVoucherStatuses()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Get all the reasons for escalations")
	@GetMapping("vouchers/reasons")
	public ResponseEntity<JsonDTO<List<ReasonEscalationDTO>>> getReasonsForEscalation() {
		return new ResponseEntity<>(new JsonDTO<>(voucherAssignmentAndClerkProcessing.getVoucherReasonsEscalation()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Get all the the escaled to")
	@GetMapping("vouchers/escalations")
	public ResponseEntity<JsonDTO<List<EscalatedToDTO>>> getEscalations() {
		return new ResponseEntity<>(new JsonDTO<>(voucherAssignmentAndClerkProcessing.getVoucherEscaledTo()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Edit a voucher by a clerk")
	@PutMapping("vouchers")
	public ResponseEntity<JsonDTO<PassedVoucherDTO>> edit(@RequestBody PassedVoucherDTO passedVoucherDTO,
			@RequestParam("clerkId") Long clerkId) {
		return new ResponseEntity<>(
				new JsonDTO<>(voucherAssignmentAndClerkProcessing.editVoucherInformation(passedVoucherDTO, clerkId)),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Set start time for a voucher")
	@PutMapping("vouchers/startTime")
	public ResponseEntity<JsonDTO<PassedVoucherDTO>> setStartTime(@RequestBody PassedVoucherDTO passedVoucherDTO,
			@RequestParam("clerkId") Long clerkId) {
		return new ResponseEntity<>(
				new JsonDTO<>(voucherAssignmentAndClerkProcessing.setStartTime(passedVoucherDTO, clerkId)),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Get all the notifications by client")
	@GetMapping("vouchers/notifications")
	public ResponseEntity<JsonDTO<List<NotificationsDTO>>> getNotifications(@RequestParam Long clientId) {
		return new ResponseEntity<>(
				new JsonDTO<>(voucherAssignmentAndClerkProcessing.getNotificationsByClient(clientId)), HttpStatus.OK);
	}
}

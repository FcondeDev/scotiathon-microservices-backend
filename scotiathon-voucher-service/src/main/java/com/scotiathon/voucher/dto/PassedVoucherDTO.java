package com.scotiathon.voucher.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassedVoucherDTO {

	private Long id;
	private String bu;
	private String voucherId;
	private Integer linesEntered;
	private String voucherSource;
	private String voucherCreatedBy;
	private String voucherCreatedOn;
	private String postStatus;
	private String entryStatus;
	private Integer supplierId;
	private String shortSupplierName;
	private String supplierName;
	private String invoiceNumber;
	private Date invoiceDate;
	private String invoiceReceived;
	private Float voucherAmount;
	private String voucherCurrency;
	private String approvalStatus;
	private Integer age;
	@JsonFormat(timezone = "America/Bogota")
	private Timestamp createdAt;
	@JsonFormat(timezone = "America/Bogota")
	private Timestamp startedAt;
	@JsonFormat(timezone = "America/Bogota")
	private Timestamp finishedAt;
	private String priorityMarkVa;
	private String priorityMarkRe;
	private String vpacLocation;
	private String statusClerk;
	@JsonFormat(timezone = "America/Bogota")
	private Timestamp assignedOn;
	private ReasonEscalationDTO reasonEscalation;
	private EscalatedToDTO escalatedTo;
	private ClientDTO client;

	public PassedVoucherDTO(Long id) {
		this.id = id;
	}

}

package com.scotiathon.voucher.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Table(name = "failed_vouchers")
@Entity
public class FailedVoucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bu;
	@Column(name = "voucher_id")
	private String voucherId;
	@Column(name = "lines_entered")
	private String linesEntered;
	@Column(name = "voucher_source")
	private String voucherSource;
	@Column(name = "voucher_created_by")
	private String voucherCreatedBy;
	@Column(name = "voucher_created_on")
	private String voucherCreatedOn;
	@Column(name = "post_status")
	private String postStatus;
	@Column(name = "entry_status")
	private String entryStatus;
	@Column(name = "supplier_id")
	private String supplierId;
	@Column(name = "short_supplier_name")
	private String shortSupplierName;
	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "invoice_number")
	private String invoiceNumber;
	@Column(name = "invoice_date")
	private String invoiceDate;
	@Column(name = "invoice_received")
	private String invoiceReceived;
	@Column(name = "voucher_amount")
	private String voucherAmount;
	@Column(name = "voucher_currency")
	private String voucherCurrency;
	@Column(name = "approval_status")
	private String approvalStatus;
	private String age;
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "failure_reason")
	private String failureReason;

}

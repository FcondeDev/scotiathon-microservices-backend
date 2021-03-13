package com.scotiathon.voucher.error;

public enum ErrorEnum {

	NOT_FOUND_EXCEPTION(1, "NOT FOUND", "The resource could not be found"),
	INVALID_PARAMETER(2, "INVALID PARAMETER", "Some of the input parameters are invalid"),
	DEFAULT_EXCEPTION(3, "UPSS", "Something happened, try later"),
	PARAMETER_REQUIRED_NOT_FOUND(4, "REQUIRED PARAMETER NOT FOUND", "Input data does not contains a mandatory field"),
	VOUCHERS_FILE_NOT_FOUND(5, "VOUCHERS FILE NOT FOUND", "Vourcher file is a mandatory field"),
	ERROR_WHILE_VALIDATING_EXCEL(6, "ERROR PROCESSING THE FILE", "The file is not a csv or is corrupted"),
	ERROR_WHILE_CONVERTING_DATE(7, "STRING COULD NOT BE CONVERTED TO DATE", "The String is a wrong date"),
	INFORMATION_DOES_NOT_MATCH(8, "FORBIDDEN", "INFORMATION DOES NOT MATCH");

	public final int code;
	public final String title;
	public final String description;

	ErrorEnum(int code, String title, String description) {
		this.code = code;
		this.title = title;
		this.description = description;
	}

}

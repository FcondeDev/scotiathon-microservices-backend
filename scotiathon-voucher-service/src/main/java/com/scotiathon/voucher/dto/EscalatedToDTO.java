package com.scotiathon.voucher.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EscalatedToDTO {

	private Long id;
	private String description;

	public EscalatedToDTO(Long id) {
		this.id = id;
	}

}

package com.scotiathon.voucher.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "reason_escalation")
@Entity
@NoArgsConstructor
public class ReasonEscalation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	public ReasonEscalation(Long id) {
		this.id = id;
	}

}

package com.springboot.fixedTermAccountVip.dto;

import java.util.List;

import lombok.Data;

@Data
public class FixedTermAccountVipDto {

	private String numberAccount;
	private Double tea;
	private String state;
	private Double balance;
	private List<PersonalVipDto> headlines;
	
}

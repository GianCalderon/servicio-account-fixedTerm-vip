package com.springboot.fixedTermAccountVip.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.fixedTermAccountVip.document.FixedTermAccountVip;
import com.springboot.fixedTermAccountVip.dto.AccountDto;
import com.springboot.fixedTermAccountVip.dto.FixedTermAccountVipDto;

@Component
public class UtilConvert {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public FixedTermAccountVip convertFixedTermAccountVip(FixedTermAccountVipDto FixedTermAccountVipDto) {

		 LOGGER.info("Antes del Convertidor -----> "+FixedTermAccountVipDto.toString());
		 
		FixedTermAccountVip FixedTermAccountVip = new FixedTermAccountVip();

		FixedTermAccountVip.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		FixedTermAccountVip.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		FixedTermAccountVip.setState(FixedTermAccountVipDto.getState());
		FixedTermAccountVip.setBalance(FixedTermAccountVipDto.getBalance());
		FixedTermAccountVip.setTea(FixedTermAccountVipDto.getTea());
		FixedTermAccountVip.setCreateDate(new Date());
		FixedTermAccountVip.setUpdateDate(new Date());
		FixedTermAccountVip.setIdOperation(new ArrayList<String>());
		
		 LOGGER.info("Antes del Convertidor -----> "+FixedTermAccountVipDto.toString());
		 
		return FixedTermAccountVip;

	}
	
	
	public FixedTermAccountVip convertAccountDto(AccountDto accountDto) {
		
		 LOGGER.info("Antes del Convertidor -----> "+accountDto.toString());

		FixedTermAccountVip  FixedTermAccountVip = new FixedTermAccountVip();

		FixedTermAccountVip.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		FixedTermAccountVip.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		FixedTermAccountVip.setState(accountDto.getState());
		FixedTermAccountVip.setBalance(accountDto.getBalance());
		FixedTermAccountVip.setTea(accountDto.getTea());
		FixedTermAccountVip.setCreateDate(new Date());
		FixedTermAccountVip.setUpdateDate(new Date());
		FixedTermAccountVip.setIdOperation(new ArrayList<String>());

		 LOGGER.info("Despues del Convertidor -----> "+accountDto.toString());
		
		return FixedTermAccountVip;

	}

}

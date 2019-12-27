package com.springboot.fixedTermAccountVip.service;

import com.springboot.fixedTermAccountVip.document.FixedTermAccountVip;
import com.springboot.fixedTermAccountVip.dto.AccountDto;
import com.springboot.fixedTermAccountVip.dto.FixedTermAccountVipDto;
import com.springboot.fixedTermAccountVip.dto.OperationDto;
import com.springboot.fixedTermAccountVip.dto.PersonalVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountVipInterface {
	

	public Flux<FixedTermAccountVip> findAll();
	public Mono<FixedTermAccountVip> findById(String id);
	public Mono<FixedTermAccountVip> save(FixedTermAccountVip FixedTermAccountVip);
	public Mono<FixedTermAccountVip> update(FixedTermAccountVip FixedTermAccountVip ,String id);
	public Mono<Void> delete(FixedTermAccountVip FixedTermAccountVip);

	
	public Mono<FixedTermAccountVip> findByNumAccount(String numAccount);
    public Mono<FixedTermAccountVip> saveOperation(OperationDto operationDto);
	public Mono<PersonalVipDto> saveHeadline(AccountDto accountDto);     
	public Mono<FixedTermAccountVipDto> saveHeadlines (FixedTermAccountVipDto FixedTermAccountVipDto);
	

}

package com.springboot.fixedTermAccountVip.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fixedTermAccountVip.client.PersonalVipClient;
import com.springboot.fixedTermAccountVip.document.FixedTermAccountVip;
import com.springboot.fixedTermAccountVip.dto.AccountClient;
import com.springboot.fixedTermAccountVip.dto.AccountDto;
import com.springboot.fixedTermAccountVip.dto.FixedTermAccountVipDto;
import com.springboot.fixedTermAccountVip.dto.OperationDto;
import com.springboot.fixedTermAccountVip.dto.PersonalVipDto;
import com.springboot.fixedTermAccountVip.repo.FixedTermAccountVipRepo;
import com.springboot.fixedTermAccountVip.util.CodAccount;
import com.springboot.fixedTermAccountVip.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedTermAccountVipImpl implements FixedTermAccountVipInterface {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(FixedTermAccountVipImpl.class);
	
	@Autowired
	FixedTermAccountVipRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	PersonalVipClient client;
	
	
	@Override
	public Flux<FixedTermAccountVip> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Mono<FixedTermAccountVip> findById(String id) {
	
		return repo.findById(id);
	}

	@Override
	public Mono<FixedTermAccountVip> save(FixedTermAccountVip FixedTermAccountVip) {
		// TODO Auto-generated method stub
		return repo.save(FixedTermAccountVip);
	}

	@Override
	public Mono<FixedTermAccountVip> update(FixedTermAccountVip FixedTermAccountVip, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(FixedTermAccountVip.getNameAccount());
			s.setNumberAccount(FixedTermAccountVip.getNumberAccount());
			s.setBalance(FixedTermAccountVip.getBalance());
			s.setState(FixedTermAccountVip.getState());
			s.setTea(FixedTermAccountVip.getTea());
			s.setUpdateDate(FixedTermAccountVip.getUpdateDate());
			s.setCreateDate(FixedTermAccountVip.getCreateDate());
			s.setIdOperation(FixedTermAccountVip.getIdOperation());
			
			return repo.save(s);
			});
	}

	@Override
	public Mono<Void> delete(FixedTermAccountVip FixedTermAccountVip) {
		return repo.delete(FixedTermAccountVip);
	}
	

	@Override
	public Mono<FixedTermAccountVip> findByNumAccount(String numAccount) {
		return repo.findByNumberAccount(numAccount);
	}


	@Override
	public Mono<PersonalVipDto> saveHeadline(AccountDto accountDto) {
		
		return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
			
			int cont = 0;

		     for (int i = 0; i < cuentas.size(); i++) {

					AccountClient obj = cuentas.get(i);

					LOGGER.info("PRUEBA 3 --->" + accountDto.toString());

				    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;

				}
		     
				if (cont == 0) {

					return repo.save(convert.convertAccountDto(accountDto)).flatMap(cuenta -> {

						return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {

							LOGGER.info("Flujo Inicial ---->: " + titular.toString());

							titular.setIdAccount(cuenta.getId());
							titular.setNameAccount(cuenta.getNameAccount());
							titular.setNumberAccount(cuenta.getNumberAccount());

							LOGGER.info("Flujo Final ----->: " + titular.toString());

							return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
								
								p.setIdAccount(cuenta.getId());
								return Mono.just(p);
							});

						});

					});

				} else {

					return Mono.empty();
				}

			});
	}

	@Override
	public Mono<FixedTermAccountVipDto> saveHeadlines(FixedTermAccountVipDto FixedTermAccountVipDto) {
		return save(convert.convertFixedTermAccountVip(FixedTermAccountVipDto)).flatMap(cuenta -> {

			FixedTermAccountVipDto.getHeadlines().forEach(titular -> {

				titular.setIdAccount(cuenta.getId());
				titular.setNameAccount(cuenta.getNameAccount());
				titular.setNumberAccount(cuenta.getNumberAccount());

				client.save(titular);

			});

			return Mono.just(FixedTermAccountVipDto);
		});
	}
	
	@Override
	public Mono<FixedTermAccountVip> saveOperation(OperationDto operationDto) {
		
		return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{

			if(operationDto.getTipoMovement().equals("debito")) {
				
				p.setBalance(p.getBalance()-operationDto.getAmount());
				return repo.save(p);
				
			}else if(operationDto.getTipoMovement().equals("abono")) {
				
				p.setBalance(p.getBalance()+operationDto.getAmount());
				return repo.save(p);
			}
			
			return repo.save(p);

		});
	}



	
	

	

	
}

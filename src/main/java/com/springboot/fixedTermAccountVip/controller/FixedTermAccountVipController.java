package com.springboot.fixedTermAccountVip.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fixedTermAccountVip.document.FixedTermAccountVip;
import com.springboot.fixedTermAccountVip.dto.AccountDto;
import com.springboot.fixedTermAccountVip.dto.FixedTermAccountVipDto;
import com.springboot.fixedTermAccountVip.dto.PersonalVipDto;
import com.springboot.fixedTermAccountVip.service.FixedTermAccountVipImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/fixedTermAccountVip")
public class FixedTermAccountVipController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedTermAccountVipController.class);
	
	
	@Autowired
	FixedTermAccountVipImpl service;

	@GetMapping
	public Mono<ResponseEntity<Flux<FixedTermAccountVip>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<FixedTermAccountVip>> search(@PathVariable String id) {

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<FixedTermAccountVip>> save(@RequestBody FixedTermAccountVip FixedTermAccountVip) {

		return service.save(FixedTermAccountVip)
				.map(f -> ResponseEntity.created(URI.create("/api/FixedTermAccountVip".concat(f.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(f));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<FixedTermAccountVip>> update(@RequestBody FixedTermAccountVip FixedTermAccountVip,
			@PathVariable String id) {

		return service.update(FixedTermAccountVip, id)
				.map(f -> ResponseEntity.created(URI.create("/api/FixedTermAccountVip".concat(f.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(f))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(f -> {
			return service.delete(f).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	//OPERACIONES QUE EXPONES SERVICIOS

//	@PostMapping("/PersonalVip")
//	public Mono<ResponseEntity<FixedTermAccountVipDto>> saveDto(@RequestBody FixedTermAccountVipDto FixedTermAccountVipDto) {
//
//        LOGGER.info("Controller -----> "+FixedTermAccountVipDto.toString());
//        
//		return service.saveDto(FixedTermAccountVipDto).map(f -> ResponseEntity.created(URI.create("/api/FixedTermAccountVip"))
//				.contentType(MediaType.APPLICATION_JSON).body(f));
//
//	}
//	
//	@PostMapping("/addAccountPer")
//	public Mono<ResponseEntity<PersonalVipDto>> saveAddDto(@RequestBody CuentaDto cuentaDto) {
//
//		 LOGGER.info("Controller -----> "+cuentaDto.toString());
//
//		return service.valid(cuentaDto).map(s -> ResponseEntity.created(URI.create("/api/currentAccount"))
//				.contentType(MediaType.APPLICATION_JSON).body(s));
//
//	}
	
	@PostMapping("/saveHeadline")
	public Mono<ResponseEntity<PersonalVipDto>> saveHeadline(@RequestBody AccountDto accountDto) {

		LOGGER.info("Controller ---> :"+accountDto.toString());

		return service.saveHeadline(accountDto).map(s -> ResponseEntity.created(URI.create("/api/FixedTermAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<PersonalVipDto>(HttpStatus.CONFLICT));

	}
	
	
	@PostMapping("/saveHeadlines")
	public Mono<ResponseEntity<FixedTermAccountVipDto>> saveHeadlines(@RequestBody FixedTermAccountVipDto FixedTermAccountVipDto) {

		LOGGER.info("Controller ----> "+FixedTermAccountVipDto.toString());

		return service.saveHeadlines(FixedTermAccountVipDto).map(s -> ResponseEntity.created(URI.create("/api/FixedTermAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<FixedTermAccountVipDto>(HttpStatus.CONFLICT));

	}
	
	
	
	
	
	
	
}

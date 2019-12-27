package com.springboot.fixedTermAccountVip.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.fixedTermAccountVip.document.FixedTermAccountVip;

import reactor.core.publisher.Mono;

public interface FixedTermAccountVipRepo extends ReactiveMongoRepository<FixedTermAccountVip, String> {

	 public Mono<FixedTermAccountVip> findByNumberAccount(String numberAccount);
}

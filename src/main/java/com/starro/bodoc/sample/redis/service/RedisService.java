package com.starro.bodoc.sample.redis.service;

import java.util.List;
import java.util.Optional;

import com.starro.bodoc.sample.redis.repository.RedisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starro.bodoc.sample.redis.domain.Redis;

import lombok.RequiredArgsConstructor;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description redis service
 **********************************************************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor
public class RedisService {
	
	public Iterable<Redis> getAll() {
		return redisRepository.findAll();
	}
	
	public Iterable<Redis> getAll(List<String> ids) {
		return redisRepository.findAllById(ids);
	}
	
	public Optional<Redis> get(String id) {
		return redisRepository.findById(id);
	}
	
	public Redis add(Redis meeting) {
		return redisRepository.save(meeting);
	}


	private final RedisRepository redisRepository;
}


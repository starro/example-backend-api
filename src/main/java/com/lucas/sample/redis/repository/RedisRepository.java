package com.lucas.sample.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucas.sample.redis.domain.Redis;

/**   
 * @since       2018.10.15
 * @author      lucas
 * @description redis repository
 **********************************************************************************************************************/
public interface RedisRepository extends CrudRepository<Redis, String>{
	
}

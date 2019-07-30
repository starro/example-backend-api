package com.starro.bodoc.sample.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.starro.bodoc.sample.redis.domain.Redis;

/**   
 * @since       2018.10.15
 * @author      starro
 * @description redis repository
 **********************************************************************************************************************/
public interface RedisRepository extends CrudRepository<Redis, String>{
	
}

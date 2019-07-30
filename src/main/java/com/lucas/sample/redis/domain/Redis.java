package com.lucas.sample.redis.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**   
 * @since       2018.10.15
 * @author      lucas
 * @description redis
 **********************************************************************************************************************/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@RedisHash("redis")
public class Redis {  

	@Id
	private String    id;
	private String    title;	
	private LocalDate startedAt;
}

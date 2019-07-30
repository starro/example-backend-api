package com.lucas.sample.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lucas.sample.api.entity.Sample;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample mybatis repostiry
 **********************************************************************************************************************/
@Mapper
public interface SampleMybatisRepository {
	
	List<Sample> findAll();
}

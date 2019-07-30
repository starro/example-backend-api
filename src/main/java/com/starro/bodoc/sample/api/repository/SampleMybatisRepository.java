package com.starro.bodoc.sample.api.repository;

import java.util.List;

import com.starro.bodoc.sample.api.entity.Sample;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description sample mybatis repostiry
 **********************************************************************************************************************/
@Mapper
public interface SampleMybatisRepository {
	
	List<Sample> findAll();
}

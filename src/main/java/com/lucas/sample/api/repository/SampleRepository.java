package com.lucas.sample.api.repository;

import com.lucas.sample.api.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample repository
 **********************************************************************************************************************/
@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, QuerydslPredicateExecutor<Sample> {

}
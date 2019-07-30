package com.starro.bodoc.sample.api.service;

import com.starro.bodoc.sample.api.entity.Sample;
import com.starro.bodoc.sample.api.repository.SampleRepository;
import com.querydsl.core.types.Predicate;
import com.starro.bodoc.sample.api.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description sample service
 **********************************************************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {

	private final SampleRepository sampleRepository;

	@Transactional(readOnly=true)
	public Page<Sample> getAll(Predicate predicate, Pageable pageable) {
		return sampleRepository.findAll(predicate, pageable);
	}

	@Transactional(readOnly=true)
	public Sample get(Long sampleId) {
		return sampleRepository.getOne(sampleId);
	}

	public Sample add(Sample sample) {
		return sampleRepository.save(sample);
	}

	public Sample modify(Long sampleId, Sample sample) {
		return SampleMapper.mapper.modify(sample, get(sampleId));
	}
	
	public void remove(Long sampleId) {
		sampleRepository.delete(get(sampleId));
	}
}
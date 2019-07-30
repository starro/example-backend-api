package com.lucas.sample.api.service;

import com.lucas.sample.api.entity.Sample;
import com.lucas.sample.api.repository.SampleRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.lucas.sample.api.mapper.SampleMapper.mapper;

/**   
 * @since       2018.10.03
 * @author      lucas
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
		return mapper.modify(sample, get(sampleId));
	}
	
	public void remove(Long sampleId) {
		sampleRepository.delete(get(sampleId));
	}
}
package com.starro.bodoc.sample.api.controller;

import com.starro.bodoc.sample.api.form.SampleForm.Request.Add;
import com.starro.bodoc.sample.api.form.SampleForm.Request.Find;
import com.starro.bodoc.sample.api.form.SampleForm.Request.Modify;
import com.starro.bodoc.sample.api.form.SampleForm.Response.FindAll;
import com.starro.bodoc.sample.api.form.SampleForm.Response.FindOne;
import com.starro.bodoc.sample.api.predicate.SamplePredicate;
import com.starro.bodoc.sample.api.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.starro.bodoc.sample.api.mapper.SampleMapper.mapper;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description sample controller
 **********************************************************************************************************************/
@Api(description="샘플")
@RestController
@RequiredArgsConstructor
@RequestMapping("${property.api.end-point}")
public class SampleController {

	private final SampleService sampleService;

	@ApiOperation("목록")
	@GetMapping("/samples")
	public Page<FindAll> getAll(@Valid Find find, @PageableDefault Pageable pageable){
		return sampleService.getAll(SamplePredicate.search(find), pageable).map(mapper::toFindAll);
	}

	@ApiOperation("조회")
	@GetMapping("/samples/{sampleId}")
	public FindOne get(@PathVariable Long sampleId){
		return mapper.toFindOne(sampleService.get(sampleId));
	}

	@ApiOperation("등록")
	@PostMapping("/samples")
	public FindOne add(@Valid @RequestBody Add add){
		return mapper.toFindOne(sampleService.add(mapper.toSample(add)));
	}

	@ApiOperation("수정")
	@PutMapping("/samples/{sampleId}")
	public FindOne modify(@PathVariable Long sampleId, @Valid @RequestBody Modify modify){
		return mapper.toFindOne(sampleService.modify(sampleId, mapper.toSample(sampleId, modify)));
	}

	@ApiOperation("삭제")
	@DeleteMapping("/samples/{sampleId}")
	public void remove(@PathVariable Long sampleId){
		sampleService.remove(sampleId);
	}
}
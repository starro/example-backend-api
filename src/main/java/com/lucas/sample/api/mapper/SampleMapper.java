package com.lucas.sample.api.mapper;

import com.lucas.sample.api.entity.Sample;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.lucas.sample.api.form.SampleForm.Request.Add;
import com.lucas.sample.api.form.SampleForm.Request.Modify;
import com.lucas.sample.api.form.SampleForm.Response.FindAll;
import com.lucas.sample.api.form.SampleForm.Response.FindOne;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample mapper
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface SampleMapper {

    SampleMapper mapper = Mappers.getMapper(SampleMapper.class);

    Sample  toSample (         Add    form);
    Sample  toSample (Long id, Modify form);
    FindOne toFindOne(Sample entity);
    FindAll toFindAll(Sample entity);

    @Mappings({
         @Mapping(target="id",        ignore=true)
    	,@Mapping(target="createdAt", ignore=true)
    })
    Sample modify(Sample source, @MappingTarget Sample target);
}
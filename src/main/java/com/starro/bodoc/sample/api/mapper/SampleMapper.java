package com.starro.bodoc.sample.api.mapper;

import com.starro.bodoc.sample.api.entity.Sample;
import com.starro.bodoc.sample.api.form.SampleForm;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description sample mapper
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface SampleMapper {

    SampleMapper mapper = Mappers.getMapper(SampleMapper.class);

    Sample toSample (SampleForm.Request.Add form);
    Sample  toSample (Long id, SampleForm.Request.Modify form);
    SampleForm.Response.FindOne toFindOne(Sample entity);
    SampleForm.Response.FindAll toFindAll(Sample entity);

    @Mappings({
         @Mapping(target="id",        ignore=true)
    	,@Mapping(target="createdAt", ignore=true)
    })
    Sample modify(Sample source, @MappingTarget Sample target);
}
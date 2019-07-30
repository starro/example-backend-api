package com.starro.bodoc.sample.api.predicate;

import java.util.Optional;

import com.starro.bodoc.sample.api.entity.QSample;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.starro.bodoc.sample.api.form.SampleForm;

/**    
 * @since       2018.10.03
 * @author      starro
 * @description sample predicate
 **********************************************************************************************************************/
public class SamplePredicate {
	
	public static Predicate search(SampleForm.Request.Find find) {
		
		QSample        sample  = QSample.sample;
		BooleanBuilder builder = new BooleanBuilder();
		
		Optional.ofNullable(find.getUserId()).ifPresent(p -> builder.and(sample.userId.eq(p)));
		Optional.ofNullable(find.getTitle ()).ifPresent(p -> builder.and(sample.title.eq(p)));
		
		return builder;
	}
}
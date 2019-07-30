package com.lucas.sample.api.predicate;

import java.util.Optional;
import com.lucas.sample.api.form.SampleForm.Request;
import com.lucas.sample.api.entity.QSample;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**    
 * @since       2018.10.03
 * @author      lucas
 * @description sample predicate
 **********************************************************************************************************************/
public class SamplePredicate {
	
	public static Predicate search(Request.Find find) {
		
		QSample        sample  = QSample.sample;
		BooleanBuilder builder = new BooleanBuilder();
		
		Optional.ofNullable(find.getUserId()).ifPresent(p -> builder.and(sample.userId.eq(p)));
		Optional.ofNullable(find.getTitle ()).ifPresent(p -> builder.and(sample.title.eq(p)));
		
		return builder;
	}
}
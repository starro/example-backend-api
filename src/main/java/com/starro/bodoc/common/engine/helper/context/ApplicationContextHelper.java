package com.starro.bodoc.common.engine.helper.context;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description application context helper
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class ApplicationContextHelper {

	public static <T> T getInstance(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}	
	
	private static final ApplicationContext applicationContext = null;
}

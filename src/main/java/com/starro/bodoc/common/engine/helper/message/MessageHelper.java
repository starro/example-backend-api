package com.starro.bodoc.common.engine.helper.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.starro.bodoc.common.engine.exception.common.ExceptionCode;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description message helper
 **********************************************************************************************************************/
@Component
public class MessageHelper {

	@Autowired 
	private MessageHelper(MessageSource messageSource) { 
		MessageHelper.messageSource = messageSource; 
	}
	
	public static String getMessage(ExceptionCode exceptionCode){
		return messageSource.getMessage(exceptionCode.name(), null, null);
	}
	
	public static String getMessage(ExceptionCode exceptionCode, String ... params){
		return messageSource.getMessage(exceptionCode.name(), params, null);
	}
	
	public static String getMessage(MessageCode messageCode){
		return messageSource.getMessage(messageCode.name(), null, null);
	}
	
	public static String getMessage(MessageCode messageCode, String ... params){
		return messageSource.getMessage(messageCode.name(), params, null);
	}
	
	
	private static MessageSource messageSource = null;
}

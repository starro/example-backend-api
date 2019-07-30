package com.starro.bodoc.common.engine.exception;

import com.starro.bodoc.common.engine.exception.common.ExceptionCode;

/**
 * @since       2018.10.15
 * @author      starro
 * @description not acceptable exception(요청을 수락 할 수 없는 예외)
 **********************************************************************************************************************/
@SuppressWarnings("serial")
public class NotAcceptableException extends RuntimeException {

	public NotAcceptableException(){
		super(ExceptionCode.E00010004.name());
	}
	
	public NotAcceptableException(ExceptionCode exceptionCode){
		super(exceptionCode.name());
	}
	
	public NotAcceptableException(ExceptionCode exceptionCode, Exception exception){
		super(exceptionCode.name(), exception);
	}
}

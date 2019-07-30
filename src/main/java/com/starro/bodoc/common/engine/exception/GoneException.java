package com.starro.bodoc.common.engine.exception;

import com.starro.bodoc.common.engine.exception.common.ExceptionCode;

/**
 * @since       2018.10.15
 * @author      starro
 * @description gone exception(더이상 해당 요청을 하지 못했을 경우 발생되는 예외)
 **********************************************************************************************************************/
@SuppressWarnings("serial")
public class GoneException extends RuntimeException {
	
	public GoneException(){
		super(ExceptionCode.E00010003.name());
	}
	
	public GoneException(ExceptionCode exceptionCode){
		super(exceptionCode.name());
	}
	
	public GoneException(ExceptionCode exceptionCode, Exception exception){
		super(exceptionCode.name(), exception);
	}
}

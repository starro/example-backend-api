package com.starro.bodoc.common.engine.exception;

import com.starro.bodoc.common.engine.exception.common.ExceptionCode;

/**
 * @since       2018.10.15
 * @author      starro
 * @description not found exception(데이터 조회를 하지 못했을 경우 발생되는 예외)
 **********************************************************************************************************************/
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	public NotFoundException(){
		super(ExceptionCode.E00010004.name());
	}
	
	public NotFoundException(ExceptionCode exceptionCode){
		super(exceptionCode.name());
	}
	
	public NotFoundException(ExceptionCode exceptionCode, Exception exception){
		super(exceptionCode.name(), exception);
	}
}

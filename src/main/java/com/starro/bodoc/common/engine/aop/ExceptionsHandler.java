package com.starro.bodoc.common.engine.aop;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.starro.bodoc.common.engine.exception.GoneException;
import com.starro.bodoc.common.engine.exception.NotAcceptableException;
import com.starro.bodoc.common.engine.exception.NotFoundException;

/**  
 * @since       2018.10.03
 * @author      starro
 * @description exception handler(reference site : http://onecellboy.tistory.com/346)
 **********************************************************************************************************************/
@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
	public ResponseEntity<?> handleNoDataException(RuntimeException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotAcceptableException.class)
	public ResponseEntity<?> handleNotAcceptableException(NotAcceptableException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(GoneException.class)
	public ResponseEntity<?> handleGoneException(GoneException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.GONE);
	}
}

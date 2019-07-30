package com.starro.bodoc.common.engine.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**  
 * @since       2018.10.11
 * @author      starro
 * @description response handler
 **********************************************************************************************************************/
@ControllerAdvice(annotations={ResponseMapper.class})
@Deprecated
public class ResponseHandler {
    
}


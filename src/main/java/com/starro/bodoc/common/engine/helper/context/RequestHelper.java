package com.starro.bodoc.common.engine.helper.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description request helper
 **********************************************************************************************************************/
@Component
public class RequestHelper {
 
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
}

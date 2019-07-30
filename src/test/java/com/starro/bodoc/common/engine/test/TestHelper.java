package com.starro.bodoc.common.engine.test;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description test helper
 **********************************************************************************************************************/
public class TestHelper {

	public static MockMvc mock;
	public static RestDocumentationResultHandler handler;

	public static void initialize(MockMvc _mock, RestDocumentationResultHandler _handler){
		mock    = _mock;
		handler = _handler;
	}

	public static MockHttpServletRequestBuilder get(String url){
		return RestDocumentationRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON);
	}

	public static MockHttpServletRequestBuilder get(String url, Object ... params){
		return RestDocumentationRequestBuilders.get(url, params).contentType(MediaType.APPLICATION_JSON);
	}

	public static MockHttpServletRequestBuilder post(String url){
		return RestDocumentationRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON);
	}

	public static MockHttpServletRequestBuilder put(String url, Object ... params){
		return RestDocumentationRequestBuilders.put(url, params).contentType(MediaType.APPLICATION_JSON);
	}

	public static MockHttpServletRequestBuilder delete(String url, Object ... params){
		return RestDocumentationRequestBuilders.delete(url, params).contentType(MediaType.APPLICATION_JSON);
	}
}
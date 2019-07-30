package com.starro.bodoc.common.engine.config.web;

import com.google.common.collect.ImmutableList;
import com.starro.bodoc.common.engine.constant.StringConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @since       2019.04.15
 * @author      starro
 * @description web cors filter
 **********************************************************************************************************************/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  request  = HttpServletRequest.class.cast (servletRequest);
        HttpServletResponse response = HttpServletResponse.class.cast(servletResponse);

        setAccessControlAllowOrigin (response);
        setAccessControlAllowMethods(response);
        setAccessControlAllowHeaders(response);

        if(StringUtils.containsIgnoreCase(RequestMethod.OPTIONS.name(), request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    private void setAccessControlAllowOrigin(HttpServletResponse response){
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, StringConstant.ASTERISK);
    }

    private void setAccessControlAllowHeaders(HttpServletResponse response){
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, String.join(StringConstant.COMMA, ImmutableList.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE)));
    }

    private void setAccessControlAllowMethods(HttpServletResponse response){
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, String.join(StringConstant.COMMA, ImmutableList.of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.HEAD.name())));
    }
}
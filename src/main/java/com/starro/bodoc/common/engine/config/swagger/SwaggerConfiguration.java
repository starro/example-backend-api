package com.starro.bodoc.common.engine.config.swagger;

import java.util.Collections;

import com.google.common.collect.ImmutableList;
import com.starro.bodoc.common.engine.helper.property.PropertyHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**   
 * @since       2018.10.03
 * @author      starro
 * @description swagger configuration
 **********************************************************************************************************************/
@Configuration
@EnableSwagger2
@Profile({"default", "dev"}) 
public class SwaggerConfiguration { 

	@Bean
	public Docket swagger() {

		return new Docket(DocumentationType.SWAGGER_2) 
				                           .select() 
				                           .apis(RequestHandlerSelectors.any()) 
				                           .paths(PathSelectors.ant("/api/**")) 
				                           .build()
				                           .securitySchemes(ImmutableList.of(new ApiKey("authorization", "authorization", "header")))
				                           .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		return new ApiInfo(
			    PropertyHelper.getProperty().getSwagger().getInfo().getTitle()
			   ,PropertyHelper.getProperty().getSwagger().getInfo().getDesc()
			   ,PropertyHelper.getProperty().getSwagger().getInfo().getVersion() 
		       ,null 
		       ,new Contact(
		    	     PropertyHelper.getProperty().getSwagger().getContact().getName()
		    	    ,PropertyHelper.getProperty().getSwagger().getContact().getUrl()
		    	    ,PropertyHelper.getProperty().getSwagger().getContact().getEmail()
		        ) 
		       ,null
		       ,null 
		       ,Collections.emptyList()
		 );
	}
}

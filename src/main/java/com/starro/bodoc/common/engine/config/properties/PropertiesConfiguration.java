package com.starro.bodoc.common.engine.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description properties configuration
 **********************************************************************************************************************/
@Data
@Component
@ConfigurationProperties(prefix="property")
public class PropertiesConfiguration {
	
	@Data
	public static class Api {
	
		private String endPoint = null;
	}
	
	@Data
	public static class Swagger {
	
		private Info    info    = null;
		private Contact contact = null;
		
		@Data
		public static class Info {
			
			private String title   = null;
			private String desc    = null;
			private String version = null;
		}
		
		@Data
		public static class Contact {
			
			private String name  = null;
			private String url   = null;
			private String email = null;
		}
	}
	
	private Api     api     = null;
	private Swagger swagger = null;
}

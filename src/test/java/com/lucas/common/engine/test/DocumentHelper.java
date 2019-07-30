package com.lucas.common.engine.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.SneakyThrows;

/**   
 * @since       2019.07.03
 * @author      lucas
 * @description document helper
 **********************************************************************************************************************/
public class DocumentHelper {

	@SneakyThrows
	public static String getDescription(Class clazz, String field) {
		return clazz.getDeclaredField(field).getAnnotation(ApiModelProperty.class).value();
	}
}
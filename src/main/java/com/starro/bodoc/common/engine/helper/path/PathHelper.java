package com.starro.bodoc.common.engine.helper.path;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description path helper
 **********************************************************************************************************************/
@Component
public class PathHelper {

	public static File getPath(String path) throws IOException {
		return new ClassPathResource(path).getFile();
	}

	public static String getRootPath(){
		return new FileSystemResource(File.separator).getFile().getAbsolutePath();
	}
}

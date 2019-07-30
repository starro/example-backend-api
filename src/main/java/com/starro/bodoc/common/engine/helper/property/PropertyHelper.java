package com.starro.bodoc.common.engine.helper.property;

import com.starro.bodoc.common.engine.config.properties.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description property helper 
 **********************************************************************************************************************/
@Component
public class PropertyHelper {
	
	@Autowired
    private PropertyHelper(PropertiesConfiguration property) {
        PropertyHelper.property = property;
    }
	
	@Getter
	private static PropertiesConfiguration property = null;
}
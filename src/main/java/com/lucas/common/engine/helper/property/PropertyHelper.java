package com.lucas.common.engine.helper.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucas.common.engine.config.properties.PropertiesConfiguration;

import lombok.Getter;

/**   
 * @since       2018.10.03
 * @author      lucas
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
package com.starro.bodoc.sample.websocket.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @since       2018.10.11
 * @author      starro
 * @description websocket
 **********************************************************************************************************************/
@Setter
@Getter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Websocket {

	private String        userId;
    private String        content;
	private LocalDateTime createdAt;
}

package com.starro.bodoc.sample.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.starro.bodoc.sample.websocket.domain.Websocket;

/**   
 * @since       2018.10.11
 * @author      starro
 * @description websocket controller
 **********************************************************************************************************************/
@Controller
public class WebsocketController {

    @MessageMapping("/rooms/{roomNo}/messages")
    @SendTo("/subscribe/rooms/{roomNo}")
    public Websocket message(@DestinationVariable String roomNo, Websocket message) {
    	return message;
    }
}


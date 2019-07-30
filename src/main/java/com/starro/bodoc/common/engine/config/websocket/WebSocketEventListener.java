package com.starro.bodoc.common.engine.config.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**   
 * @since       2018.10.11
 * @author      starro
 * @description websocket event configuration
 **********************************************************************************************************************/
@Component
public class WebSocketEventListener {

	@EventListener public void onEvent(SessionConnectEvent     event) { }
	@EventListener public void onEvent(SessionConnectedEvent   event) { }
	@EventListener public void onEvent(SessionSubscribeEvent   event) { }
	@EventListener public void onEvent(SessionUnsubscribeEvent event) { }
	@EventListener public void onEvent(SessionDisconnectEvent  event) { }
}

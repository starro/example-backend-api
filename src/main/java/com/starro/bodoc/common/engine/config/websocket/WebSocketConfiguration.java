package com.starro.bodoc.common.engine.config.websocket;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**   
 * @since       2018.10.11
 * @author      starro
 * @description websocket configuration
 **********************************************************************************************************************/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint      ("/stomp-chat")
        	    .setAllowedOrigins("*")
        	    .withSockJS()
        	    .setDisconnectDelay(1000L);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/publish");
        registry.enableSimpleBroker               ("/subscribe");
    }

	@Override public void    addArgumentResolvers          (List<HandlerMethodArgumentResolver>   o) {	             }
	@Override public void    addReturnValueHandlers        (List<HandlerMethodReturnValueHandler> o) {	             }
	@Override public void    configureClientInboundChannel (ChannelRegistration                   o) {	             }
	@Override public void    configureClientOutboundChannel(ChannelRegistration                   o) {	             }
	@Override public void    configureWebSocketTransport   (WebSocketTransportRegistration        o) {	             }
	@Override public boolean configureMessageConverters    (List<MessageConverter>                o) { return false; }
}

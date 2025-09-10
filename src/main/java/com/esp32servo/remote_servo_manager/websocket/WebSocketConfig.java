package com.esp32servo.remote_servo_manager.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final ServoWebSocketHandler servoWebSocketHandler;
	
	public WebSocketConfig(ServoWebSocketHandler servoWebSocketHandler) {
		this.servoWebSocketHandler = servoWebSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// Registry the handler in route "/ws/servo"
		registry.addHandler(servoWebSocketHandler,"/ws/servo").setAllowedOrigins("*");
	}
	
	
}

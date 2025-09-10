package com.esp32servo.remote_servo_manager.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ServoWebSocketHandler extends TextWebSocketHandler {
	
	private final List<WebSocketSession> sessions = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ESP32 Conectado: " + session.getId());
		sessions.add(session);
	}
	
	public void sendCommandAct() {
		TextMessage message = new TextMessage("ACT");
        System.out.println("Enviando comando 'ACT' a " + sessions.size() + " ESP32s.");
        for (WebSocketSession session : sessions) {
        	try {
        		if (session.isOpen()) {
        			session.sendMessage(message);
        		}
        	} catch (IOException e) {
                System.err.println("Error enviando mensaje: " + e.getMessage());
			}
        }
	}
	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		 System.out.println("ESP32 Desconectado: " + session.getId());
	     sessions.remove(session);
	}
	
	

}

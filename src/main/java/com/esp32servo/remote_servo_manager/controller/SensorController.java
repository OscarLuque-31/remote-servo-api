package com.esp32servo.remote_servo_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esp32servo.remote_servo_manager.model.SensorRead;
import com.esp32servo.remote_servo_manager.repository.SensorReadRepository;
import com.esp32servo.remote_servo_manager.websocket.ServoWebSocketHandler;

@RestController
@RequestMapping("/api")
public class SensorController {
	
	private final SensorReadRepository sensorRepository;
    private final ServoWebSocketHandler servoWebSocketHandler;

	
	public SensorController(SensorReadRepository sensorRepository, ServoWebSocketHandler servoWebSocketHandler) {
		this.sensorRepository = sensorRepository;
        this.servoWebSocketHandler = servoWebSocketHandler;
	}
	
	@PostMapping("/actuar")
	public ResponseEntity<String> actServo() {
		servoWebSocketHandler.sendCommandAct();
        return ResponseEntity.ok("Comando 'ACT' enviado al ESP32.");
	}
	
	@PostMapping("/save")
	public ResponseEntity<SensorRead> saveSensorRead(@RequestBody SensorRead sensorRead) {
		SensorRead readSave = sensorRepository.save(sensorRead);
		return ResponseEntity.ok(readSave);
	}

}

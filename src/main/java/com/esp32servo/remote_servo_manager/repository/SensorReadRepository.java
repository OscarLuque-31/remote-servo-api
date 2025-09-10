package com.esp32servo.remote_servo_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esp32servo.remote_servo_manager.model.SensorRead;

public interface SensorReadRepository extends JpaRepository<SensorRead, Long> {

}

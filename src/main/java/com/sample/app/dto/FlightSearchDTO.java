package com.sample.app.dto;

import java.time.Duration;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class FlightSearchDTO {
	@JsonIgnore
	private long id;
	private String flightNumber;
	private String origin;
	private String destination;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private double price;
	@JsonIgnore
	private Duration duration;

	public FlightSearchDTO(long id, String flightNumber, String origin, String destination, LocalTime departureTime,
			LocalTime arrivalTime, double price) {
		this.id = id;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
		this.duration = Duration.between(departureTime, arrivalTime);
	}
}

package com.sample.app.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Flight_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FlightSearchDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "flight_number")
	private String flightNumber;
	
	@Column(name = "origin")
	private String origin;
	@Column(name = "destination")
	private String destination;
	@Column(name = "departure_time", columnDefinition = "TIME")
	private LocalTime departureTime;
	@Column(name = "arrival_time", columnDefinition = "TIME")
	private LocalTime arrivalTime;
	@Column(name = "price")
	private double price;
}

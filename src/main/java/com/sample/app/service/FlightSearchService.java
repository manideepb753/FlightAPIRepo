package com.sample.app.service;

import java.util.List;

import com.sample.app.dto.FlightSearchDTO;
import com.sample.app.exception.FlightsNotFoundException;
import com.sample.app.exception.ValidationException;

public interface FlightSearchService {

	public List<FlightSearchDTO> searchFlights(String origin, String destination,
			String sortType) throws FlightsNotFoundException, ValidationException;
}

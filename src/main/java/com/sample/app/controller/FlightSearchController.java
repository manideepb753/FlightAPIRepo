package com.sample.app.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sample.app.dto.FlightSearchDTO;
import com.sample.app.exception.FlightsNotFoundException;
import com.sample.app.exception.ValidationException;
import com.sample.app.service.FlightSearchService;
import static com.sample.app.constants.FlightConstants.PRICE_SORT_TYPE;
import static com.sample.app.constants.FlightConstants.DURATION_SORT_TYPE;

@RestController
public class FlightSearchController {

	@Autowired
	private FlightSearchService flightSearchService;

    @GetMapping(value = "/flights")
	@ResponseStatus(value = HttpStatus.OK)
	public List<FlightSearchDTO> searchFlights(@RequestParam(value="origin",required = true) String origin,
			@RequestParam(value="destination",required = true)  String destination,
			@RequestParam(required = false) String sortType
			) throws FlightsNotFoundException, ValidationException {
    	
    	if(sortType !=null && !(PRICE_SORT_TYPE.equalsIgnoreCase(sortType) || DURATION_SORT_TYPE.equalsIgnoreCase(sortType))) {
    		throw new ValidationException("sortType should be one of price or duration");
    	}
    	
		return flightSearchService.searchFlights(origin, destination, sortType);
	}
}

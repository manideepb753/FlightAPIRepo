package com.sample.app.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sample.app.dto.FlightSearchDTO;
import com.sample.app.entity.FlightSearchDetails;
import com.sample.app.exception.FlightsNotFoundException;
import com.sample.app.exception.ValidationException;
import com.sample.app.repository.FlightSearchRepository;

import static com.sample.app.constants.FlightConstants.PRICE_SORT_TYPE;
import static com.sample.app.constants.FlightConstants.DURATION_SORT_TYPE;


@Service
public class FlightSearchServiceImpl implements FlightSearchService {
	
	@Autowired
	FlightSearchRepository flightsearchRepo;

	@Override
	public List<FlightSearchDTO> searchFlights(String origin, String destination, String sortType) throws FlightsNotFoundException, ValidationException {

		List<FlightSearchDetails> flightDetails = null;
		List<FlightSearchDTO> flightList=null;
	

		if (!origin.isBlank() && !destination.isBlank()) {
			flightDetails = flightsearchRepo.findAllByOriginAndDestination(origin, destination);
			if (CollectionUtils.isEmpty(flightDetails)) {
				throw new FlightsNotFoundException("Flight Details Not Available for : "+origin+" and "+destination);
			} else {
				flightList  = convertyEntityToModel(flightDetails);
			}
		} else {
			throw new FlightsNotFoundException("Flight Details Not Available: ");
		}

		if(PRICE_SORT_TYPE.equalsIgnoreCase(sortType)) {
			flightList = flightList.stream().sorted(Comparator.comparingDouble(FlightSearchDTO::getPrice))
			.collect(Collectors.toList());
		}
		else {
			if (DURATION_SORT_TYPE.equalsIgnoreCase(sortType)) {
				flightList = flightList.stream().sorted(Comparator.comparing(FlightSearchDTO::getDuration))
						.collect(Collectors.toList());
			}
		}

		return flightList;
	}
	
	public static List<FlightSearchDTO> convertyEntityToModel(List<FlightSearchDetails> flightDetails) {
		List<FlightSearchDTO> flightList = null;
		if (!CollectionUtils.isEmpty(flightDetails)) {
			flightList = flightDetails.stream().filter(Objects::nonNull)
					.collect(
							Collectors.mapping(
									fs -> new FlightSearchDTO(fs.getId(),fs.getFlightNumber(), fs.getOrigin(), fs.getDestination(),
											fs.getDepartureTime(), fs.getArrivalTime(), fs.getPrice()),
									Collectors.toList()));
		}
		return flightList;
	}


}

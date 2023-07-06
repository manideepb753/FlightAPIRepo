
package com.sample.app.service;

import static com.sample.app.constants.FlightConstants.DURATION_SORT_TYPE;
import static com.sample.app.constants.FlightConstants.PRICE_SORT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.sample.app.constants.FlightConstants;
import com.sample.app.dto.FlightSearchDTO;
import com.sample.app.entity.FlightSearchDetails;
import com.sample.app.exception.FlightsNotFoundException;
import com.sample.app.exception.ValidationException;
import com.sample.app.repository.FlightSearchRepository;

@ExtendWith(MockitoExtension.class)
class FlightSearchServiceTest {

	FlightSearchRepository flightSerachRepo = mock(FlightSearchRepository.class);

	@InjectMocks
	FlightSearchServiceImpl flightSearchService;
	String origin;
	String destination;

	// @SuppressWarnings("unchecked")

	@Test
	void testFlightListByOriginAndDestination() throws FlightsNotFoundException, ValidationException {

		List<FlightSearchDetails> flightDetailList = this.buildTestFlightDetails();
		flightDetailList = flightDetailList.stream()
				.filter(f -> f.getOrigin().equalsIgnoreCase("BOM") && f.getDestination().equalsIgnoreCase("DEL"))
				.collect(Collectors.toList());

		when(flightSerachRepo.findAllByOriginAndDestination("BOM", "DEL")).thenReturn(flightDetailList);
		
		List<FlightSearchDTO> flightListPrice = this.flightSearchService.searchFlights("BOM", "DEL",FlightConstants.PRICE_SORT_TYPE);
		assertThat(flightListPrice).isNotEmpty()
				.allMatch(f -> "BOM".equals(f.getOrigin()) && "DEL".equals(f.getDestination()));
		verify(this.flightSerachRepo).findAllByOriginAndDestination("BOM", "DEL");
		
		List<FlightSearchDTO> flightListDest = this.flightSearchService.searchFlights("BOM", "DEL",FlightConstants.DURATION_SORT_TYPE);
		assertThat(flightListDest).isNotEmpty()
				.allMatch(f -> "BOM".equals(f.getOrigin()) && "DEL".equals(f.getDestination()));
		//verify(this.flightSerachRepo).findAllByOriginAndDestination("BOM", "DEL");
	}

	private List<FlightSearchDetails> buildTestFlightDetails() {
		List<FlightSearchDetails> flightList = new ArrayList<FlightSearchDetails>();
		FlightSearchDetails flightDetails = new FlightSearchDetails();
		flightDetails.setId(1);
		flightDetails.setFlightNumber("F101");
		flightDetails.setOrigin("BOM");
		flightDetails.setDestination("DEL");
		flightDetails.setArrivalTime(LocalTime.parse("12:35"));
		flightDetails.setDepartureTime(LocalTime.parse("19:30"));
		flightDetails.setPrice(80.0);
		flightList.add(flightDetails);

		return flightList;
	}
}

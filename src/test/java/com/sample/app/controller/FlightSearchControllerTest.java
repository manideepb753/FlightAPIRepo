
package com.sample.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.sample.app.constants.FlightConstants;
import com.sample.app.service.FlightSearchService;

@SpringBootTest
@AutoConfigureMockMvc
class FlightSearchControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	FlightSearchService flightSerachService=mock(FlightSearchService.class);

	@Test
	void retrieveFlightDetailsByOriginAndDestination() throws Exception {

		ResultActions response = mockMvc
				  .perform(MockMvcRequestBuilders.get("/flights").param("origin",
				  "BOM").param("destination", "DEL"));
				  response.andExpect(MockMvcResultMatchers.status().isOk()); 
				  response.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$[0].flightNumber").exists())
					.andExpect(jsonPath("$[1].origin").value("BOM"))
					.andExpect(jsonPath("$[2].destination").value("DEL"));
	}

	@Test
	void testMissingParam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/flights").param("origin", "LHR"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(result -> assertTrue(
						result.getResolvedException() instanceof MissingServletRequestParameterException))
				.andExpect(result -> assertEquals(
						"Required request parameter 'destination' for method parameter type String is not present",
						result.getResolvedException().getMessage()));

	}

	@Test
	void testEmptyRequiredFileldDestinationParam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/flights").param("origin", "LHR"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(result -> assertTrue(
						result.getResolvedException() instanceof MissingServletRequestParameterException))
				.andReturn();

	}
}

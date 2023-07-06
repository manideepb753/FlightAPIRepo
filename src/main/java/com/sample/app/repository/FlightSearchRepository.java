package com.sample.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.FlightSearchDetails;

@Repository
public interface FlightSearchRepository extends CrudRepository<FlightSearchDetails, Long> {

	List<FlightSearchDetails> findAllByOriginAndDestination(String origin, String destination);

}

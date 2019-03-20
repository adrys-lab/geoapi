package com.dexmatech.controller;

import com.dexmatech.domain.exception.GeoNamesException;
import com.dexmatech.domain.exception.GoogleMapsException;
import com.dexmatech.domain.response.GeoLocation;
import com.dexmatech.mapper.GeocodingResultMapper;
import com.dexmatech.service.geonames.IGeoNamesService;
import com.dexmatech.service.googlemaps.IGoogleMapsService;
import com.google.maps.model.GeocodingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/geoData")
public class GeoDataController {

	private final IGoogleMapsService googleMapsService;
	private final IGeoNamesService geoNamesService;

	@Autowired
	public GeoDataController(final IGoogleMapsService googleMapsService, final IGeoNamesService geoNamesService) {
		this.googleMapsService = googleMapsService;
		this.geoNamesService = geoNamesService;
	}

	@GetMapping("/geoByLatLon/{lat}/{lon}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GeoLocation> getGeoByLatLon(@PathVariable double lat, @PathVariable double lon) {
		try {
			final List<GeocodingResult> results = googleMapsService.getGeoByLatLon(lat, lon);
			return ResponseEntity.ok(GeocodingResultMapper.map(results.stream().findFirst()));
		} catch (GoogleMapsException e) {
			log.error(String.format("Exception on getGeoByLatLon with %s and %s", lat, lon));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/geoByAddress/{address}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GeoLocation> getGeoByAddress(@PathVariable String address) {
		try {
			final List<GeocodingResult> results = googleMapsService.getGeoByAddress(address);
			return ResponseEntity.ok(GeocodingResultMapper.map(results.stream().findFirst()));
		} catch (GoogleMapsException e) {
			log.error(String.format("Exception on getGeoByAddress with %s", address));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/geoByPostalCodeAndCountry/{postalCode}/{country}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GeoLocation> geoByPostalCodeAndCountry(@PathVariable String postalCode, @PathVariable String country) {
		try {
			final List<GeocodingResult> results = googleMapsService.getGeoByPostalCodeAndCountry(postalCode, country);
			return ResponseEntity.ok(GeocodingResultMapper.map(results.stream().findFirst()));
		} catch (GoogleMapsException e) {
			log.error(String.format("Exception on geoByPostalCodeAndCountry with postal code %s and country %s", postalCode, country));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/geoByCityAndAddress/{city}/{address}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GeoLocation> getGeoByCityAndAddress(@PathVariable String city, @PathVariable String address) {
		try {
			final List<GeocodingResult> results = googleMapsService.getGeoByCityAndAddress(city, address);
			return ResponseEntity.ok(GeocodingResultMapper.map(results.stream().findFirst()));
		} catch (GoogleMapsException e) {
			log.error(String.format("Exception on getGeoByCityAndAddress with city %s and address %s", city, address));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/countryCodesByPostalCode/{postalCode}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<String>> getCountryCodesByPostalCode(@PathVariable String postalCode) {
		try {
			return ResponseEntity.ok(geoNamesService.getCountryCodesByPostalCode(postalCode));
		} catch (GeoNamesException e) {
			log.error(String.format("Exception on getCountryCodesByPostalCode with %s.", postalCode));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/timeZoneIdByLatLon/{lat}/{lon}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> getTimeZoneIdByLatLon(@RequestParam double lat, double lon) {
		try {
			return ResponseEntity.ok(geoNamesService.getTimeZoneIdByLatLon(lat, lon));
		} catch (GeoNamesException e) {
			log.error(String.format("Exception on getGeoByAddress with %s and %s", lat, lon));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
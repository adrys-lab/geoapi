package com.dexmatech.service.googlemaps;

import com.dexmatech.domain.exception.GoogleMapsException;
import com.google.maps.model.GeocodingResult;

import java.util.List;

public interface IGoogleMapsService {

	/*
	 * Get Geocoding data given Latitude and Longitude
	 */
	List<GeocodingResult> getGeoByLatLon(double lat, double lon) throws GoogleMapsException;

	/*
	 * Get Geocoding data given an Address
	 */
	List<GeocodingResult> getGeoByAddress(String address) throws GoogleMapsException;

	/*
	 * Get Geocoding data given a Postal code and Its Country
	 */
	List<GeocodingResult> getGeoByPostalCodeAndCountry(String postalCode, String country) throws GoogleMapsException;

	/*
	 * Get Geocoding data given a city and Address
	 */
	List<GeocodingResult> getGeoByCityAndAddress(String city, String address) throws GoogleMapsException;
}

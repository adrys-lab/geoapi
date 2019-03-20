package com.dexmatech.service.geonames;

import com.dexmatech.domain.exception.GeoNamesException;

import java.util.List;

public interface IGeoNamesService {

	/*
	 * Get a List of valid country codes for a given postal code
	 */
	List<String> getCountryCodesByPostalCode(final String postalCode) throws GeoNamesException;

	/*
	 * Get a Timezone Id for a given latitude and longitude
	 */
	String getTimeZoneIdByLatLon(double lat, double lon) throws GeoNamesException;
}

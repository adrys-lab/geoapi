package com.dexmatech.service.geonames;

import com.dexmatech.config.GeoNamesConfig;
import com.dexmatech.domain.exception.GeoNamesException;
import org.geonames.PostalCode;
import org.geonames.PostalCodeSearchCriteria;
import org.geonames.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoNamesService extends GeoNamesContext implements IGeoNamesService {

	@Autowired
	public GeoNamesService(GeoNamesConfig geoNamesConfig) {
		super(geoNamesConfig);
	}

	/*
	 * Call External API to get all country codes related with a given postalCode
	 */
	@Override
	public List<String> getCountryCodesByPostalCode(final String postalCode) throws GeoNamesException {
		try {
			final PostalCodeSearchCriteria postalCodeSearchCriteria = new PostalCodeSearchCriteria();
			postalCodeSearchCriteria.setPostalCode(postalCode);
			return WebService.postalCodeSearch(postalCodeSearchCriteria)
					.stream()
					.map(PostalCode::getCountryCode)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new GeoNamesException(String.format("Exception occurred on getCountryCodesByPostalCode with %s.", postalCode), e);
		}
	}

	/*
	 * Call External API to get timezoneId by lat lon.
	 */
	@Override
	public String getTimeZoneIdByLatLon(double lat, double lon) throws GeoNamesException {
		try {
			return WebService.timezone(lat, lon).getTimezoneId();
		} catch (Exception e) {
			throw new GeoNamesException(String.format("Exception occurred on getTimeZoneIdByLatLon with %s and %s", lat, lon), e);
		}
	}
}

package com.dexmatech.service.googlemaps;

import com.dexmatech.config.GoogleMapsConfig;
import com.dexmatech.domain.exception.GoogleMapsException;
import com.google.maps.errors.ApiException;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleMapsService extends GoogleMapsContext implements IGoogleMapsService {

	@Autowired
	public GoogleMapsService(GoogleMapsConfig googleMapsConfig) {
		super(googleMapsConfig);
	}

	public List<GeocodingResult> getGeoByLatLon(double lat, double lon) throws GoogleMapsException {
		try {
			return Arrays.asList(getGeoApiRequest().latlng(new LatLng(lat, lon)).await());
		} catch (ApiException | InterruptedException | IOException e) {
			throw new GoogleMapsException("Exception occurred on getGeoByLatLon", e);
		}
	}

	public List<GeocodingResult> getGeoByAddress(String address) throws GoogleMapsException {
		try {
			return Arrays.asList(getGeoApiRequest().address(address).await());
		} catch (ApiException | InterruptedException | IOException e) {
			throw new GoogleMapsException("Exception occurred on getGeoByAddress", e);
		}
	}

	public List<GeocodingResult> getGeoByPostalCodeAndCountry(String postalCode, String country) throws GoogleMapsException {
		try {
			return Arrays.asList(getGeoApiRequest().components(ComponentFilter.postalCode(postalCode), ComponentFilter.country(country)).await());
		} catch (ApiException | InterruptedException | IOException e) {
			throw new GoogleMapsException("Exception occurred on getGeoByPostalCodeAndCountry", e);
		}
	}

	public List<GeocodingResult> getGeoByCityAndAddress(String city, String address) throws GoogleMapsException {
		try {
			return Arrays.asList(getGeoApiRequest().address(StringUtils.join(Arrays.asList(city, address), ',')).await());
		} catch (ApiException | InterruptedException | IOException e) {
			throw new GoogleMapsException("Exception occurred on getGeoByCityAndAddress", e);
		}
	}
}

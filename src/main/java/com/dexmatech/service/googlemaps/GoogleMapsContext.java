package com.dexmatech.service.googlemaps;

import com.dexmatech.config.GoogleMapsConfig;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class GoogleMapsContext {

	private GeoApiContext context;

	@Getter
	private final GoogleMapsConfig googleMapsConfig;

	@Autowired
	public GoogleMapsContext(GoogleMapsConfig googleMapsConfig) {
		this.googleMapsConfig = googleMapsConfig;
	}

	@PostConstruct
	public void init() {
		this.context = new GeoApiContext.Builder()
				.apiKey(getGoogleMapsConfig().getKey())
				.build();
	}

	GeocodingApiRequest getGeoApiRequest() {
		return GeocodingApi.newRequest(context);
	}
}

package com.dexmatech.mapper;

import com.dexmatech.domain.response.Direction;
import com.dexmatech.domain.response.GeoLocation;
import com.dexmatech.domain.response.GeoPoint;
import com.dexmatech.domain.response.Location;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class GeocodingResultMapper {

	public static GeoLocation map(final Optional<GeocodingResult> geocodingOptional) {
		if(geocodingOptional.isPresent()) {
			final GeocodingResult geocodingResult = geocodingOptional.get();
			return GeoLocation.builder()
					.geoPoint(buidlGeoPoint(geocodingResult.geometry.location))
					.zipCode(buildLocationFromTypes(geocodingResult.addressComponents, AddressComponentType.POSTAL_CODE).getShortName())
					.direction(buildDirectionFromTypes(geocodingResult.addressComponents))
					.city(buildLocationFromTypes(geocodingResult.addressComponents, AddressComponentType.LOCALITY))
					.country(buildLocationFromTypes(geocodingResult.addressComponents, AddressComponentType.COUNTRY))
					.build();
		} else {
			return GeoLocation.builder().build();
		}
	}

	private static GeoPoint buidlGeoPoint(LatLng location) {
		return new GeoPoint(location.lat, location.lng);
	}

	private static Location buildLocationFromTypes(AddressComponent[] addressComponents, AddressComponentType type) {
		final AddressComponent addressComponent = filterAddressByType(addressComponents, type);
		return new Location(addressComponent.shortName, addressComponent.longName);
	}

	private static Direction buildDirectionFromTypes(AddressComponent[] addressComponents) {
		final AddressComponent address = filterAddressByType(addressComponents, AddressComponentType.ROUTE);
		final AddressComponent number = filterAddressByType(addressComponents, AddressComponentType.STREET_NUMBER);
		return new Direction(address.shortName, Integer.valueOf(Optional.ofNullable(number.shortName).orElse("0")));
	}

	private static AddressComponent filterAddressByType(AddressComponent[] addressComponents, AddressComponentType type) {
		return Stream.of(addressComponents)
					.filter(r -> r.types != null && !Arrays.asList(r.types).isEmpty())
					.filter(r -> Stream.of(r.types).filter(Objects::nonNull).anyMatch(p -> p.equals(type)))
					.findFirst()
					.orElse(new AddressComponent());
	}
}

package com.dexmatech.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class GeoLocation implements Serializable {

	private GeoPoint geoPoint;
	private String zipCode;
	private Direction direction;
	private Location city;
	private Location country;

	@Override
	public boolean equals(Object g) {
		if(!(g instanceof GeoLocation)) {
			return false;
		}

		final GeoLocation compared = (GeoLocation) g;
		return compared.city.getShortName().equals(this.city.getShortName())
				&& compared.country.getLongName().equals(this.country.getLongName())
				&& compared.direction.equals(this.direction)
				&& compared.zipCode.equals(this.zipCode)
				&& compared.geoPoint.equals(this.geoPoint);
	}
}

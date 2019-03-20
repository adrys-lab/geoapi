package com.dexmatech.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class GeoPoint implements Serializable {
	private double latitude;
	private double longitude;

	@Override
	public boolean equals(Object g) {
		if(!(g instanceof GeoPoint)) {
			return false;
		}
		final GeoPoint compared = (GeoPoint) g;
		return compared.latitude == this.latitude && compared.longitude == this.longitude;
	}
}

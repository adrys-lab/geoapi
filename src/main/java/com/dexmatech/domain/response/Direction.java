package com.dexmatech.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Direction {

	private String street;
	private int number;

	@Override
	public boolean equals(Object g) {
		if(!(g instanceof Direction)) {
			return false;
		}
		final Direction compared = (Direction) g;
		return compared.street.equals(this.street) && compared.number == this.number;
	}
}

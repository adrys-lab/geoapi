package com.dexmatech.service.geonames;

import com.dexmatech.config.GeoNamesConfig;
import lombok.Getter;
import org.geonames.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class GeoNamesContext {

	@Getter
	private final GeoNamesConfig geoNamesConfig;

	@Autowired
	public GeoNamesContext(GeoNamesConfig geoNamesConfig) {
		this.geoNamesConfig = geoNamesConfig;
	}

	@PostConstruct
	public void init() {
		WebService.setUserName(getGeoNamesConfig().getUsername());
	}
}

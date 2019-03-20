package com.dexmatech.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix="geoapi.googlemaps")
public class GoogleMapsConfig {

    private String url;
    private String key;
}

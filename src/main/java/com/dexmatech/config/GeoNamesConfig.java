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
@ConfigurationProperties(prefix="geoapi.geonames")
public class GeoNamesConfig {

    private String username;
}

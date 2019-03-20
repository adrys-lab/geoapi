package com.dexmatech.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
class GsonConfiguration {

	/*
	 * Configure Gson as default Serializer/Deserializer instead of Jackson
	 */
	@Bean(value = "HttpMessageConverters")
	public HttpMessageConverters gson() {
		final Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		final GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
		messageConverters.add(gsonHttpMessageConverter);
		return new HttpMessageConverters(false, messageConverters);
	}
}
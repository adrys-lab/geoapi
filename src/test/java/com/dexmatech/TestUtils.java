package com.dexmatech;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.maps.model.GeocodingResult;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

class TestUtils {

	static <T> T readFromJson(final String resourcePath, Type type) throws FileNotFoundException {
		return new GsonBuilder().create().fromJson(TestUtils.getJsonReader(resourcePath), type);
	}

	static class TypeTokenListGeocodingResult extends TypeToken<List<GeocodingResult>> {
	}

	private static JsonReader getJsonReader(final String resourcePath) throws FileNotFoundException {
		return new JsonReader(new FileReader(TestUtils.class.getResource(resourcePath).getPath()));
	}
}

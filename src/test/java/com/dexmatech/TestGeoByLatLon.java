package com.dexmatech;

import com.dexmatech.controller.GeoDataController;
import com.dexmatech.domain.exception.GoogleMapsException;
import com.dexmatech.domain.response.GeoLocation;
import com.dexmatech.service.geonames.GeoNamesService;
import com.dexmatech.service.googlemaps.GoogleMapsService;
import com.google.maps.model.GeocodingResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;

public class TestGeoByLatLon {

    @Mock
    private GoogleMapsService googleMapsService;
    @Mock
    private GeoNamesService geoNamesService;

    private GeoDataController geoDataController;

    private final double lat = 41.3985177;
    private final double lon = 2.1725793;

    @Before
    public void init() throws GoogleMapsException, FileNotFoundException {
        MockitoAnnotations.initMocks(this);
        geoDataController = new GeoDataController(googleMapsService, geoNamesService);
        final List<GeocodingResult> geocodingResults = TestUtils.readFromJson("/geoByLatLonGoogleMapsResponse.json",
                new TestUtils.TypeTokenListGeocodingResult().getType());
        Mockito.when(googleMapsService.getGeoByLatLon(lat, lon)).thenReturn(geocodingResults);
    }

    @Test
    public void exampleTest() throws Exception {
        ResponseEntity<GeoLocation> response = geoDataController.getGeoByLatLon(lat, lon);
        Mockito.verify(googleMapsService, Mockito.times(1)).getGeoByLatLon(lat, lon);
        Assert.assertNotNull(response);
        GeoLocation expectedGeoApiResult = TestUtils.readFromJson("/geoByLatLonResponse.json", GeoLocation.class);
        Assert.assertTrue(response.getBody().equals(expectedGeoApiResult));
    }
}

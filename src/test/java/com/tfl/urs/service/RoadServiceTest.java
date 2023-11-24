package com.tfl.urs.service;

import com.tfl.urs.pojo.RoadStatusResponse;
import com.tfl.urs.pojo.TflRoadStatusResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoadServiceTest {
    @MockBean
    private TFLRemoteWebService tflRemoteWebService;

    @Autowired
    RoadService roadService;

    @Test
    public void successfulResponseRoadStatus() throws Exception {
        when(tflRemoteWebService.getRoadStatus("A2")).thenReturn(getSuccessResponse());
        RoadStatusResponse response= roadService.getRoadStatus("A2");
        assertEquals(response.getResponse(),"The status of the A2 is Good and No Exceptional Delays");

    }

    @Test
    public void failureResponseRoadStatus() throws Exception {
        when(tflRemoteWebService.getRoadStatus("A233")).thenReturn(getFailResponse());
        RoadStatusResponse response= roadService.getRoadStatus("A233");
        assertEquals(response.getResponse(),"A233 is not a valid road.");
    }

    private Optional<TflRoadStatusResponse> getSuccessResponse() {
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setStatusSeverityDescription("No Exceptional Delays");
        response.setDisplayName("A2");
        response.setStatusSeverity("Good");
        return Optional.of(response);

    }

    private Optional<TflRoadStatusResponse> getFailResponse() {
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setHttpStatusCode("404");
        return Optional.of(response);

    }

}
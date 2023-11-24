package com.tfl.urs.service;


import com.tfl.urs.pojo.TflRoadStatusResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;



@RunWith(SpringRunner.class)
public class TFLRemoteWebserviceTest {

    RestTemplate restTemplate;

    MockRestServiceServer server;

    TFLRemoteWebService client;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        server = MockRestServiceServer.createServer(restTemplate);
        client = new TFLRemoteWebService("http://localhost:8080/Road/", restTemplate);
    }
    @Test
    public void remoteWSSuccessfulResponse() throws Exception {

        server.expect(requestTo("http://localhost:8080/Road/A2"))
                .andRespond(withSuccess(successfulMessageBody(), MediaType.APPLICATION_JSON));

        Optional<TflRoadStatusResponse> response= client.getRoadStatus("A2");

        assertEquals(response.get().getDisplayName(),"A2");

    }

    @Test
    public void remoteWSFailureResponse() throws Exception {

        server.expect(requestTo("http://localhost:8080/Road/A233"))
                .andRespond(withSuccess(failMessageBody(), MediaType.APPLICATION_JSON));

        server.expect(requestTo("http://localhost:8080/Road/A233"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                        .body(failMessageBody()));


        Optional<TflRoadStatusResponse> response= client.getRoadStatus("A233");

        assertEquals(response.get().getHttpStatusCode(),"404");

    }

    private String successfulMessageBody() {
            StringBuilder str = new StringBuilder();
            str.append("[");
            str.append("{");
            str.append("\"$type\": \"Tfl.Api.Presentation.Entities.RoadCorridor, Tfl.Api.Presentation.Entities\",");
            str.append("\"id\": \"a2\",");
            str.append("\"displayName\": \"A2\",");
            str.append("\"statusSeverity\": \"Good\",");
            str.append("\"statusSeverityDescription\": \"No Exceptional Delays\",");
            str.append("\"bounds\": \"[[-0.0857,51.44091],[0.17118,51.49438]]\",");
            str.append("\"envelope\": \"[[-0.0857,51.44091],[-0.0857,51.49438],[0.17118,51.49438],[0.17118,51.44091],[-0.0857,51.44091]]\",");
            str.append("\"url\": \"/Road/a2\"");
            str.append("}");
            str.append("]");
        return str.toString();

    }

    private String failMessageBody() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        str.append("{");
        str.append("\"$type\": \"Tfl.Api.Presentation.Entities.ApiError, Tfl.Api.Presentation.Entities\",");
        str.append("\"timestampUtc\": \"2023-11-23T10:31:04.9533682Z\",");
        str.append("\"exceptionType\": \"EntityNotFoundException\",");
        str.append("\"httpStatusCode\": 404,");
        str.append("\"httpStatus\": \"NotFound\",");
        str.append("\"relativeUri\": \"/Road/A233\",");
        str.append("\"message\": \"The following road id is not recognised: A233\"");
        str.append("}");
        str.append("]");
        return str.toString();

    }
}

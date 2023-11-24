package com.tfl.urs.exception;

import com.tfl.urs.pojo.TflRoadStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RoadNotFoundErrorHandler implements ResponseErrorHandler {




    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return hasError(response.getStatusCode());
    }

    protected boolean hasError(HttpStatus statusCode) {
        return (statusCode.is4xxClientError() || statusCode.is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        TflRoadStatusResponse errorObject = new TflRoadStatusResponse();
        errorObject.setHttpStatusCode("404");
        throw new RoadNotFoundException(ResponseEntity.status(response.getRawStatusCode()).headers(response.getHeaders()).body(errorObject));

    }


}
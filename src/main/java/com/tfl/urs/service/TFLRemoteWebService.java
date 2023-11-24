package com.tfl.urs.service;


import com.tfl.urs.exception.RoadNotFoundException;
import com.tfl.urs.pojo.TflRoadStatusResponse;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;
import java.util.Optional;

@Service
public class TFLRemoteWebService {


    String tflUrl;

    RestTemplate restTemplate;


  public TFLRemoteWebService(@Value("${tfl.url}") String tflUrl, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.tflUrl = tflUrl;
  }

    public Optional<TflRoadStatusResponse> getRoadStatus(String roadId) {

      try {
          String resourceUrl
                  = tflUrl + roadId;
          ResponseEntity<TflRoadStatusResponse[]> response
                  = restTemplate.getForEntity(resourceUrl, TflRoadStatusResponse[].class);

          if (response.hasBody()) {
              return Optional.of((Objects.requireNonNull(response.getBody())[0]));
          } else {
              return Optional.of(new TflRoadStatusResponse());
          }
      }catch(RoadNotFoundException e) {

          return Optional.of((Objects.requireNonNull(e.getErrorResponse().getBody())));

      }



      }




}

package com.tfl.urs.service;

import com.tfl.urs.pojo.RoadStatusResponse;
import com.tfl.urs.pojo.TflRoadStatusResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoadService {


    TFLRemoteWebService tflRemoteWebService;

    public RoadService(TFLRemoteWebService tflRemoteWebService) {
        this.tflRemoteWebService = tflRemoteWebService;
    }
    public RoadStatusResponse getRoadStatus(String roadId) throws Exception {

            Optional<TflRoadStatusResponse> response = tflRemoteWebService.getRoadStatus(roadId);
            return mapTflResponseToRoadStatus(response,roadId);
    }


     private RoadStatusResponse mapTflResponseToRoadStatus(Optional<TflRoadStatusResponse> response, String roadId) {

         TflRoadStatusResponse tflRoadStatusResponse = response.get();
         RoadStatusResponse roadStatusResponse = new RoadStatusResponse();
         if (tflRoadStatusResponse.getHttpStatusCode() != null &&
                 tflRoadStatusResponse.getHttpStatusCode().equalsIgnoreCase("404"))
         {
             StringBuilder str = new StringBuilder();
             str.append(roadId);
             str.append(" is not a valid road.");
             roadStatusResponse.setResponse(str.toString());
         }else {
             StringBuilder str = new StringBuilder();
             str.append("The status of the ");
             str.append(tflRoadStatusResponse.getDisplayName());
             str.append(" is ");
             str.append(tflRoadStatusResponse.getStatusSeverity());
             str.append(" and ");
             str.append(tflRoadStatusResponse.getStatusSeverityDescription());
             roadStatusResponse.setResponse(str.toString());

         }
         return roadStatusResponse;
        }



}

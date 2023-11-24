package com.tfl.urs.exception;

import com.tfl.urs.pojo.TflRoadStatusResponse;
import org.springframework.http.ResponseEntity;

public class RoadNotFoundException extends RuntimeException {

        private ResponseEntity<TflRoadStatusResponse> errorResponse;
        public RoadNotFoundException(ResponseEntity<TflRoadStatusResponse> errorResponse) {
            this.errorResponse = errorResponse;
        }
        public ResponseEntity<TflRoadStatusResponse> getErrorResponse() {
            return errorResponse;
        }


}

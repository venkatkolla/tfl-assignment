package com.tfl.urs.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class TflRoadStatusResponse {
    @JsonIgnore
    String $type;
    @JsonIgnore
    String timestampUtc;
    String id;
    String displayName;
    String statusSeverity;
    String statusSeverityDescription;
    String bounds;
    String envelope;
    String url;
    String exceptionType;
    String httpStatusCode;
    String httpStatus;
    String relativeUri;
    String message;



}

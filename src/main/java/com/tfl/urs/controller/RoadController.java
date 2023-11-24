package com.tfl.urs.controller;

import com.tfl.urs.pojo.RoadStatusResponse;
import com.tfl.urs.service.RoadService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Road")
public class RoadController {


    RoadService roadService;
    public RoadController(RoadService roadService) {
        this.roadService = roadService;
    }

    @GetMapping("/{roadId}")
    public RoadStatusResponse getRoadStatus(
            @PathVariable ("roadId") String roadId)  throws Exception {
        RoadStatusResponse roadStatusResponse = roadService.getRoadStatus(
                roadId);
        return roadStatusResponse;
    }
}

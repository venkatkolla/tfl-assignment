package com.tfl.urs.controller;


import com.tfl.urs.pojo.RoadStatusResponse;
import com.tfl.urs.service.RoadService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RoadController.class)
public class RoadControllerTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private RoadService roadService;


    @Test
    public void getRoadStatusSuccess() throws Exception {
            String roadId = "A2";
            RoadStatusResponse response = getRoadStatusSuccessResponse();
            when(roadService.getRoadStatus(roadId)).thenReturn(response);

        MvcResult result= mvc.perform(get("/Road/A2")
                            )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.response").value("The status of the A2 is Good and No Exceptional Delays"))
                    .andReturn();
    }

    @Test
    public void getRoadFailStatus() throws Exception {
        String roadId = "A233";
        RoadStatusResponse response = getRoadStatusFailResponse();
        when(roadService.getRoadStatus(roadId)).thenReturn(response);

        MvcResult result= mvc.perform(get("/Road/A233")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("A233 is not a valid road."))
                .andReturn();
    }


    private RoadStatusResponse getRoadStatusSuccessResponse() {
        RoadStatusResponse response = new RoadStatusResponse();
        response.setResponse("The status of the A2 is Good and No Exceptional Delays");
      return  response;
    }

    private RoadStatusResponse getRoadStatusFailResponse() {
        RoadStatusResponse response = new RoadStatusResponse();
        response.setResponse("A233 is not a valid road.");
        return  response;
    }
}

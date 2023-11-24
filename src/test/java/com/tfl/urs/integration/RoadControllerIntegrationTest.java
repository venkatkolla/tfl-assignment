package com.tfl.urs.integration;


import com.tfl.urs.pojo.TflRoadStatusResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RoadControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    RestTemplate restTemplate;

    @Test
    public void givenValidRoad_WhenClientRun_ThenFullMessage() throws Exception {
        String roadId = "A2";
        String tflUrl = "https://api.tfl.gov.uk/Road/A2";
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setDisplayName("A2");
        response.setStatusSeverity("Good");
        response.setStatusSeverityDescription("No Exceptional Delays");

        TflRoadStatusResponse[] responseArr = {response};
        when(restTemplate.getForEntity(tflUrl, TflRoadStatusResponse[].class))
                .thenReturn(new ResponseEntity(responseArr, HttpStatus.OK));

        MvcResult result= mvc.perform(get("/Road/A2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("The status of the A2 is Good and No Exceptional Delays"))
                .andReturn();
    }

    @Test
    public void givenValidRoad_WhenClientRun_ThenDisPlayName() throws Exception {
        String roadId = "A2";
        String tflUrl = "https://api.tfl.gov.uk/Road/A2";
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setDisplayName("A2");
        response.setStatusSeverity("Good");
        response.setStatusSeverityDescription("No Exceptional Delays");

        TflRoadStatusResponse[] responseArr = {response};
        when(restTemplate.getForEntity(tflUrl, TflRoadStatusResponse[].class))
          .thenReturn(new ResponseEntity(responseArr, HttpStatus.OK));

        MvcResult result= mvc.perform(get("/Road/A2")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("A2")))
                .andReturn();
    }

    @Test
    public void givenValidRoad_WhenClientRun_ThenStatusSeverity() throws Exception {
        String roadId = "A2";
        String tflUrl = "https://api.tfl.gov.uk/Road/A2";
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setDisplayName("A2");
        response.setStatusSeverity("Good");
        response.setStatusSeverityDescription("No Exceptional Delays");

        TflRoadStatusResponse[] responseArr = {response};
        when(restTemplate.getForEntity(tflUrl, TflRoadStatusResponse[].class))
                .thenReturn(new ResponseEntity(responseArr, HttpStatus.OK));

        MvcResult result= mvc.perform(get("/Road/A2")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Good")))
                .andReturn();
    }

    @Test
    public void givenValidRoad_WhenClientRun_ThenStatusSeverityDesc() throws Exception {
        String roadId = "A2";
        String tflUrl = "https://api.tfl.gov.uk/Road/A2";
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setDisplayName("A2");
        response.setStatusSeverity("Good");
        response.setStatusSeverityDescription("No Exceptional Delays");

        TflRoadStatusResponse[] responseArr = {response};
        when(restTemplate.getForEntity(tflUrl, TflRoadStatusResponse[].class))
                .thenReturn(new ResponseEntity(responseArr, HttpStatus.OK));

        MvcResult result= mvc.perform(get("/Road/A2")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No Exceptional Delays")))
                .andReturn();
    }

    @Test
    public void givenInValidRoad_WhenClientRun_ThenErrorMessage() throws Exception {
        String roadId = "A233";
        String tflUrl = "https://api.tfl.gov.uk/Road/A233";
        TflRoadStatusResponse response = new TflRoadStatusResponse();
        response.setHttpStatusCode("404");

        TflRoadStatusResponse[] responseArr = {response};
        when(restTemplate.getForEntity(tflUrl, TflRoadStatusResponse[].class))
                .thenReturn(new ResponseEntity(responseArr, HttpStatus.OK));

        MvcResult result= mvc.perform(get("/Road/A233")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("A233 is not a valid road."))
                .andReturn();
    }



}

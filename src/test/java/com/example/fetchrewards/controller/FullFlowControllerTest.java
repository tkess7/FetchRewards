package com.example.fetchrewards.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullFlowControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void spendPointsSuccessTest() throws JSONException {
        String createTransactionUrl = "http://localhost:" + port + "/transaction";

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        String createTransactionInput = "{ \"payer\": \"DANNON\", \"points\": 1000, \"timestamp\": \"2020-11-02T14:00:00Z\" }";

        HttpEntity<String> createTransactionRequest = new HttpEntity<>(createTransactionInput, headers);

        ResponseEntity<String> createTransactionResponse = restTemplate.postForEntity(createTransactionUrl, createTransactionRequest, String.class);

        assertEquals(createTransactionResponse.getStatusCode(), HttpStatus.OK);

        String spendPointsUrl = "http://localhost:" + port + "/spendPoints";

        String spendPointsInput = "{ \"points\": 555 }";
        HttpEntity<String> spendPointsRequest = new HttpEntity<>(spendPointsInput, headers);

        ResponseEntity<String> spendPointsResponseEntity = restTemplate.postForEntity(spendPointsUrl, spendPointsRequest, String.class);

        assertEquals(HttpStatus.OK, spendPointsResponseEntity.getStatusCode());

        JSONArray spendPointsJsonArray = new JSONArray(spendPointsResponseEntity.getBody());
        JSONObject spendPointsJsonResponse = (JSONObject) spendPointsJsonArray.get(0);

        assertEquals("DANNON", spendPointsJsonResponse.getString("payer"));
        assertEquals(-555, spendPointsJsonResponse.getInt("points"));

        String pointBalanceUrl = "http://localhost:" + port + "/pointBalance";

        ResponseEntity<String> pointBalanceResponseEntity = restTemplate.getForEntity(pointBalanceUrl, String.class);

        assertEquals(HttpStatus.OK, pointBalanceResponseEntity.getStatusCode());

        JSONArray pointBalanceJsonArray = new JSONArray(pointBalanceResponseEntity.getBody());
        JSONObject pointBalanceJsonResponse = (JSONObject) pointBalanceJsonArray.get(0);

        assertEquals("DANNON", pointBalanceJsonResponse.getString("payer"));
        assertEquals(445, pointBalanceJsonResponse.getInt("points"));
    }
}

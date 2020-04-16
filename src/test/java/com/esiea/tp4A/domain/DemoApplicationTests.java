package com.esiea.tp4A.domain;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import Controller.ApiController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	void apiRegisterPlayerTest() throws URISyntaxException {
        ResponseEntity<String> result = restTemplate
            .exchange("/api/player/test_player_reg", HttpMethod.POST, null, String.class);

        ResponseEntity<String> fail_result = restTemplate
            .exchange("/api/player/test_player_reg", HttpMethod.POST, null, String.class);

        // Verify request gave 409
        Assertions.assertEquals(HttpStatus.CONFLICT, fail_result.getStatusCode());


        // Verify request succeed
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
	}

	@Test
    void apiPlayerStatusTest() throws URISyntaxException {
        ResponseEntity<String> regResult = restTemplate
            .exchange("/api/player/test_player_status", HttpMethod.POST, null, String.class);
        ResponseEntity<String> result = restTemplate
            .exchange("/api/player/test_player_status", HttpMethod.GET, null, String.class);


        //Verify request succeed
        Assertions.assertEquals(HttpStatus.CREATED, regResult.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
    }

    @Test
    void apiPlayerMoveTest() throws URISyntaxException {
        ResponseEntity<String> regResult = restTemplate
            .exchange("/api/player/test_player_move", HttpMethod.POST, null, String.class);
        ResponseEntity<String> result = restTemplate
            .exchange("/api/player/test_player_move", HttpMethod.GET, null, String.class);

        //Verify request succeed
        Assertions.assertEquals(HttpStatus.CREATED, regResult.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
    }
    
    @Test
    void apiSendHello() throws URISyntaxException {
        ResponseEntity<String> result = restTemplate
                .exchange("/hello", HttpMethod.GET, null, String.class);
    	
         Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
     	 Map<String, String> res = new HashMap<>();
    	 res.put("status", HttpStatus.OK.toString());
    	 res.put("message", "Hello World!");
         Assertions.assertEquals(new JSONObject(res).toString(), result.getBody());
    }
    
    @Test
    void apiExecuteCommandFailure() {
    	RestTemplate patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        
    	restTemplate.exchange("/api/player/toto", HttpMethod.POST, null, String.class);
        ResponseEntity<String> result = patchRestTemplate
                .exchange("/api/player/toto/f", HttpMethod.PATCH, null, String.class);
    	
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}

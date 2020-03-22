package com.esiea.tp4A.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Controller.ApiController;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.print.URIException;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	void registerPlayerTest() throws URISyntaxException {

        ResponseEntity<String> result = restTemplate
            .exchange("/api/player/test_player_reg", HttpMethod.POST, null, String.class);

        ResponseEntity<String> fail_result = restTemplate
            .exchange("/api/player/test_player_reg", HttpMethod.POST, null, String.class);

        // Verify request gave 409
        Assertions.assertEquals(409, fail_result.getStatusCodeValue());


        // Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
	}

	@Test
    void playerStatusTest() throws URISyntaxException {

        ResponseEntity<String> regResult = restTemplate
            .exchange("/api/player/test_player_status", HttpMethod.POST, null, String.class);
        ResponseEntity<String> result = restTemplate
            .exchange("/api/player/test_player_status", HttpMethod.GET, null, String.class);


        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
    }

    @Test
    void playerMoveTest() throws URISyntaxException {

        ResponseEntity<String> regResult = restTemplate
            .exchange("/api/player/test_player_move", HttpMethod.POST, null, String.class);
        ResponseEntity<String> result = restTemplate
            .exchange("/api/player/test_player_move", HttpMethod.GET, null, String.class);

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
    }

}

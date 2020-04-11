package com.esiea.tp4A.domain;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Assertions.assertEquals(409, fail_result.getStatusCodeValue());


        // Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());
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
        Assertions.assertEquals(200, result.getStatusCodeValue());
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
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(true, result.getBody().contains("name"));
        Assertions.assertEquals(true, result.getBody().contains("local-map"));
        Assertions.assertEquals(true, result.getBody().contains("alive"));
        Assertions.assertEquals(true, result.getBody().contains("direction"));
    }
    
    @Test
    void apiSendHello() throws URISyntaxException {
        ResponseEntity<String> result = restTemplate
                .exchange("/hello", HttpMethod.GET, null, String.class);
    	
         Assertions.assertEquals(200, result.getStatusCodeValue());
         Assertions.assertEquals("Hello World!", result.getBody());
    }
    
    @Test
    void apiRoverPosition() throws URISyntaxException {
        ResponseEntity<String> result = restTemplate
                .exchange("/rover/position?roverId=1", HttpMethod.GET, null, String.class);
    	
         Assertions.assertEquals(200, result.getStatusCodeValue());
         Assertions.assertEquals(HttpStatus.OK + "Rover Position", result.getBody());
    }
    
    @Test 
    void apiRoverMovement() throws URISyntaxException {
        ResponseEntity<String> result = restTemplate
                .exchange("/rover/move?command=f", HttpMethod.GET, null, String.class);
    	
         Assertions.assertEquals(200, result.getStatusCodeValue());
         Assertions.assertEquals(HttpStatus.OK + "Rover has moved with the command : f", result.getBody());
    }
    
    @Test 
    void apiRoverStatus() throws URISyntaxException {
        ResponseEntity<String> result = restTemplate
                .exchange("/rover/alive?roverId=1", HttpMethod.GET, null, String.class);
    	
         Assertions.assertEquals(200, result.getStatusCodeValue());
         Assertions.assertEquals("true", result.getBody());
    }
    
    @Test
    void apiExecuteCommandFailure() {
    	restTemplate.exchange("/api/player/toto", HttpMethod.POST, null, String.class);
        ResponseEntity<String> result = restTemplate
                .exchange("/api/player/toto/f", HttpMethod.GET, null, String.class);
    	
         Assertions.assertEquals(200, result.getStatusCodeValue());
    }
}

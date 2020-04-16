package Controller;

import Gestionnaire.GestionnaireApiControllerImpl;
import Gestionnaire.MarsRoverImpl;
import Gestionnaire.PlanetMapImpl;
import com.esiea.tp4A.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
public class ApiController extends GestionnaireApiControllerImpl {

    public static void main(String[] args) {
        SpringApplication.run(ApiController.class, args);
    }

    private final Map<String, MarsRoverImpl> playerNameRoverMap = new HashMap<>();

    @GetMapping("/hello")
    public ResponseEntity<?> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    	Map<String, String> res = new HashMap<>();
    	res.put("status", HttpStatus.OK.toString());
    	res.put("message", String.format("Hello %s!", name));
    	return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/api/position/{player_name}")
    @ResponseBody
    public ResponseEntity<?> getPositionOfRover(@PathVariable("player_name") String name) {
    	MarsRoverImpl marsRover = playerNameRoverMap.get(name);
    	if (marsRover == null) {
    		Map<String, String> res = new HashMap<>();
        	res.put("status", HttpStatus.NOT_FOUND.toString());
        	res.put("message", "Player does not exist");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    	}
    	Map<String, String> res = new HashMap<>();
    	res.put("status", HttpStatus.OK.toString());
    	res.put("player_name", name);
    	res.put("x", Integer.toString(marsRover.getPos().getX()));
    	res.put("y", Integer.toString(marsRover.getPos().getY()));
    	return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    
    /**
     * @param name Name of the player to register
     * @return the status of the new player, HTTP 201 if success - 409 if player exists
     */
    @PostMapping("/api/player/{player_name}")
    public ResponseEntity<?> generatePlayer(@PathVariable("player_name") String name) {
        if (playerNameRoverMap.containsKey(name)) {
        	Map<String, String> res = new HashMap<>();
        	res.put("status", HttpStatus.CONFLICT.toString());
        	res.put("message", "Specified player name is already in use");
        	return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        Random random = new Random();
        Direction dir = getDirection(random);
        MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(random.nextInt()%10, random.nextInt()%10, dir));
        Set<Position> obstacles = new HashSet<>();
        PlanetMapImpl planetMap = new PlanetMapImpl();
        generateObstaclePosition(obstacles, planetMap.getPlanetMapSize());
        planetMap.setObstacles(obstacles);
        marsRover.updateMap(planetMap);
        marsRover.configureLaserRange(5);

        Map<String, Object> response = getStateResponse(name, marsRover, planetMap);
        playerNameRoverMap.put(name, marsRover);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/api/player/{player_name}")
    public ResponseEntity<?> playerStatus(@PathVariable("player_name") String name) {
    	MarsRoverImpl marsRover = playerNameRoverMap.get(name);
    	if (marsRover == null) {
    		Map<String, String> res = new HashMap<>();
        	res.put("status", HttpStatus.NOT_FOUND.toString());
        	res.put("message", "Player does not exist");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    	}
    	
        return ResponseEntity.status(HttpStatus.OK).body(getStateResponse(name, marsRover, marsRover.getPlanetMap()));
    }

    /**
     *
     * @return HTTP 404 if the player is not found
     * @return HTTP 200 with the new status if player is found, after using commands
     */
    @PatchMapping("/api/player/{name}/{command}")
    public ResponseEntity<?> executeCommand(@PathVariable("name") String name, @PathVariable("command") String command) {
        MarsRoverImpl marsRover = playerNameRoverMap.get(name);
        if (marsRover == null) {
        	Map<String, String> res = new HashMap<>();
        	res.put("status", HttpStatus.NOT_FOUND.toString());
        	res.put("message", "Player does not exist");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        	
        marsRover.move(command);
        return ResponseEntity.status(HttpStatus.OK).body(getStateResponse(name, marsRover, marsRover.getPlanetMap()));
    }
}

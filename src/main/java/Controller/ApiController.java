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
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping(path = "/rover/position" , method = RequestMethod.GET)
    @ResponseBody
    public String getPositionOfRover(Integer roverId) {
        return (HttpStatus.OK + "Rover Position");
    }

    @RequestMapping(path = "/rover/move" , method = RequestMethod.GET)
    @ResponseBody
    public String handlePositionOfRover(String command) {
        return (HttpStatus.OK + "Rover has moved with the command : " + command);
    }

    @RequestMapping(path = "/rover/alive" , method = RequestMethod.GET)
    @ResponseBody
    public Boolean getStatutRover(Integer roverId) {
       return true;
    }

    @GetMapping("/api/player/{player_name}")
    public ResponseEntity<?> playerStatus(@PathVariable("player_name") String name) {
    	MarsRoverImpl marsRover = playerNameRoverMap.get(name);
        Map<String, Object> response = getStateResponse(name, marsRover, marsRover.getPlanetMap());
        return ResponseEntity.status(200).body(response);
    }

    /**
     *
     * @return HTTP 404 if the player is not found
     * @return HTTP 200 with the new status if player is found, after using commands
     */
    @GetMapping("/api/player/{name}/{command}")
    public ResponseEntity<?> executeCommand(@PathVariable("name") String name, @PathVariable("command") String command) {
        if(name.isEmpty()) return ResponseEntity.status(404).body("404 : Player does not exist");

        MarsRoverImpl marsRover = playerNameRoverMap.get(name);
        marsRover.move(command);

        Map<String, Object> response = getStateResponse(name, marsRover, marsRover.getPlanetMap());

        return ResponseEntity.status(200).body(response);
    }

    /**
     * @param name Name of the player to register
     * @return the status of the new player, http 201 if success - 409 if player exists
     */
    @PostMapping("/api/player/{player_name}")
    public ResponseEntity<?> generatePlayer(@PathVariable("player_name") String name) {
        if(playerNameRoverMap.containsKey(name))
            return ResponseEntity.status(409).body("Player name is not available");

        Random random = new Random();

        Direction dir = getDirection(random);

        MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(random.nextInt()%10, random.nextInt()%10, dir));

        Set<Position> obstacles = new HashSet<>();
        PlanetMapImpl planetMap = new PlanetMapImpl();

        int size = planetMap.getPlanetMapSize();

        getRandomObstaclePosition(random, obstacles, size);

        planetMap.setObstacles(obstacles);
        marsRover.updateMap(planetMap);
        marsRover.configureLaserRange(5);

        Map<String, Object> response = getStateResponse(name, marsRover, planetMap);
        playerNameRoverMap.put(name, marsRover);

        return ResponseEntity.status(201).body(response);
    }
}

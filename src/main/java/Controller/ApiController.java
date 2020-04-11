package Controller;

import com.esiea.tp4A.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
public class ApiController {

    public static void main(String[] args) {
        SpringApplication.run(ApiController.class, args);
    }


    private Map<String, MarsRover> playerNameRoverMap = new HashMap<>();

    ApiController() {

    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    /**
     * GET : Return rover position by roverId
     * @param roverId
     * @return
     */
    @RequestMapping(path = "/rover/position" , method = RequestMethod.GET)
    @ResponseBody
    public String getPositionOfRover(Integer roverId) {
        return (HttpStatus.OK + "Rover Position");
    }

    /**
     * GET : Rover move by command
     * @param command
     * @return
     */
    @RequestMapping(path = "/rover/move" , method = RequestMethod.GET)
    @ResponseBody
    public String handlePositionOfRover(String command) {
        return (HttpStatus.OK + "Rover has moved with the command : " + command);
    }

    /**
     * GET : Rover statut Alive or Dead
     * @return
     */
    @RequestMapping(path = "/rover/alive" , method = RequestMethod.GET)
    @ResponseBody
    public Boolean getStatutRover(Integer roverId) {
       return true;
    }

    /**
     *
     * @param name Name of the player
     * @return response 200 with body containing state if player is registered
     */
    @GetMapping("/api/player/{player_name}")
    public ResponseEntity<?> playerStatus(@PathVariable("player_name") String name) {
        MarsRover marsRover = playerNameRoverMap.get(name);

        Map<String, Object> response = getStateResponse(name, marsRover, marsRover.getPlanetMap());

        return ResponseEntity.status(200).body(response);
    }

    /**
     *
     * @param name The name of the player
     * @param command The command to be executed
     * @return HTTP 404 if the player is not found
     * @return HTTP 200 with the new status if player is found, after using commands
     */
    @GetMapping("/api/player/{name}/{command}")
    public ResponseEntity<?> executeCommand(@PathVariable("name") String name, @PathVariable("command") String command) {
        if(name.isEmpty()) {
            return ResponseEntity.status(404).body("404 : Player does not exist");
        }

        MarsRover marsRover = playerNameRoverMap.get(name);
        marsRover.move(command);

        Map<String, Object> response = getStateResponse(name, marsRover, marsRover.getPlanetMap());

        return ResponseEntity.status(200).body(response);
    }

    /**
     *
     * @param name Name of the player to register
     * @return the status of the new player, http 201 if success - 409 if player exists
     */
    @PostMapping("/api/player/{player_name}")
    public ResponseEntity<?> generatePlayer(@PathVariable("player_name") String name) {

        if(playerNameRoverMap.containsKey(name)){
            return ResponseEntity.status(409).body("Player name is not available");
        }

        Random random = new Random();

        Direction dir;
        switch(random.nextInt()%4) {
            case 0:
                dir = Direction.NORTH;
                break;
            case 1:
                dir = Direction.EAST;
                break;
            case 2:
                dir = Direction.WEST;
                break;
            default:
                dir = Direction.SOUTH;
        }

        MarsRover marsRover = new MarsRoverImpl().initialize(Position.of(random.nextInt()%10, random.nextInt()%10, dir));

        Set<Position> obstacles = new HashSet<>();
        PlanetMap planetMap = new PlanetMapImpl();

        int size = planetMap.getPlanetMapSize();

        for(int i = 0; i < 0.15*(size * size) ; i++){
            int x = random.nextInt()%size;
            int y = random.nextInt()%size;

            Position pos = Position.of(x, y, Direction.NORTH);

            obstacles.add(pos);
        }

        planetMap.setObstacles(obstacles);
        marsRover.updateMap(planetMap);
        marsRover.configureLaserRange(5);

        Map<String, Object> response = getStateResponse(name, marsRover, planetMap);

        playerNameRoverMap.put(name, marsRover);

        return ResponseEntity.status(201).body(response);

    }

    private Map<String, Object> getStateResponse(String name, MarsRover marsRover, PlanetMap planetMap) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> playerObj = new HashMap<>();
        Map<String, Object> positionObj = new HashMap<>();

        playerObj.put("name", name);
        playerObj.put("status", "alive");

        Position position = marsRover.getPos();

        positionObj.put("x", position.getX());
        positionObj.put("y", position.getY());
        positionObj.put("direction", position.getDirection().toString());
        playerObj.put("position", positionObj);

        playerObj.put("laser-range", marsRover.getLaserRange());
        response.put("player", playerObj);

        Map<String, Object> localMapObj = new HashMap<>();
        List<Map<String, Integer>> obstaclesList = new ArrayList<>();
        for(Position obsPos : planetMap.obstaclePositions()) {
            Map<String, Integer> obs = new HashMap<>();
            obs.put("x", obsPos.getX());
            obs.put("y", obsPos.getY());
            obstaclesList.add(obs);
        }
        localMapObj.put("obstacles", obstaclesList);

        List<Map<String, Object>> playersList = new ArrayList<>();
        localMapObj.put("players", playersList);

        response.put("local-map", localMapObj);

        return response;
    }

}

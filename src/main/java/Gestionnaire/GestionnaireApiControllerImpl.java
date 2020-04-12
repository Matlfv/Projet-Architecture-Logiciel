package Gestionnaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

public class GestionnaireApiControllerImpl {

    public Map<String, Object> getStateResponse(String name, MarsRoverImpl marsRover, PlanetMap planetMap) {
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

    public Direction getDirection(Random random) {
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
        return dir;
    }

    public void getRandomObstaclePosition(Random random, Set<Position> obstacles, int size) {
        for(int i = 0; i < 0.15*(size * size) ; i++){
            int x = random.nextInt()%size;
            int y = random.nextInt()%size;

            Position pos = Position.of(x, y, Direction.NORTH);

            obstacles.add(pos);
        }
    }
}

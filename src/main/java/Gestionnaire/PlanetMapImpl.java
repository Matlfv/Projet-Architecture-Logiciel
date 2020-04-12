package Gestionnaire;

import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.Set;

public class PlanetMapImpl implements PlanetMap {
	private final int MAP_SIZE = 100;
	private Set<Position> obstacles;
	
	/**
	 * PlanetMapImpl constructor
	 */
	public PlanetMapImpl(Set<Position> obstaclePositions) {
		this.obstacles = obstaclePositions;
	}

    /**
     * PlanetMapImpl constructor without obstacles
     */
    public PlanetMapImpl() {
//
    }
	
	/**
	 * Retrieves map size
	 * 
	 * @return Map size
	 */
	public int getPlanetMapSize() {
		return this.MAP_SIZE;
	}
	
	/**
	 * Retrieves all obstacles
	 * 
	 * @return Set<Position> of obstacles
	 */
	public Set<Position> obstaclePositions() {
		return this.obstacles;
	}
	
	public void setObstacles(Set<Position> obstaclePositions) {
		this.obstacles = obstaclePositions;
	}

	public void deleteObstacle(Position obstaclePosition) {
        obstacles.removeIf(position -> position.getX() == obstaclePosition.getX() && position.getY() == obstaclePosition.getY());
    }
}

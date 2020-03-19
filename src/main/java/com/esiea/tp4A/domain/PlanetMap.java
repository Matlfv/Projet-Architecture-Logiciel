package com.esiea.tp4A.domain;

import java.util.Set;

public interface PlanetMap {
	Set<Position> obstaclePositions();
	int getPlanetMapSize();
	void deleteObstacle(Position obstaclePosition);
	void setObstacles(Set<Position> obstacles);
}

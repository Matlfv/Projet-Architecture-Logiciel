package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

import Gestionnaire.MarsRoverImpl;
import Gestionnaire.PlanetMapImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {
	
	final MarsRoverImpl mr = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(0, 0, Direction.NORTH));
	final MarsRoverImpl mrIssue23 = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(4, 4, Direction.SOUTH));
	final MarsRoverImpl mrRightEast = (MarsRoverImpl) new MarsRoverImpl().
			initialize(Position.of(50, 0, Direction.EAST));
	final MarsRoverImpl mrRightWest = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(50, 0, Direction.WEST));
	final MarsRoverImpl mrLeftWest = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(-49, 0, Direction.WEST));
	final MarsRoverImpl mrLeftEast = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(-49, 0, Direction.EAST));
	final MarsRoverImpl mrTopNorth = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(0, 50, Direction.NORTH));
	final MarsRoverImpl mrTopSouth = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(0, 50, Direction.SOUTH));
	final MarsRoverImpl mrBottomSouth = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(0, -49, Direction.SOUTH));
	final MarsRoverImpl mrBottomNorth = (MarsRoverImpl) new MarsRoverImpl()
			.initialize(Position.of(0, -49, Direction.NORTH));

	@Test
	void testMoveForwardFromOrigin() {
		Assertions.assertThat(mr.move("f"))
			.as("Rover is moving forward (North) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 1, Direction.NORTH);
	}
	
	@Test
	void testMoveForwardEastFromRightEnd() {
		Assertions.assertThat(mrRightEast.move("f"))
			.as("Rover is moving forward (EAST) from right end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-49, 0, Direction.EAST);
	}
	
	@Test
	void testMoveForwardWestFromLeftEnd() {
		Assertions.assertThat(mrLeftWest.move("f"))
			.as("Rover is moving forward (WEST) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(50, 0, Direction.WEST);
	}
	
	@Test
	void testMoveForwardNorthFromTopEnd() {
		Assertions.assertThat(mrTopNorth.move("f"))
			.as("Rover is moving forward (NORTH) from top end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -49, Direction.NORTH);
	}
	
	@Test
	void testMoveForwardSouthFromLeftEnd() {
		Assertions.assertThat(mrBottomSouth.move("f"))
			.as("Rover is moving forward (SOUTH) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 50, Direction.SOUTH);
	}
		
	@Test
	void testMoveBackwardFromOrigin() {
		Assertions.assertThat(mr.move("b"))
			.as("Rover is moving backward (South) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -1, Direction.NORTH);
	}
	
	@Test
	void testMoveBackwardWestFromRightEnd() {
		Assertions.assertThat(mrRightWest.move("b"))
			.as("Rover is moving backward (WEST) from right end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-49, 0, Direction.WEST);
	}
		
	@Test
	void testMoveBackwardEastFromLeftEnd() {
		Assertions.assertThat(mrLeftEast.move("b"))
			.as("Rover is moving backward (EAST) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(50, 0, Direction.EAST);
	}
	
	@Test
	void testMoveBackwardSouthFromTopEnd() {
		Assertions.assertThat(mrTopSouth.move("b"))
			.as("Rover is moving backward (SOUTH) from top end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -49, Direction.SOUTH);
	}
		
	@Test
	void testMoveBackwardNorthFromLeftEnd() {
		Assertions.assertThat(mrBottomNorth.move("b"))
			.as("Rover is moving backward (NORTH) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 50, Direction.NORTH);
	}
	
	@Test
	void testMoveRight() {
		Assertions.assertThat(mr.move("r"))
			.as("Rover is moving right (East) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.EAST);
	}
	
	@Test
	void testMoveLeft() {
		Assertions.assertThat(mr.move("l"))
			.as("Rover is moving left (West)")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.WEST);
	}
	
	@Test
	void testMoveNowhere() {
		Assertions.assertThat(mr.move("mmmmm"))
			.as("Rover is not moving from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovesLeft() {
		Assertions.assertThat(mr.move("lffff"))
			.as("Rover moving 4 times to the left")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-4, 0, Direction.WEST);
	}
	
	@Test
	void testMultipleMovesRight() {
		Assertions.assertThat(mr.move("rffff"))
			.as("Rover moving 4 times to the right")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(4, 0, Direction.EAST);
	}
	
	@Test
	void testMultipleMovesForward() {
		Assertions.assertThat(mr.move("ffff"))
			.as("Rover moving 4 times forward")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 4, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovesBackward() {
		Assertions.assertThat(mr.move("bbbb"))
			.as("Rover moving 4 times backward")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -4, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovements1() {
		Assertions.assertThat(mr.move("bfbfbf"))
			.as("Rover back and forth 3 times to the origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovements2() {
		Assertions.assertThat(mr.move("frfrfrfr"))
			.as("Rover move in circle to the origin by rotating right")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovements3() {
		Assertions.assertThat(mr.move("flflflfl"))
			.as("Rover move in circle to the origin by rotating left")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMoveForwardFromOriginNorthWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.NORTH));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(0, 1, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("f"))
			.as("Rover is moving forward (North) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMoveForwardFromOriginSouthWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.SOUTH));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(0, -1, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("f"))
			.as("Rover is moving forward (South) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.SOUTH);
	}
	
	@Test
	void testMoveForwardFromOriginWestWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.WEST));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(-1, 0, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("f"))
			.as("Rover is moving forward (West) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.WEST);
	}
	
	@Test
	void testMoveForwardFromOriginEastWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.EAST));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(1, 0, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("f"))
			.as("Rover is moving forward (East) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.EAST);
	}
	
	@Test
	void testMoveDownwardFromOriginNorthWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.NORTH));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(0, -1, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("b"))
			.as("Rover is moving backward (North) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMoveDownwardFromOriginSouthWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.SOUTH));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(0, 1, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("b"))
			.as("Rover is moving backward (South) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.SOUTH);
	}
	
	@Test
	void testMoveDownwardFromOriginWestWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.WEST));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(1, 0, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("b"))
			.as("Rover is moving backward (West) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.WEST);
	}
	
	@Test
	void testMoveDownwardFromOriginEastWithObstacle() {
		MarsRoverImpl marsRover = (MarsRoverImpl) new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.EAST));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(-1, 0, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.move("b"))
			.as("Rover is moving backward (East) from origin with an obstacle")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.EAST);
	}

	@Test
    void testLaserFunction() {
	    MarsRover marsRover = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
	    marsRover.configureLaserRange(2);

        Set<Position> set = new HashSet<Position>();
        set.add(Position.of(0, 2, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(set));

        Assertions.assertThat(marsRover.move("sff"))
            .as("Rover is shooting a laser forward(North), and then moving forward")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0, 2, Direction.NORTH);

    }
	
	@Test
	void testIssue23() {
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(4, 5, Direction.NORTH));
		set.add(Position.of(5, 4, Direction.NORTH));
		mrIssue23.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(mrIssue23.move("bllfrb"))
			.as("Rover is moving following the issue23 of github")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(3, 4, Direction.EAST);
	}
}

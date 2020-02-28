package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {
	final MarsRoverImpl impl = new MarsRoverImpl();
	
	@Test
	void testMoveForwardFromOrigin() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("f"))
			.as("Rover is moving forward (North) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 1, Direction.NORTH);
	}
	
	@Test
	void testMoveForwardEastFromRightEnd() {
		MarsRover mrRightEast = impl.initialize(Position.of(
			impl.getPlanetMap().getPlanetMapSize()/2,
			0,
			Direction.EAST
		));
		Assertions.assertThat(mrRightEast.move("f"))
			.as("Rover is moving forward (EAST) from right end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0,
				Direction.EAST
			);
	}
	
	@Test
	void testMoveForwardWestFromLeftEnd() {
		MarsRover mrLeftWest = impl.initialize(
			Position.of(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0,
				Direction.WEST
			)
		);
		Assertions.assertThat(mrLeftWest.move("f"))
			.as("Rover is moving forward (WEST) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				impl.getPlanetMap().getPlanetMapSize()/2,
				0,
				Direction.WEST
			);
	}
	
	@Test
	void testMoveForwardNorthFromTopEnd() {
		MarsRover mrTopNorth = impl.initialize(
			Position.of(
				0,
				impl.getPlanetMap().getPlanetMapSize()/2,
				Direction.NORTH
			)
		);
		Assertions.assertThat(mrTopNorth.move("f"))
			.as("Rover is moving forward (NORTH) from top end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				0,
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				Direction.NORTH
			);
	}
	
	@Test
	void testMoveForwardSouthFromLeftEnd() {
		MarsRover mrBottomSouth = impl.initialize(
			Position.of(
				0,
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				Direction.SOUTH
			)
		);
		Assertions.assertThat(mrBottomSouth.move("f"))
			.as("Rover is moving forward (SOUTH) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				0,
				impl.getPlanetMap().getPlanetMapSize()/2,
				Direction.SOUTH
			);
	}
		
	@Test
	void testMoveBackwardFromOrigin() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("b"))
			.as("Rover is moving backward (South) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -1, Direction.NORTH);
	}
	
	@Test
	void testMoveBackwardWestFromRightEnd() {
		MarsRover mrRightWest = impl.initialize(
			Position.of(
				impl.getPlanetMap().getPlanetMapSize()/2,
				0,
				Direction.WEST
		));
		Assertions.assertThat(mrRightWest.move("b"))
			.as("Rover is moving backward (WEST) from right end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0,
				Direction.WEST
			);
	}
		
	@Test
	void testMoveBackwardEastFromLeftEnd() {
		MarsRover mrLeftEast = impl.initialize(
			Position.of(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0,
				Direction.EAST
			)
		);
		Assertions.assertThat(mrLeftEast.move("b"))
			.as("Rover is moving backward (EAST) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				impl.getPlanetMap().getPlanetMapSize()/2,
				0,
				Direction.EAST
			);
	}
	
	@Test
	void testMoveBackwardSouthFromTopEnd() {
		MarsRover mrTopSouth = impl.initialize(
			Position.of(
				0,
				impl.getPlanetMap().getPlanetMapSize()/2,
				Direction.SOUTH
			)
		);
		Assertions.assertThat(mrTopSouth.move("b"))
			.as("Rover is moving backward (SOUTH) from top end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				0,
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				Direction.SOUTH
			);
	}
		
	@Test
	void testMoveBackwardNorthFromLeftEnd() {
		MarsRover mrBottomNorth = impl.initialize(
			Position.of(
				0,
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				Direction.NORTH
			)
		);
		Assertions.assertThat(mrBottomNorth.move("b"))
			.as("Rover is moving backward (NORTH) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(
				0,
				impl.getPlanetMap().getPlanetMapSize()/2,
				Direction.NORTH
			);
	}
	
	@Test
	void testMoveRight() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("r"))
			.as("Rover is moving right (East) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.EAST);
	}
	
	@Test
	void testMoveLeft() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("l"))
			.as("Rover is moving left (West)")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.WEST);
	}
	
	@Test
	void testMoveNowhere() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("mmmmm"))
			.as("Rover is not moving from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovesLeft() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("lffff"))
			.as("Rover moving 4 times to the left")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-4, 0, Direction.WEST);
	}
	
	@Test
	void testMultipleMovesRight() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("rffff"))
			.as("Rover moving 4 times to the left")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(4, 0, Direction.EAST);
	}
	
	@Test
	void testMultipleMovesForward() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("ffff"))
			.as("Rover moving 4 times forward")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 4, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovesBackward() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("bbbb"))
			.as("Rover moving 4 times backward")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -4, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovements1() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("bfbfbf"))
			.as("Rover back and forth 3 times to the origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMultipleMovements2() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.move("frfrfrfr"))
			.as("Rover move in circle to the origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 0, Direction.NORTH);
	}
	
	@Test
	void testMoveForwardFromOriginNorthWithObstacle() {
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
		MarsRover marsRover = new MarsRoverImpl()
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
	void testShootNorthFromOriginWithoutObstacle() {
		MarsRover mr = impl.initialize(Position.of(0, 0, Direction.NORTH));
		Assertions.assertThat(mr.shoot())
			.as("Rover is shooting (North) from origin without any obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(0, 0);
	}
	
	@Test
	void testShootNorthFromOriginWithObstacle() {
		MarsRover marsRover = new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.NORTH));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(0, impl.getLaserRange(), Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (North) from origin with an obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(0, impl.getLaserRange());
	}
	
	@Test
	void testShootNorthFromOriginWithObstacleTooFar() {
		MarsRover marsRover = new MarsRoverImpl()
				.initialize(Position.of(0, 0, Direction.NORTH));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(0, impl.getLaserRange()+1, Direction.NORTH));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (North) from origin with an obstacle too far")
			.extracting(Position::getX, Position::getY)
			.containsExactly(0, 0);
	}
	
	@Test
	void testShootEastFromRightEndWithoutObstacle() {
		MarsRover mrRightEast = impl.initialize(Position.of(
			impl.getPlanetMap().getPlanetMapSize()/2,
			0,
			Direction.EAST
		));
		Assertions.assertThat(mrRightEast.shoot())
			.as("Rover is shooting (East) from right end without any obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				impl.getPlanetMap().getPlanetMapSize()/2,
				0
			);
	}
	
	@Test
	void testShootEastFromRightEndWithObstacle() {
		MarsRover marsRover = impl.initialize(Position.of(
			impl.getPlanetMap().getPlanetMapSize()/2,
			0,
			Direction.EAST
		));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			0,
			Direction.EAST
		));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (East) from right end with an obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0
			);
	}
	
	@Test
	void testShootEastFromRightEndWithObstacleTooFar() {
		MarsRover marsRover = impl.initialize(Position.of(
			impl.getPlanetMap().getPlanetMapSize()/2,
			0,
			Direction.EAST
		));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1+(impl.getLaserRange()+1),
			0,
			Direction.EAST
		));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (East) from right end with an obstacle too far")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				impl.getPlanetMap().getPlanetMapSize()/2,
				0
			);
	}
	
	@Test
	void testShootWestFromLeftEndWithoutObstacle() {
		MarsRover mr = impl.initialize(Position.of(
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			0,
			Direction.WEST
		));
		Assertions.assertThat(mr.shoot())
			.as("Rover is shooting (West) from left end without any obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0
			);
	}
	
	@Test
	void testShootWestFromLeftEndWithObstacle() {
		MarsRover marsRover = impl.initialize(Position.of(
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			0,
			Direction.WEST
		));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(
			impl.getPlanetMap().getPlanetMapSize()/2,
			0,
			Direction.WEST
		));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (West) from left end with an obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				impl.getPlanetMap().getPlanetMapSize()/2,
				0
			);
	}
	
	@Test
	void testShootWestFromLeftEndWithObstacleTooFar() {
		MarsRover marsRover = impl.initialize(Position.of(
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			0,
			Direction.WEST
		));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(
			impl.getPlanetMap().getPlanetMapSize()/2-impl.getLaserRange()-1,
			0,
			Direction.WEST
		));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (West) from left end with an obstacle too far")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
				0
			);
	}
	
	@Test
	void testShootSouthFromBottomEndWithoutObstacle() {
		MarsRover mr = impl.initialize(Position.of(
			0,
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			Direction.SOUTH
		));
		Assertions.assertThat(mr.shoot())
			.as("Rover is shooting (South) from bottom end without any obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				0,
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1
			);
	}
	
	@Test
	void testShootSouthFromBottomEndWithObstacle() {
		MarsRover marsRover = impl.initialize(Position.of(
			0,
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			Direction.SOUTH
		));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(
			0,
			impl.getPlanetMap().getPlanetMapSize()/2,
			Direction.SOUTH
		));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (South) from bottom end with an obstacle")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				0,
				impl.getPlanetMap().getPlanetMapSize()/2
			);
	}
	
	@Test
	void testShootSouthFromBottomEndWithObstacleTooFar() {
		MarsRover marsRover = impl.initialize(Position.of(
			0,
			(-impl.getPlanetMap().getPlanetMapSize()/2)+1,
			Direction.SOUTH
		));
		
		Set<Position> set = new HashSet<Position>();
		set.add(Position.of(
			0,
			impl.getPlanetMap().getPlanetMapSize()/2-impl.getLaserRange()-1,
			Direction.SOUTH
		));
		marsRover.updateMap(new PlanetMapImpl(set));
		
		Assertions.assertThat(marsRover.shoot())
			.as("Rover is shooting (South) from bottom end with an obstacle too far")
			.extracting(Position::getX, Position::getY)
			.containsExactly(
				0,
				(-impl.getPlanetMap().getPlanetMapSize()/2)+1
			);
	}
}

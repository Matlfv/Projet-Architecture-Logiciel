package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {
	
	final MarsRover mr = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));	
	final MarsRover mrRightEast = new MarsRoverImpl().initialize(Position.of(50, 0, Direction.EAST));
	final MarsRover mrRightWest = new MarsRoverImpl().initialize(Position.of(50, 0, Direction.WEST));
	final MarsRover mrLeftWest = new MarsRoverImpl().initialize(Position.of(-49, 0, Direction.WEST));
	final MarsRover mrLeftEast = new MarsRoverImpl().initialize(Position.of(-49, 0, Direction.EAST));
	final MarsRover mrTopNorth = new MarsRoverImpl().initialize(Position.of(0, 50, Direction.NORTH));
	final MarsRover mrTopSouth = new MarsRoverImpl().initialize(Position.of(0, 50, Direction.SOUTH));
	final MarsRover mrBottomSouth = new MarsRoverImpl().initialize(Position.of(0, -49, Direction.SOUTH));
	final MarsRover mrBottomNorth = new MarsRoverImpl().initialize(Position.of(0, -49, Direction.NORTH));

	@Test
	void testMoveForward() {
		Assertions.assertThat(mr.move("f"))
			.as("Rover is moving forward (North) from origin")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 1, Direction.NORTH);
		
		Assertions.assertThat(mrRightEast.move("f"))
			.as("Rover is moving forward (EAST) from right end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-49, 0, Direction.EAST);
		
		Assertions.assertThat(mrLeftWest.move("f"))
			.as("Rover is moving forward (WEST) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(50, 0, Direction.WEST);
		
		Assertions.assertThat(mrTopNorth.move("f"))
			.as("Rover is moving forward (NORTH) from top end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -49, Direction.NORTH);
		
		Assertions.assertThat(mrBottomSouth.move("f"))
			.as("Rover is moving forward (SOUTH) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 50, Direction.SOUTH);
	}
	
	@Test
	void testMoveBackward() {
		Assertions.assertThat(mr.move("b"))
				.as("Rover is moving backward (South) from origin")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.NORTH);
		
		Assertions.assertThat(mrRightWest.move("b"))
			.as("Rover is moving backward (WEST) from right end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-49, 0, Direction.WEST);
	
		Assertions.assertThat(mrLeftEast.move("b"))
			.as("Rover is moving backward (EAST) from left end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(50, 0, Direction.EAST);
	
		Assertions.assertThat(mrTopSouth.move("b"))
			.as("Rover is moving backward (SOUTH) from top end")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -49, Direction.SOUTH);
	
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
		.as("Rover moving 4 times to the left")
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
		.as("Rover move in circle to the origin")
		.extracting(Position::getX, Position::getY, Position::getDirection)
		.containsExactly(0, 0, Direction.NORTH);
	}
}

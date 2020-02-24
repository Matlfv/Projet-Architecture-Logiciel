package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {
	
	final MarsRover mr = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));

	@Test
	void testMoveForward() {
		Assertions.assertThat(mr.move("f"))
				.as("Rover is moving forward (North) from origin")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.NORTH);
	}
	
	@Test
	void testMoveBackward() {
		Assertions.assertThat(mr.move("b"))
				.as("Rover is moving backward (South) from origin")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.NORTH);
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

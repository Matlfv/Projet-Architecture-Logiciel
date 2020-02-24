package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {

	final MarsRover mr = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));

	@Test
	void testMoveForward() {
		Assertions.assertThat(mr.move("f"))
				.as("Rover moving forward (North)")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, 1, Direction.NORTH);
	}
	
	@Test
	void testMoveBackward() {
		Assertions.assertThat(mr.move("b"))
				.as("Rover moving backward (South)")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(0, -1, Direction.SOUTH);
	}
	
	@Test
	void testMoveRight() {
		Assertions.assertThat(mr.move("r"))
				.as("Rover moving right (East)")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(1, 0, Direction.EAST);
	}
	
	@Test
	void testMoveLeft() {
		Assertions.assertThat(mr.move("l"))
				.as("Rover moving left (West)")
				.extracting(Position::getX, Position::getY, Position::getDirection)
				.containsExactly(-1, 0, Direction.WEST);
	}
	
	@Test
	void testMoveNowhere() {
		Assertions.assertThat(mr.move("mmmmm"))
		.as("Rover not moving")
		.extracting(Position::getX, Position::getY, Position::getDirection)
		.containsExactly(0, 0, null);
	}
}

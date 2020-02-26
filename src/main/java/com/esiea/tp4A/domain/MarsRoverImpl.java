package com.esiea.tp4A.domain;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MarsRoverImpl implements MarsRover {
	private Position pos;
	public boolean alive;
	public String name;
	final int MAP_SIZE = 100;

	@Override
	public MarsRover initialize(Position position) {
		setPos(position);
		return this;
	}

	/**
	 * Return the new position of the rover
	 */
	@Override
	public Position move(String command) {
		CharacterIterator it = new StringCharacterIterator(command);
		Position initialPosition = getPos();
		Position tempPosition = getPos();

		while (it.current() != CharacterIterator.DONE) {
			tempPosition = computeMovement(it.current());

			if (checkMovement(tempPosition)) {
				setPos(tempPosition);
				it.next();
			} else {
				return initialPosition;
			}
		}
		return tempPosition;
	}

	/**
	 * Iterate every step of the command and process it
	 * 
	 * @param c
	 */
	private Position computeMovement(char c) {
		switch (c) {
		case 'f':
			return moveForward(c);
		case 'b':
			return moveBackward(c);
		case 'l':
			return rotateLeft();
		case 'r':
			return rotateRight();
		default:
			return getPos();
		}
	}

	private boolean checkMovement(Position position) {
		return true;
	}

	/**
	 * Allow the rover to go forward depending on the direction
	 * 
	 * @param c
	 */
	private Position moveForward(char c) {
		switch (pos.getDirection()) {
		case NORTH:
			if (pos.getY() > this.MAP_SIZE / 2) {
				return Position.of(pos.getX(), pos.getY() - this.MAP_SIZE, Direction.NORTH);
			}
			return Position.of(pos.getX(), pos.getY() + 1, Direction.NORTH);
		case SOUTH:
			if (pos.getY() < (-this.MAP_SIZE / 2) + 1) {
				return Position.of(pos.getX(), pos.getY() + this.MAP_SIZE, Direction.SOUTH);
			}
			return Position.of(pos.getX(), pos.getY() - 1, Direction.SOUTH);
		case EAST:
			if (pos.getX() > this.MAP_SIZE / 2) {
				return Position.of(pos.getX() - this.MAP_SIZE, pos.getY(), Direction.EAST);
			}
			return Position.of(pos.getX() + 1, pos.getY(), Direction.EAST);
		case WEST:
			if (pos.getX() < (-this.MAP_SIZE / 2) + 1) {
				return Position.of(pos.getX() + this.MAP_SIZE, pos.getY(), Direction.WEST);
			}
			return Position.of(pos.getX() - 1, pos.getY(), Direction.WEST);
		default:
			break;
		}
		;
		return getPos();
	}

	/**
	 * Allow the rover to go backward depending on the direction
	 * 
	 * @param c
	 */
	private Position moveBackward(char c) {
		switch (pos.getDirection()) {
		case NORTH:
			if (pos.getY() < (-this.MAP_SIZE / 2) + 1) {
				return Position.of(pos.getX(), pos.getY() + this.MAP_SIZE, Direction.NORTH);
			}
			return Position.of(pos.getX(), pos.getY() - 1, Direction.NORTH);
		case SOUTH:
			if (pos.getY() > this.MAP_SIZE / 2) {
				return Position.of(pos.getX(), pos.getY() - this.MAP_SIZE, Direction.SOUTH);
			}
			return Position.of(pos.getX(), pos.getY() + 1, Direction.SOUTH);
		case EAST:
			if (pos.getX() < (-this.MAP_SIZE / 2) + 1) {
				return Position.of(pos.getX() + this.MAP_SIZE, pos.getY(), Direction.EAST);
			}
			return Position.of(pos.getX() - 1, pos.getY(), Direction.EAST);
		case WEST:
			if (pos.getX() > this.MAP_SIZE / 2) {
				return Position.of(pos.getX() - this.MAP_SIZE, pos.getY(), Direction.WEST);
			}
			return Position.of(pos.getX() + 1, pos.getY(), Direction.WEST);
		default:
			return getPos();
		}
	}

	/**
	 * Rotate the direction to the right
	 */
	private Position rotateRight() {
		switch (pos.getDirection()) {
		case NORTH:
			return Position.of(pos.getX(), pos.getY(), Direction.EAST);
		case SOUTH:
			return Position.of(pos.getX(), pos.getY(), Direction.WEST);
		case EAST:
			return Position.of(pos.getX(), pos.getY(), Direction.SOUTH);
		case WEST:
			return Position.of(pos.getX(), pos.getY(), Direction.NORTH);
		default:
			return getPos();
		}
	}

	/*
	 * Rotate the direction to the left
	 */
	private Position rotateLeft() {
		switch (pos.getDirection()) {
		case NORTH:
			return Position.of(pos.getX(), pos.getY(), Direction.WEST);
		case SOUTH:
			return Position.of(pos.getX(), pos.getY(), Direction.EAST);
		case EAST:
			return Position.of(pos.getX(), pos.getY(), Direction.NORTH);
		case WEST:
			return Position.of(pos.getX(), pos.getY(), Direction.SOUTH);
		default:
			return getPos();
		}
	}

	private Position getPos() {
		return pos;
	}

	private void setPos(Position pos) {
		this.pos = pos;
	}

}

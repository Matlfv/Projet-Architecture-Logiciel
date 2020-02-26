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

	@Override
	public Position move(String command) {
		CharacterIterator it = new StringCharacterIterator(command);
		
		while(it.current() != CharacterIterator.DONE) {
			computeMovement(it.current());	
			it.next();
		}
		
		return getPos();
	}

    /**
	 * Iterate every step of the command and process it
	 * @param c
	 */
	private void computeMovement(char c) {
		switch (c) {
		case 'f':
			moveForward(c);
			break;
		case 'b':
			moveBackward(c);
			break;
		case 'l':
			rotateLeft();
			break;
		case 'r':
			rotateRight();
		default:
			break;
		};
	}

	/**
	 * Allow the rover to go forward depending on the direction
	 * @param c
	 */
	private void moveForward(char c) {
		switch(pos.getDirection()) {
		case NORTH:
			setPos(Position.of(pos.getX(), pos.getY() + 1, Direction.NORTH));
			if (pos.getY() > this.MAP_SIZE / 2) {
				setPos(Position.of(pos.getX(), pos.getY() - this.MAP_SIZE, Direction.NORTH));
			}
			break;
		case SOUTH:
			setPos(Position.of(pos.getX(), pos.getY() - 1, Direction.SOUTH));
			if (pos.getY() < (- this.MAP_SIZE / 2) + 1) {
				setPos(Position.of(pos.getX(), pos.getY() + this.MAP_SIZE, Direction.SOUTH));
			}
			break;
		case EAST:
			setPos(Position.of(pos.getX() + 1, pos.getY(), Direction.EAST));
			if (pos.getX() > this.MAP_SIZE / 2) {
				setPos(Position.of(pos.getX() - this.MAP_SIZE, pos.getY(), Direction.EAST));
			}
			break;
		case WEST:
			setPos(Position.of(pos.getX() - 1, pos.getY(), Direction.WEST));
			if (pos.getX() < (- this.MAP_SIZE / 2) + 1) {
				setPos(Position.of(pos.getX() + this.MAP_SIZE, pos.getY(), Direction.WEST));
			}
			break;
		default:
			break;
		};
	}

	/**
	 * Allow the rover to go backward depending on the direction
	 * @param c
	 */
	private void moveBackward(char c) {
		switch(pos.getDirection()) {
		case NORTH:
			setPos(Position.of(pos.getX(), pos.getY() - 1, Direction.NORTH));
			if (pos.getY() < (- this.MAP_SIZE / 2) + 1) {
				setPos(Position.of(pos.getX(), pos.getY() + this.MAP_SIZE, Direction.NORTH));
			}
			break;
		case SOUTH:
			setPos(Position.of(pos.getX(), pos.getY() + 1, Direction.SOUTH));
			if (pos.getY() > this.MAP_SIZE / 2) {
				setPos(Position.of(pos.getX(), pos.getY() - this.MAP_SIZE, Direction.SOUTH));
			}
			break;
		case EAST:
			setPos(Position.of(pos.getX() - 1, pos.getY(), Direction.EAST));
			if (pos.getX() < (- this.MAP_SIZE / 2) + 1) {
				setPos(Position.of(pos.getX() + this.MAP_SIZE, pos.getY(), Direction.EAST));
			}
			break;
		case WEST:
			setPos(Position.of(pos.getX() + 1, pos.getY(), Direction.WEST));
			if (pos.getX() > this.MAP_SIZE / 2) {
				setPos(Position.of(pos.getX() - this.MAP_SIZE, pos.getY(), Direction.WEST));
			}
			break;
		default:
			break;
		};
	}

	/**
	 * Rotate the direction to the right
	 */
	private void rotateRight() {
		switch (pos.getDirection()) {
		case NORTH:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.EAST));
			break;
		case SOUTH:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.WEST));
			break;
		case EAST:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.SOUTH));
			break;
		case WEST:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.NORTH));
			break;
		default:
			break;
		};
	}

	/*
	 * Rotate the direction to the left
	 */
	private void rotateLeft() {
		switch (pos.getDirection()) {
		case NORTH:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.WEST));
			break;
		case SOUTH:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.EAST));
			break;
		case EAST:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.NORTH));
			break;
		case WEST:
			setPos(Position.of(pos.getX(), pos.getY(), Direction.SOUTH));
			break;
		default:
			break;
		};
	}
  
	private Position getPos() {
		return pos;
	}

	private void setPos(Position pos) {
		this.pos = pos;
	}

}

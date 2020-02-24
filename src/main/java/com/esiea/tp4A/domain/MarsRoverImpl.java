package com.esiea.tp4A.domain;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MarsRoverImpl implements MarsRover {
	private Position pos;

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
	
	private void computeMovement(char c) {
		switch (c) {
		case 'f':
			moveUp();
			break;
		case 'b':
			moveDown();
			break;
		case 'l':
			moveLeft();
			break;
		case 'r':
			moveRight();
		default:
			break;
		}
	}

	private void moveUp() {
		setPos(Position.of(pos.getX(), pos.getY() + 1, Direction.NORTH));
	}

	private void moveDown() {
		setPos(Position.of(pos.getX(), pos.getY() - 1, Direction.SOUTH));
	}

	private void moveRight() {
		setPos(Position.of(pos.getX() + 1, pos.getY(), Direction.EAST));
	}

	private void moveLeft() {
		setPos(Position.of(pos.getX() - 1, pos.getY(), Direction.WEST));
	}

	private Position getPos() {
		return pos;
	}

	private void setPos(Position pos) {
		this.pos = pos;
	}

}

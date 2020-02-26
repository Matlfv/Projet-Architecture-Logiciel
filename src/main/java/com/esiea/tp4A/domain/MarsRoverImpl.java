package com.esiea.tp4A.domain;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashSet;
import java.util.Iterator;

public class MarsRoverImpl implements MarsRover {
	private Position pos;
	private PlanetMap planetMap;
	private int laserRange;
	
	@Override
	public MarsRover initialize(Position position) {
		this.setPos(position);
		this.setPlanetMap(new PlanetMapImpl(new HashSet<Position>()));
		this.setLaserRange(0);
		return this;
	}
	
	@Override
	public MarsRover updateMap(PlanetMap map) {
		this.setPlanetMap(map);
		return this;
	}
	
	@Override
	public MarsRover configureLaserRange(int range) {
		this.setLaserRange(range);
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
		return this.pos;
	}

	private void setPos(Position pos) {
		this.pos = pos;
	}

	private void setLaserRange(int laserRange) {
		this.laserRange = laserRange;
	}
	
	private void setPlanetMap(PlanetMap planetMap) {
		this.planetMap = planetMap;
	}
	
	private PlanetMap getPlanetMap() {
		return this.planetMap;
	}
	
	/*
	 * Determines if the cell front of rover is an obstacle
	 * 
	 * @return true if an obstacle is front of rover, false otherwise
	 */
	private boolean isNextCellEmpty() {
		Position position = null;
		
		switch(this.pos.getDirection()) {
		case NORTH:
			position = Position.of(
				this.pos.getX(),
				this.pos.getY() + 1,
				Direction.NORTH
			);
			if (position.getY() > this.planetMap.getPlanetMapSize() / 2) {
				position = Position.of(
					position.getX(),
					position.getY() - this.planetMap.getPlanetMapSize(),
					Direction.NORTH
				);
			}
			break;
		case SOUTH:
			position = Position.of(
				this.pos.getX(),
				this.pos.getY() - 1,
				Direction.SOUTH
			);
			if (position.getY() < (-this.planetMap.getPlanetMapSize()/2)+1) {
				position = Position.of(
					position.getX(),
					position.getY() + this.planetMap.getPlanetMapSize(),
					Direction.SOUTH
				);
			}
			break;
		case EAST:
			position = Position.of(
				this.pos.getX() + 1,
				this.pos.getY(),
				Direction.EAST
			);
			if (position.getX() > this.planetMap.getPlanetMapSize() / 2) {
				position = Position.of(
					position.getX() - this.planetMap.getPlanetMapSize(),
					position.getY(),
					Direction.EAST
				);
			}
			break;
		case WEST:
			position = Position.of(
				this.pos.getX() - 1,
				this.pos.getY(),
				Direction.WEST
			);
			if (position.getX() < (- this.planetMap.getPlanetMapSize()/2)+1) {
				position = Position.of(
					position.getX() + this.planetMap.getPlanetMapSize(),
					position.getY(),
					Direction.WEST
				);
			}
			break;
		default:
			break;
		};
		
		for (Iterator<Position> it = this.getPlanetMap()
				.obstaclePositions()
				.iterator(); it.hasNext();
		) {
	        Position p = it.next();
	        if (p.getX() == position.getX() && p.getY() == position.getY()) {
	            return false;
	        }
	    }
		
		return true;
	}
	
	/*
	 * Determines if the cell behind rover is an obstacle
	 * 
	 * @return true if an obstacle is behind of rover, false otherwise
	 */
	private boolean isPreviousCellEmpty() {
		Position position = null;
		
		switch(this.pos.getDirection()) {
		case NORTH:
			position = Position.of(
				this.pos.getX(),
				this.pos.getY() - 1,
				Direction.NORTH
			);
			if (position.getY() < (- this.planetMap.getPlanetMapSize()/2)+1) {
				position = Position.of(
					position.getX(),
					position.getY() + this.planetMap.getPlanetMapSize(),
					Direction.NORTH
				);
			}
			break;
		case SOUTH:
			position = Position.of(
				this.pos.getX(),
				this.pos.getY() + 1,
				Direction.SOUTH
			);
			if (position.getY() > this.planetMap.getPlanetMapSize() / 2) {
				position = Position.of(
					position.getX(),
					position.getY() - this.planetMap.getPlanetMapSize(),
					Direction.SOUTH
				);
			}
			break;
		case EAST:
			position = Position.of(
				this.pos.getX() - 1,
				this.pos.getY(),
				Direction.EAST
			);
			if (position.getX() < (- this.planetMap.getPlanetMapSize()/2)+1) {
				position = Position.of(
					position.getX() + this.planetMap.getPlanetMapSize(),
					position.getY(),
					Direction.EAST
				);
			}
			break;
		case WEST:
			position = Position.of(
				this.pos.getX() + 1,
				this.pos.getY(),
				Direction.WEST
			);
			if (position.getX() > this.planetMap.getPlanetMapSize() / 2) {
				position = Position.of(
					position.getX() - this.planetMap.getPlanetMapSize(),
					position.getY(),
					Direction.WEST
				);
			}
			break;
		default:
			break;
		};
		
		for (Iterator<Position> it = this.getPlanetMap()
				.obstaclePositions()
				.iterator(); it.hasNext();
		) {
	        Position p = it.next();
	        if (p.getX() == position.getX() && p.getY() == position.getY()) {
	            return false;
	        }
	    }
		
		return true;
	}
}

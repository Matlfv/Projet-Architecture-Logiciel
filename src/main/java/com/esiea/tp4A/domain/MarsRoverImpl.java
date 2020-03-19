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
		Position tempPosition;
		while (it.current() != CharacterIterator.DONE) {
			tempPosition = this.computeMovement(it.current());
			if (this.checkMovement(tempPosition)) {
				this.setPos(tempPosition);
			}
			it.next();
		}
		return getPos();
	}

	/**
	 * Iterate every step of the command and process it
	 * 
	 * @param c
	 */
	private Position computeMovement(char c) {
		switch (c) {
		case 'f':
			return this.moveForward();
		case 'b':
			return this.moveBackward();
		case 'l':
			return this.rotateLeft();
		case 'r':
			return this.rotateRight();
        case 's':
            return this.shootLaser();
		default:
			return this.getPos();
		}
	}

	/**
	 * Checks if given position is not already in use by an obstacle
	 * 
	 * @return true if there is no obstacle, false otherwise
	 */
	private boolean checkMovement(Position position) {
		for (Iterator<Position> it = this.getPlanetMap().obstaclePositions().iterator(); it.hasNext();) {
			Position p = it.next();
			if (p.getX() == position.getX() && p.getY() == position.getY()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Allow the rover to go forward depending on the direction
	 */
	private Position moveForward() {
		switch (this.pos.getDirection()) {
		case NORTH:
			if (this.pos.getY() + 1 > this.getPlanetMap().getPlanetMapSize() / 2) {
				return Position.of(this.pos.getX(), this.pos.getY() + 1 - this.getPlanetMap().getPlanetMapSize(),
						Direction.NORTH);
			}
			return Position.of(this.pos.getX(), this.pos.getY() + 1, Direction.NORTH);
		case SOUTH:
			if (this.pos.getY() - 1 < (-this.getPlanetMap().getPlanetMapSize() / 2) + 1) {
				return Position.of(this.pos.getX(), this.pos.getY() - 1 + this.getPlanetMap().getPlanetMapSize(),
						Direction.SOUTH);
			}
			return Position.of(this.pos.getX(), this.pos.getY() - 1, Direction.SOUTH);
		case EAST:
			if (this.pos.getX() + 1 > this.getPlanetMap().getPlanetMapSize() / 2) {
				return Position.of(this.pos.getX() + 1 - this.getPlanetMap().getPlanetMapSize(), this.pos.getY(),
						Direction.EAST);
			}
			return Position.of(this.pos.getX() + 1, this.pos.getY(), Direction.EAST);
		case WEST:
			if (this.pos.getX() - 1 < (-this.getPlanetMap().getPlanetMapSize() / 2) + 1) {
				return Position.of(this.pos.getX() - 1 + this.getPlanetMap().getPlanetMapSize(), this.pos.getY(),
						Direction.WEST);
			}
			return Position.of(this.pos.getX() - 1, this.pos.getY(), Direction.WEST);
		default:
			break;
		}
		;
		return this.getPos();
	}

	/**
	 * Allow the rover to go backward depending on the direction
	 */
	private Position moveBackward() {
		switch (this.pos.getDirection()) {
		case NORTH:
			if (this.pos.getY() - 1 < (-this.getPlanetMap().getPlanetMapSize() / 2) + 1) {
				return Position.of(this.pos.getX(), this.pos.getY() - 1 + this.getPlanetMap().getPlanetMapSize(),
						Direction.NORTH);
			}
			return Position.of(this.pos.getX(), this.pos.getY() - 1, Direction.NORTH);
		case SOUTH:
			if (pos.getY() + 1 > this.getPlanetMap().getPlanetMapSize() / 2) {
				return Position.of(this.pos.getX(), this.pos.getY() + 1 - this.getPlanetMap().getPlanetMapSize(),
						Direction.SOUTH);
			}
			return Position.of(this.pos.getX(), this.pos.getY() + 1, Direction.SOUTH);
		case EAST:
			if (pos.getX() - 1 < (-this.getPlanetMap().getPlanetMapSize() / 2) + 1) {
				return Position.of(this.pos.getX() - 1 + this.getPlanetMap().getPlanetMapSize(), pos.getY(),
						Direction.EAST);
			}
			return Position.of(this.pos.getX() - 1, this.pos.getY(), Direction.EAST);
		case WEST:
			if (this.pos.getX() + 1 > this.getPlanetMap().getPlanetMapSize() / 2) {
				return Position.of(this.pos.getX() + 1 - this.getPlanetMap().getPlanetMapSize(), this.pos.getY(),
						Direction.WEST);
			}
			return Position.of(this.pos.getX() + 1, this.pos.getY(), Direction.WEST);
		default:
			return this.getPos();
		}
	}

	/**
	 * Rotate the direction to the right
	 */
	private Position rotateRight() {
		switch (this.pos.getDirection()) {
		case NORTH:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.EAST);
		case SOUTH:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.WEST);
		case EAST:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.SOUTH);
		case WEST:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.NORTH);
		default:
			return this.getPos();
		}
	}

	/**
	 * Rotate the direction to the left
	 */
	private Position rotateLeft() {
		switch (this.pos.getDirection()) {
		case NORTH:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.WEST);
		case SOUTH:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.EAST);
		case EAST:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.NORTH);
		case WEST:
			return Position.of(this.pos.getX(), this.pos.getY(), Direction.SOUTH);
		default:
			return this.getPos();
		}
	}

    /**
     * Shoot the laser in the direction faced
     */
    private Position shootLaser() {
        Position originalPosition = this.getPos();

        Position tempPosition;

        for(int i = 1; i <= this.laserRange; i++) {
            tempPosition = this.computeMovement('f');

            if(!this.checkMovement(tempPosition)) {
//                If movement isn't possible due to obstacle
                this.getPlanetMap().deleteObstacle(tempPosition);
                break;
            }

            this.setPos(tempPosition);
        }

        return originalPosition;
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
}

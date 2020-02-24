package com.esiea.tp4A.domain;

import com.esiea.tp4A.domain.Position.FixedPosition;

public class MarsRoverImpl implements MarsRover {
	private FixedPosition pos;

	@Override
	public Position move(String command) {
		switch (command) {
		case "f":
			return Position.of(0, 1, Direction.NORTH);
		case "b":
			return Position.of(0, -1, Direction.SOUTH);
		case "l":
			return Position.of(-1, 0, Direction.WEST);
		case "r":
			return Position.of(1, 0, Direction.EAST);
		default:
			return Position.of(0, 0, null);
		}
	}

	public FixedPosition getPos() {
		return pos;
	}

	public void setPos(FixedPosition pos) {
		this.pos = pos;
	}

}

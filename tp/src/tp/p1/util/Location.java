package tp.p1.util;

import tp.p1.game.Game;

public class Location {
	private int x, y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Location(Location pos) {
		this.setEqual(pos);
	}

	public boolean equals(Location pos) {
		return x == pos.getX() && y == pos.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private Boolean isValid() {
		int dimX = Game.boardDimension.getX();
		int dimY = Game.boardDimension.getY();
		return 0 <= x && x < dimX && 0 <= y && y < dimY;
	}

	public void setEqual(Location position) {
		x = position.getX();
		y = position.getY();
	}

	public void setPosition(Location diff) {
		setY(diff.getY());
		setX(diff.getX());
	}

	private void setX(int diffX) {
		this.x += diffX;
		if (!this.isValid())
			this.x -= diffX;
	}

	private void setY(int diffY) {
		this.y += diffY;
		if (!this.isValid())
			this.y -= diffY;
	}
}

package tp.p1.game.info;

import tp.p1.util.Location;

public enum ProjectileType {
	MISSILE(1, "oo", MovementDirection.UP.getCoordinates()),
	PROJECTILE(1, ".", MovementDirection.DOWN.getCoordinates()),
	SHOCKWAVE(1, "", null);

	private Location direction;
	private int dmg;
	private String symbol;

	ProjectileType(int dmg, String symbol, Location direction) {
		this.dmg = dmg;
		this.direction = direction;
		this.symbol = symbol;
	}

	public Location getDirection() {
		return direction;
	}

	public int getDmg() {
		return dmg;
	}

	public String getSymbol() {
		return symbol;
	}
};
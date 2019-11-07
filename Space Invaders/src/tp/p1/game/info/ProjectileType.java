package tp.p1.game.info;

public enum ProjectileType {
	MISSILE(1, "oo", MovementDirection.UP),
	SUPERMISSILE(2, "xx", MovementDirection.UP),
	PROJECTILE(1, ".", MovementDirection.DOWN),
	SHOCKWAVE(1, "", null),
	EXPLOSION(1, "", null);

	private MovementDirection direction;
	private int dmg;
	private String symbol;

	ProjectileType(int dmg, String symbol, MovementDirection direction) {
		this.dmg = dmg;
		this.direction = direction;
		this.symbol = symbol;
	}

	public MovementDirection getDirection() {
		return direction;
	}

	public int getDmg() {
		return dmg;
	}

	public String getSymbol() {
		return symbol;
	}
};
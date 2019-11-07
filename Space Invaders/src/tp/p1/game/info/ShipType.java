package tp.p1.game.info;
import tp.p1.util.Location;

public enum ShipType {
	DESTROYER("[D]estroyer ship", 1, 10, "D[*hp*]", null, "D",  ProjectileType.PROJECTILE), 
	OVNI("[O]vni", 1, 25, "O[*hp*]", new Location(0, 8), "O", null), 
	REGULAR("[R]egular ship", 2, 5, "R[*hp*]", null, "R", null),
	EXPLOSIVE("[E]xplosive ship", 2, 5, "E[*hp*]", null, "E", null),
	UCM("^__^", 3, 0, "^__^", new Location(7, 4), "U", ProjectileType.MISSILE);

	private int awardedPoints;


	private String fullName;
	private int hp;
	private Location initialPosition;
	private String mapSymbol;
	private ProjectileType projectile;
	private String symbol;

	ShipType(String fullName, int hp, int awardedPoints, String symbol, Location initialPosition, String mapSymbol, 
			ProjectileType projectile) {
		this.fullName = fullName;
		this.hp = hp;
		this.awardedPoints = awardedPoints;
		this.symbol = symbol;
		this.initialPosition = initialPosition;
		this.mapSymbol = mapSymbol;
		this.projectile = projectile;
	}
	
	public int getAwardedPoints() {
		return awardedPoints;
	}
	public String getFullName() {
		return fullName;
	}

	public int getHp() {
		return hp;
	}

	public Location getInitialPosition() {
		return initialPosition;
	}

	public String getMapSymbol () {
		return mapSymbol;
	}
	
	public ProjectileType getProjectile() {
		return projectile;
	}
	
	public String getSymbol() {
		return symbol;
	}
};
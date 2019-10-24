package tp.p1.game.info;
import tp.p1.util.Location;

public enum ShipType {
	DESTROYER(Faction.ENEMY, "[D]estroyer ship", 1, 10, "D[*hp*]", null, "D",  ProjectileType.PROJECTILE), 
	OVNI(Faction.ENEMY, "[O]vni", 1, 25, "O[*hp*]", new Location(0, 8), "O", null), 
	REGULAR(Faction.ENEMY, "[R]egular ship", 2, 5, "R[*hp*]", null, "R", null),
	UCM(Faction.ALLY, "^__^", 3, 0, "^__^", new Location(7, 4), "U", ProjectileType.MISSILE);

	private int awardedPoints;


	protected Faction faction;
	private String fullName;
	private int hp;
	private Location initialPosition;
	private String mapSymbol;
	private ProjectileType projectile;
	private String symbol;

	ShipType(Faction faction, String fullName, int hp, int awardedPoints, String symbol, Location initialPosition, String mapSymbol, 
			ProjectileType projectile) {
		this.faction = faction;
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

	public Faction getFaction() {
		return faction;
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
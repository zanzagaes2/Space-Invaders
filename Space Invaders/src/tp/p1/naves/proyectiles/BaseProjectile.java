package tp.p1.naves.proyectiles;

import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.Location;

public abstract class BaseProjectile {
	protected Boolean inBoard;
	protected BaseShip owner;
	protected Location position;
	protected ProjectileType type;

	public BaseProjectile(Location initialPos, BaseShip owner, ProjectileType type) {
		position = new Location(0, 0);
		position.setEqual(initialPos);
		inBoard = true;
		this.owner = owner;
		this.type = type;
	}

	public int getDmg() {
		return type.getDmg();
	}

	public Location getPosition() {
		return position;
	}
	
	public ProjectileType getType() {
		return type;
	}
	
	public void informOwner() {
		if (owner != null)
			owner.projectileImpacted();
	}

	public Boolean isInBoard() {
		return inBoard;
	}

	public void markUnboard() {
		inBoard = false;
	}

	public void move() {
		Location previousPosition = new Location(position);
		position.setPosition(type.getDirection());
		if (previousPosition.equals(position)) inBoard = false;
	}

	@Override
	public String toString() {
		return type.getSymbol();
	}
}

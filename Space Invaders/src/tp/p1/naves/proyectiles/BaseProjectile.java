package tp.p1.naves.proyectiles;

import tp.p1.game.Game;
import tp.p1.game.GameObject;
import tp.p1.game.info.EntityType;
import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public abstract class BaseProjectile extends GameObject {
	protected BaseShip owner;
	protected ProjectileType type;

	public BaseProjectile(Game game, Location initialPos, BaseShip owner, ProjectileType type) {
		super(game, EntityType.PROJECTILE, owner.getType().getFaction(), initialPos);
		this.owner = owner;
		this.type = type;
	}

	public int getDmg() {
		return type.getDmg();
	}

	public ProjectileType getType() {
		return type;
	}
	
	public void informOwner() {
		if (owner != null)
			owner.projectileImpacted();
	}
	
	public void move() {
		Location previousPosition = new Location(position);
		super.move(type.getDirection());
		if (previousPosition.equals(position)) isValid = false;
	}

	@Override
	public void passTurn() {
		move();
		
		for (GameObject object: game.getObjects().objectsAt(this.position).iter())
			if (object.getFaction() != this.faction && object != this) {
				object.sufferHit(this); 
				isValid = false;
			}
		if (!isValid) informOwner();
	}
	
	@Override
	public void sufferHit(BaseProjectile projectile) {
		isValid = false;
	}
	
	@Override
	public String toString() {
		return type.getSymbol();
	}
		
	@Override
	public GameEventList update(GameEventList events) {
		return events;
	}
}

package tp.p1.naves.proyectiles;

import java.util.function.BiFunction;
import java.util.function.Function;

import tp.p1.game.Game;
import tp.p1.game.GameObject;
import tp.p1.game.info.Entity;
import tp.p1.game.info.MovementDirection;
import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public abstract class BaseProjectile extends GameObject {
	protected BaseShip owner;
	protected ProjectileType type;

	public BaseProjectile(Game game, Location initialPos, BaseShip owner, ProjectileType type) {
		super(game, Entity.PROJECTILE, initialPos);
		this.owner = owner;
		this.type = type;	
	}

	private void checkProjectileImpact() {
		for (GameObject object: game.getObjects().iter())
			if (object.isValid() && getFilterFunction().apply(object) && 
					getDmgFunction().apply(object, type.getDmg())) 
				isValid = false;
	}
	
	protected abstract Function<GameObject, Boolean> getFilterFunction();
	protected abstract BiFunction<GameObject, Integer, Boolean> getDmgFunction();
	
	public ProjectileType getType() {
		return type;
	}

	public void informOwner() {
		if (owner != null)
			owner.projectileImpacted();
	}
	
	public void move() {
		MovementDirection direction = type.getDirection();
		if (direction != null) { 
			Location previousPosition = new Location(position);
			super.move(type.getDirection());
			if (previousPosition.equals(position)) isValid = false;
		}
	}
	
	public Boolean receiveExplosionAttack(Integer dmg) {
		isValid = false;
		return true;
	}
	
	@Override
	public void passTurn(boolean timeToMove) {
		move();	
		checkProjectileImpact();
		if (!isValid) informOwner();
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

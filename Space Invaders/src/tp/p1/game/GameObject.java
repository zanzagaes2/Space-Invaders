package tp.p1.game;

import tp.p1.game.info.Entity;
import tp.p1.game.info.Faction;
import tp.p1.game.info.MovementDirection;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public abstract class GameObject {
	protected Entity entity;
	protected Faction faction;
	protected Game game;
	protected boolean isValid = true;
	protected Location position;
	
	
	protected GameObject (Game game, Entity entity, Faction faction, Location position) {
		this.game = game;
		this.entity = entity;
		this.faction = faction;
		this.position = new Location(position);
	}

	public Entity getEntity() {
		return entity;
	}

	public Faction getFaction() {
		return faction;
	}
	
	public Location getPosition () {
		return position;
	}
	
	public boolean isAtLimit() {
		return false;	
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public void move(Location direction) {
		position.setPosition(direction);
	}
	
	public void move(MovementDirection direction) {
		position.setPosition(direction.getCoordinates());
	}
	
	public abstract void passTurn();
	public abstract void sufferHit(int damage);
	@Override
	public abstract String toString();
	public abstract GameEventList update(GameEventList events);
	
}

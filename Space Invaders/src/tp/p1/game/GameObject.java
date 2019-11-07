package tp.p1.game;

import tp.p1.game.info.Entity;
import tp.p1.game.info.MovementDirection;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public abstract class GameObject implements IAttack {
	protected Entity entity;
	protected Game game;
	protected boolean isValid = true;
	protected Location position;

	
	protected GameObject (Game game, Entity entity, Location position) {
		this.game = game;
		this.entity = entity;
		this.position = new Location(position);
	}

	public Entity getEntity() {
		return entity;
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
	
	public abstract void passTurn(boolean timeToMove);
	@Override
	public abstract String toString();
	public abstract GameEventList update(GameEventList events);
	
}

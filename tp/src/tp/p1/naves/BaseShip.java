package tp.p1.naves;

import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public abstract class BaseShip {
	protected int hp;
	protected Location position;
	protected ShipType type;
	protected Boolean valid = true;
	protected BaseProjectile projectile = null;
	
	BaseShip(ShipType type) {
		this.type = type;
		hp = type.getHp();
		position = new Location(type.getInitialPosition());
	}
	
	BaseShip(ShipType type, Location position) {
		this.type = type;
		hp = type.getHp();
		this.position = new Location(position);
	}
	
	public int getHp() {
		return hp;
	}

	public Location getPosition() {
		return position;
	}
	
	public ShipType getType () {
		return this.type;
	}
	
	public Boolean isValid() {
		return valid;
	}

	public void move(Location movement, Boolean timeToMove) {
		if(timeToMove) position.setPosition(movement);
	}

	public void projectileImpacted() {
		projectile = null;
	}
	
	public BaseProjectile shoot () {
		return null;
	}
	
	public void sufferHit(int impact) {
		hp -= impact;
		valid = hp > 0;
	}
	
	@Override
	public String toString() {
		return type.getSymbol().replace("*hp*", "" + hp);
	}
	
	public GameEventList update() {
		GameEventList newEvents = new GameEventList();
		if (hp <= 0) {
			GameEvent event = GameEvent.POINTS_EARNED;
			event.setQuantity(type.getAwardedPoints());
			newEvents.add(event);
		}
		return newEvents;
	}

}

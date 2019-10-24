package tp.p1.naves;

import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Missile;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class UCMShip extends BaseShip {
	private static ShipType type = ShipType.UCM;
	private Boolean shockwave = false;

	public UCMShip() {
		super (type);
	}
	
	public Boolean getShockwave() {
		return shockwave;
	}
		
	public void move(Location movement) {
		super.move(movement, true);
	}
	
	public void setShockwave(Boolean status) {
		shockwave = status;
	}
	
	@Override
	public Missile shoot() {
		BaseProjectile newProjectile = null;
		if (projectile == null) projectile = newProjectile = new Missile(position, this);
		return (Missile) newProjectile;
	}

	@Override
	public String toString () {
		if (getHp() <= 0) return "!xx!";
		else return super.toString();
	}
	
	@Override
	public GameEventList update() {
		GameEventList newEvents = super.update();
		if (!isValid()) newEvents.add(GameEvent.PLAYER_KILLED);
		return newEvents;
	}


}
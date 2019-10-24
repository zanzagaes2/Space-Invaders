package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Missile;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;

public class UCMShip extends BaseShip {
	private static ShipType type = ShipType.UCM;
	private Boolean shockwave = false;

	public UCMShip(Game game) {
		super (game, type);
	}
	
	public Boolean getShockwave() {
		return shockwave;
	}
		
	@Override
	public void passTurn() {
		;
	}
	
	public void setShockwave(Boolean status) {
		shockwave = status;
	}

	public void shoot() {
		BaseProjectile newProjectile = null;
		if (projectile == null) {
			projectile = newProjectile = new Missile(game, position, this);
			game.addProjectile(newProjectile);
		}
	}
	
	@Override
	public String toString () {
		if (getHp() <= 0) return "!xx!";
		else return super.toString();
	}
	
	@Override
	public GameEventList update(GameEventList events) {
		if (!isValid()) events.add(GameEvent.PLAYER_KILLED);
		return events;
	}


}
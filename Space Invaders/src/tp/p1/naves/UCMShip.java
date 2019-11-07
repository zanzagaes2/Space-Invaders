package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Missile;
import tp.p1.naves.proyectiles.SuperMissile;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;

public class UCMShip extends BaseShip {
	private static ShipType type = ShipType.UCM;
	private int superMissileNumber = 0;
	
	public int getSuperMissileNumber() {
		return superMissileNumber;
	}

	public void addSuperMissile() {
		this.superMissileNumber += 1;
	}

	private Boolean shockwave;

	public UCMShip(Game game) {
		super (game, type);
	}
	
	public Boolean getShockwave() {
		return shockwave;
	}
		
	@Override
	public void passTurn(boolean timeToMove) {
		;
	}
	
	public Boolean receiveBombAttack(Integer damage) {
		alterHP(-1*damage);
		return true;
	}

	public void setShockwave(Boolean status) {
		shockwave = status;
	}
	
	public boolean shoot(boolean superMissile) {
		BaseProjectile newProjectile = null;
		if (projectile == null) {
			if (superMissile) {
				projectile = newProjectile = new SuperMissile(game, position,this);
				superMissileNumber--;
			}
			else projectile = newProjectile = new Missile(game,position,this);
			game.addProjectile(newProjectile);
		}
		return newProjectile != null;
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
package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Projectile;
import tp.p1.util.Location;

public class DestroyerShip extends AlienShip {
	public DestroyerShip(Game game, Location initialPosition) {
		super(game, ShipType.DESTROYER, initialPosition);
	}
	
	@Override
	public void passTurn(boolean timeToMove) {
		if(game.randomChance(game.getDifficulty().getShootFrequency())) {
			BaseProjectile newProjectile = shoot();
			if (newProjectile != null) game.addProjectile(newProjectile);
		}
		super.passTurn(timeToMove);
	} 
	
	public Projectile shoot() {
		BaseProjectile newProjectile = null;
		if (projectile == null) projectile = newProjectile = new Projectile(game, position, this);
		return (Projectile) newProjectile;
	}	
}

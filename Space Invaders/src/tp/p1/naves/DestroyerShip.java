package tp.p1.naves;

import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Projectile;
import tp.p1.util.Location;

public class DestroyerShip extends BaseShip {
	public DestroyerShip(Location initialPosition) {
		super(ShipType.DESTROYER, initialPosition);
	}

	@Override
	public void projectileImpacted() {
		projectile = null;
	}

	@Override
	public Projectile shoot() {
		BaseProjectile newProjectile = null;
		if (projectile == null) projectile = newProjectile = new Projectile(this.getPosition(), this);
		return (Projectile) newProjectile;
	}

}

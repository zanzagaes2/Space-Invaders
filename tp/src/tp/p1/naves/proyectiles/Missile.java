package tp.p1.naves.proyectiles;

import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.Location;

public class Missile extends BaseProjectile {
	public Missile(Location initialPos, BaseShip owner) {
		super(initialPos, owner, ProjectileType.MISSILE);
	}
}

package tp.p1.naves.proyectiles;

import tp.p1.game.Game;
import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.Location;

public class Projectile extends BaseProjectile {
	public Projectile(Game game, Location initialPos, BaseShip owner) {
		super (game, initialPos, owner, ProjectileType.PROJECTILE);
	}

}

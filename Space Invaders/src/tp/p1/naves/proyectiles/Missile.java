package tp.p1.naves.proyectiles;

import tp.p1.game.Game;
import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.Location;

public class Missile extends BaseProjectile {
	public Missile(Game game, Location initialPos, BaseShip owner) {
		super(game, initialPos, owner, ProjectileType.MISSILE);
	}
}

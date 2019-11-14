package tp.p1.naves.proyectiles;

import java.util.function.BiFunction;
import java.util.function.Function;

import tp.p1.game.Game;
import tp.p1.game.GameObject;
import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.naves.UCMShip;
import tp.p1.util.Location;

public class Shockwave extends BaseProjectile {
	public Shockwave(Game game, BaseShip player) {
		super(game, new Location(0,0), player,ProjectileType.SHOCKWAVE);
	}

	protected BiFunction<GameObject, Integer, Boolean> getDmgFunction() {
		return (GameObject object, Integer dmg) -> object.receiveShockWaveAttack(dmg);
	}
	
	protected Function<GameObject, Boolean> getFilterFunction() {
		return (GameObject object) -> true;
	}
	
	@Override
	public void informOwner() {
		((UCMShip) owner).setShockwave(false);
	}
}

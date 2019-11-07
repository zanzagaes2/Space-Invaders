package tp.p1.naves.proyectiles;

import java.util.function.BiFunction;
import java.util.function.Function;

import tp.p1.game.Game;
import tp.p1.game.GameObject;
import tp.p1.game.info.ProjectileType;
import tp.p1.naves.BaseShip;
import tp.p1.util.Location;

public class Missile extends BaseProjectile {
	public Missile(Game game, Location initialPos, BaseShip owner) {
		super(game, initialPos, owner, ProjectileType.MISSILE);
	}
	
	public Missile(Game game, Location initialPos, BaseShip owner, boolean superMissile) {
		super(game, initialPos, owner, (superMissile ? ProjectileType.SUPERMISSILE : ProjectileType.MISSILE));
	}
	
	protected BiFunction<GameObject, Integer, Boolean> getDmgFunction() {
		return (GameObject object, Integer dmg) -> object.receiveMissileAttack(dmg);
	}
	
	public Boolean receiveBombAttack(Integer damage) {
		isValid = false;
		informOwner();
		return true;
	}
	
	protected Function<GameObject, Boolean> getFilterFunction() {
		return (GameObject object) -> object.getPosition().equals(position);
	}
}

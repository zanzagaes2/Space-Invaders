package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.GameObject;
import tp.p1.game.info.EntityType;
import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public abstract class BaseShip extends GameObject {
	protected int hp;
	protected BaseProjectile projectile = null;
	protected ShipType type;
	
	BaseShip(Game game, ShipType type) {
		this(game, type, type.getInitialPosition());
	}
	
	BaseShip(Game game, ShipType type, Location position) {
		super(game, EntityType.SHIP, type.getFaction(), position);
		this.type = type;
		this.hp = type.getHp();
	}
	
	public int getHp() {
		return hp;
	}
	
	public ShipType getType () {
		return this.type;
	}

	public void projectileImpacted() {
		projectile = null;
	}
	
	@Override
	public void sufferHit(BaseProjectile projectile) {
		hp -= projectile.getDmg();
		isValid = hp > 0;
	}
	
	@Override
	public String toString() {
		return type.getSymbol().replace("*hp*", "" + hp);
	}
	
	public GameEventList update() {
		GameEventList newEvents = new GameEventList();
		if (hp <= 0) {
			GameEvent event = GameEvent.POINTS_EARNED;
			event.setQuantity(type.getAwardedPoints());
			newEvents.add(event);
		}
		return newEvents;
	}

}

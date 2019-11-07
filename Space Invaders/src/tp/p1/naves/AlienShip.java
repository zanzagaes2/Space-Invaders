package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.IAttack;
import tp.p1.game.info.ShipType;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class AlienShip extends BaseShip {

	AlienShip(Game game, ShipType type, Location position) {
		super(game, type, position);
	}
	
	@Override
	public boolean isAtLimit() {
		return game.isAtLimit(this);
	}
	
	@Override
	public void passTurn(boolean timeToMove) {
		if (timeToMove) move(game.getObjects().getMovementDirection());
	}
	
	public Boolean receiveMissileAttack(Integer damage) {
		alterHP(-1*damage);
		return true;
	}
	
	
	public Boolean receiveShockWaveAttack(Integer damage) {
		alterHP(-1*damage);
		return true;
	}
	
	@Override
	public GameEventList update(GameEventList events) {
		if (position.getX() == Game.boardDimension.getX()-1) 
			events.add(GameEvent.ENEMY_REACHED_FIRST_LINE);
		if (hp <= 0) {
			GameEvent earnedPoints = GameEvent.POINTS_EARNED;
			earnedPoints.setQuantity(type.getAwardedPoints());
			events.add(earnedPoints);
		}
		return events;
	}
	
}

package tp.p1.naves;

import tp.p1.game.Game;
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
	public void passTurn() {
		if (game.isTimeToMove()) move(game.getMovementDirection());
	}
	
	@Override
	public GameEventList update(GameEventList events) {
		if (position.getY() == Game.boardDimension.getY()) 
			events.add(GameEvent.ENEMY_REACHED_FIRST_LINE);
		return events;
	}
	
}

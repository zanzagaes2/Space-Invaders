package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.info.MovementDirection;
import tp.p1.game.info.ShipType;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class UFOShip extends BaseShip {
	public UFOShip(Game game) {
		super(game, ShipType.OVNI);
	}
	
	public void move() {
		Location previousPosition = new Location(position);
		super.move(MovementDirection.LEFT.getCoordinates());
		if (previousPosition.equals(position)) isValid = false;
	}
	
	@Override
	public void passTurn(boolean timeToMove) {
		move();
	}
	
	@Override
	public GameEventList update() {
		GameEventList newEvents = super.update();
		if (!isValid()) newEvents.add(GameEvent.OVNI_DESTROYED);
		return newEvents;
	}
	
	@Override
	public GameEventList update(GameEventList events) {
		if (!isValid)
			if (hp <= 0) events.add(GameEvent.OVNI_DESTROYED);
			else events.add(GameEvent.OVNI_OUT_OF_SCREEN);
		return events;
	}


}

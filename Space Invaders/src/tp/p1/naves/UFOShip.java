package tp.p1.naves;

import tp.p1.game.info.MovementDirection;
import tp.p1.game.info.ShipType;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class UFOShip extends BaseShip {
	public UFOShip() {
		super(ShipType.OVNI);
	}
	
	@Override
	public Boolean isValid() {
		return valid;
	}
	
	@Override
	public void move(Location movement, Boolean timeToMove) {
		Location previousPosition = new Location(position);
		super.move(MovementDirection.LEFT.getCoordinates(), true);
		if (previousPosition.equals(position)) valid = false;
	}
	
	@Override
	public GameEventList update() {
		GameEventList newEvents = super.update();
		if (!isValid()) newEvents.add(GameEvent.OVNI_DESTROYED);
		return newEvents;
	}

}

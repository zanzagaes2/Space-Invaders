package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.info.ShipType;
import tp.p1.util.Location;

public class RegularShip extends AlienShip {
	public RegularShip(Game game, Location initialPosition) {
		super(game, ShipType.REGULAR, initialPosition);
	}
}
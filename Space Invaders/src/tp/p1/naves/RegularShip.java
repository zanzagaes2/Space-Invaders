package tp.p1.naves;

import tp.p1.game.info.ShipType;
import tp.p1.util.Location;

public class RegularShip extends BaseShip {
	public RegularShip(Location initialPosition) {
		super(ShipType.REGULAR, initialPosition);
	}
}
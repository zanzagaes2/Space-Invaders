package tp.p1.naves;

import tp.p1.game.Game;
import tp.p1.game.info.ShipType;
import tp.p1.naves.proyectiles.Explosion;
import tp.p1.util.Location;

public class RegularShip extends AlienShip {
	boolean exploded = false;
	public RegularShip(Game game, Location initialPosition) {
		super(game, ShipType.REGULAR, initialPosition);
	}
	
	@Override
	public void passTurn(boolean turnToMove) {
		if (game.randomChance(game.getDifficulty().getExplosiveTransformationFrequency()))
			type = ShipType.EXPLOSIVE;
		super.passTurn(turnToMove);
	}
	
	protected void alterHP(int diff) {
		super.alterHP(diff);
		if (!isValid && type == ShipType.EXPLOSIVE && !exploded) {
			exploded = true;
			game.addProjectile(new Explosion(game, this));
		}
	}
}
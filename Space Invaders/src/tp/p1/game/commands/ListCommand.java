package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;
import tp.p1.game.info.ShipType;

public class ListCommand extends Command {
	private static UserOption type = UserOption.LIST;
	public ListCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		for (ShipType ship: ShipType.values()) {
			controller.printMessage(ship.getFullName()
					+ ": Points: " + ship.getAwardedPoints()
					+ ": - Harm: " + (ship.getProjectile() != null ? ship.getProjectile().getDmg() : 0) 
					+ ": - Shield: " + ship.getHp()
			);
		}
		return true;
	}

}

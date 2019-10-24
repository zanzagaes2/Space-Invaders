package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public class ShootCommand extends Command {
	private static UserOption type = UserOption.SHOOT;
	public ShootCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		game.playerShoot();
		return true;
	}
}

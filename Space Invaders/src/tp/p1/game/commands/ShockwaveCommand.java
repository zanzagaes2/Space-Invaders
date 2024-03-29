package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public class ShockwaveCommand extends Command {
	private static UserOption type = UserOption.SHOCKWAVE;
	public ShockwaveCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		game.shockWave();
		return true;
	}
}

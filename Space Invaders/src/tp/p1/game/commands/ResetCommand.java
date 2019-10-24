package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public class ResetCommand extends Command {
	private static UserOption type = UserOption.RESET;
	public ResetCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		return true;
	}
}

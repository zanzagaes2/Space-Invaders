package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public class UpdateCommand extends Command {
	private static UserOption type = UserOption.NONE;
	public UpdateCommand() {
		super(type.toString(), "", "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		return true;
	}

}

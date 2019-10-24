package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public class ExitCommand extends Command {
	private static UserOption type = UserOption.EXIT;
	public ExitCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		return null;
	}

}

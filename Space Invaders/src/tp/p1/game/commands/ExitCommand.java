package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;
import tp.p1.game.info.GameStatus;

public class ExitCommand extends Command {
	private static UserOption type = UserOption.EXIT;

	public ExitCommand() {
		super(type.toString(), type.getSymbol(), "", "");
		this.help = "Ends current game";
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		game.setGameStatus(GameStatus.GAME_EXITED);
		return true;
	}

}

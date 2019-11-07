package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;
import tp.p1.game.info.MovementDirection;

public class ShootCommand extends Command {
	private static UserOption type = UserOption.SHOOT;
	public ShootCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}
	
	public ShootCommand(String details) {
		super(type.toString(), type.getSymbol(), details, "");
	}

	
	@Override
	public boolean execute(Game game, Controller controller) {
		String noSuperMissileError = "No super missiles bought";
		String supermisil = "supermisil";
		boolean match = details.equalsIgnoreCase(supermisil);
		boolean sucess = match ? game.shootLaser(true) : game.shootLaser(false);
		if (!sucess) controller.printMessage(noSuperMissileError);
		return sucess;
	}

	@Override
	public Command parse(String[] commandWords) {
		boolean success = matchCommandName(commandWords[0]);
		Command newCommand = null;
		if (success) newCommand = new ShootCommand(commandWords.length > 1 ? commandWords[1] : "");
		return newCommand;
	}
}

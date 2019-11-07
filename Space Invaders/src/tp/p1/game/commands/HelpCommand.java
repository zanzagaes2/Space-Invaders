package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public class HelpCommand extends Command {
	private static String helpMessage = 
			"Command > help\n"
			+ "move <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
			+ "shoot: UCM-Ship launches a missile.\n"
			+ "shockWave: UCM-Ship releases a shock wave.\n"
			+ "list: Prints the list of available ships.\n"
			+ "reset: Starts a new game.\n"
			+ "help: Prints this help message.\n"
			+ "exit: Terminates the program.\n"
			+ "[none]: Skips one cycle\n";
	private static UserOption type = UserOption.HELP;

	public HelpCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		for (Command command: CommandGenerator.getAvailableCommands())
			controller.printMessage(command.getHelp());
		return true;
	}

}

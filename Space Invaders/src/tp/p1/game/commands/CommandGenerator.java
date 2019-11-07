package tp.p1.game.commands;


public class CommandGenerator {
	private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new MoveCommand(),
			new ShockwaveCommand(),
			new ShootCommand(),
			new UpdateCommand(),
			new ComprarCommand()
			};
	
	public static Command[] getAvailableCommands() {
		return availableCommands;
	}

	public static Command parseCommand (String[] commandWords) {
		Command commandRead = null;
		for (Command availableCommand: availableCommands)
			commandRead = commandRead == null ? availableCommand.parse(commandWords) : commandRead;
		return commandRead;
	}

}

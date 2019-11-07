package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;
import tp.p1.game.info.MovementDirection;

public class MoveCommand extends Command {
	private static final int maximumMovement = 2;
	private static UserOption type = UserOption.MOVE;
	public MoveCommand() {
		super(type.toString(), type.getSymbol(), "", "");
	}
	
	private MoveCommand(String details) {
		super(type.toString(), type.getSymbol(), details, "");
	}

	@Override
	public boolean execute(Game game, Controller controller) {
		String[] words = details.split(" ");
		MovementDirection direction = MovementDirection.parseDirection(words[0]);
		int ammount = Integer.parseInt(words[1]);
		
		if (direction != null)
			for (int i = 0; i < Math.min(ammount, maximumMovement); i++)
				game.move(direction);
		return direction != null;
	}

	@Override
	public Command parse(String[] commandWords) {
		boolean success = matchCommandName(commandWords[0]) && commandWords.length >= 3;
		Command newCommand = null;
		if (success) newCommand = new MoveCommand (commandWords[1] + " " + commandWords[2]);
		return newCommand;
	}
}

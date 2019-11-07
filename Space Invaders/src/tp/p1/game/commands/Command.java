package tp.p1.game.commands;

import tp.p1.game.Controller;
import tp.p1.game.Game;

public abstract class Command {
	protected static final String incorrectArgsMsg ="Incorrect argument format";
	protected static final String incorrectNumArgsMsg ="Incorrect number of arguments";
	protected final String details;
	protected String help;
	
	protected final String name;
	protected final String shortcut;
	
	protected Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details  = details;
		this.help = help;
	}
	
	public abstract boolean execute(Game game, Controller controller);
	
	public String getHelp(){
		return help;
	}
	
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) ||
				this.name.equalsIgnoreCase(name);
	}
	
	public Command parse(String[] commandWords) {
		return matchCommandName(commandWords[0]) ? this : null;
	}
}

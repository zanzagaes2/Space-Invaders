package tp.p1.game;
import java.util.Scanner;

import tp.p1.game.commands.Command;
import tp.p1.game.commands.CommandGenerator;
import tp.p1.game.commands.UserOption;
import tp.p1.game.info.GameStatus;
import tp.p1.util.GameDifficulty;

public class Controller {
	private GameDifficulty difficulty;
		
	private long seed;
	
	public Controller (GameDifficulty difficulty) {
		this(difficulty, (int) System.currentTimeMillis());
	}

	public Controller (GameDifficulty difficulty, int seed) {
		this.difficulty = difficulty;
		this.seed = seed;
	} 
	
	private void printGreeting (GameStatus gameStatus) {
		String sep = "*****************************";
		String spaces = "      ";
		String greeting = "\n\n\n" + spaces + sep + "\n"
				+ spaces + "*" + spaces + (gameStatus == GameStatus.PLAYER_WINS ? "PLAYER" : "COMPUTER")
				+ "  WINS" + spaces + "*\n"
				+ spaces + sep + "\n\n\n";
		System.out.print(greeting);
	}
	
	public void printMessage(String message) {
		System.out.println(message);
	}
	 
	public void run() {
		Game game = new Game(difficulty, seed);
		GamePrinter drawer = new GamePrinter(game);
		UserOption option = UserOption.HELP;
		GameStatus gameStatus = GameStatus.IN_COURSE;
		Scanner in = new Scanner(System.in);
		
		final String prompt = "Please enter a command: ";
		String[] words;
		Command command;
		drawer.draw();
		do {		
			System.out.print(prompt);
			words = in.nextLine().toLowerCase().trim().split ("\\s+");
			command = CommandGenerator.parseCommand(words);
			if(command != null && command.execute(game, null)) { 
                gameStatus = game.update();
				drawer.draw();
			}
		} while (gameStatus == GameStatus.IN_COURSE);
		
		drawer.draw();
		in.close();
		if (option != UserOption.EXIT) printGreeting(gameStatus);
	}

}

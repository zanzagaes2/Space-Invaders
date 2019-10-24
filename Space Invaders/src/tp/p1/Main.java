package tp.p1;
import tp.p1.game.Controller;
import tp.p1.util.GameDifficulty;
// New comment

public class Main {

	public static void main(String[] args) {
		if (args.length >= 1) {
			GameDifficulty difficulty = parseDifficulty(args[0]);
			Controller controller = null;
			if (args.length == 1)  controller = new Controller(difficulty);
			else controller = new Controller(difficulty, Integer.parseInt(args[1]));
			controller.run();
		}
		else System.out.println("Lacking arguments: introduce easy/hard/insane");
	}
	
	private static GameDifficulty parseDifficulty (String level) {
		GameDifficulty difficulty = null;
		level = level.toUpperCase();
		for (GameDifficulty posibleDifficulty: GameDifficulty.values())
			if (level.contentEquals(posibleDifficulty.toString()))
				difficulty = posibleDifficulty;
		return difficulty;
	}

}

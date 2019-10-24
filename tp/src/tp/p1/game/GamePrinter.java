package tp.p1.game;

import tp.p1.naves.BaseShip;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.util.Location;
import tp.p1.util.MyStringUtils;

class GamePrinter {
	private Game game;
	private String screen[][] = 
			new String[Game.boardDimension.getX()][Game.boardDimension.getY()];
	
	GamePrinter (Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		int numCols = Game.boardDimension.getY();
		int numRows = Game.boardDimension.getX();
		int cellSize = 10;
		int marginSize = 4;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String space = " ";


		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();
		str.append(lineDelimiter);

		for (int i = 0; i < numRows; i++) {
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < numCols; j++)
				str.append(MyStringUtils.centre(screen[i][j], cellSize)).append(vDelimiter);
			str.append(lineDelimiter);
		}
		return str.toString();
	}

	private void cleanScreen() {
		String empty = " ";
		for (int i = 0; i < Game.boardDimension.getX(); i++)
			for (int j = 0; j < Game.boardDimension.getY(); j++)
				screen[i][j] = empty ;
	}

	private void drawAt(String symbol, Location position) {
		screen[position.getX()][position.getY()] = symbol;
	}
	
	private void drawScreen() {
		int info[] = game.getGameInfo();
		
		System.out.println(
				"Life: " + info[0]
				+ "\nTurns: " + info[1]
				+ "\nPoints: " + info[2]
				+ "\nRemaining aliens: " + info[3]
				+ "\nShockwave: " + (info[4] == 1 ? "YES" : "NO")
		); 
		System.out.print(toString());
	}
	
	void draw() {
		cleanScreen();
		for (BaseProjectile bomb : game.getProjectiles())
			drawAt(bomb.toString(), bomb.getPosition());

		for (BaseShip ship : game.getShips())
			drawAt(ship.toString(), ship.getPosition());

		drawScreen();
	}
}

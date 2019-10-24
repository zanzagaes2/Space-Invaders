package tp.p1.game.info;

import tp.p1.util.Location;

public enum MovementDirection {
	DOWN(4, new Location(1, 0)), 
	LEFT(1, new Location(0, -1)), 
	RIGHT(2, new Location(0, 1)), 
	UP(3, new Location(-1, 0));

	private int code;
	private Location coordinates;

	MovementDirection(int i, Location movement) {
		code = i;
		coordinates = movement;
	}

	public int getCode() {
		return code;
	}

	public Location getCoordinates() {
		return coordinates;
	}
	
	public static MovementDirection parseDirection(String input) {
		/*Arguments:
		 * Parses first argument of the move option selected by player,
		 * if it corresponds with a valid direction (LEFT, RIGHT, UP, DOWN)
		 * it returns it. Else, it returns *null*.
		 */
		MovementDirection direction = null;
		for (MovementDirection posibleMove: MovementDirection.values())
			if (input.equalsIgnoreCase(posibleMove.toString()))
				direction = posibleMove;
		return direction;
	}
};
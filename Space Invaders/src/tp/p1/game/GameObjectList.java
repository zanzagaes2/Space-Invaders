package tp.p1.game;

import tp.p1.game.info.Entity;
import tp.p1.game.info.MovementDirection;
import tp.p1.naves.UCMShip;
import tp.p1.naves.UFOShip;
import tp.p1.util.GameDifficulty;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class GameObjectList {
	private static int initialSize = 10;
	private int counter = 0;
	private GameDifficulty difficulty;
	private Game game;
	private boolean limitReached = false;
	private GameObject list[];
	private MovementDirection movementDirection = MovementDirection.LEFT;
	private int size;
	private Boolean ufo = false;

	public GameObjectList(Game game) {
		list = new GameObject[initialSize];
		size = initialSize;
		this.game = game;
		this.difficulty = game.getDifficulty();
	}

	public GameObjectList(Game game, UCMShip player) {
		this(game);
		add(player);
	}

	public void add(GameObject newObject) {
		if (counter == size)
			this.expandList();
		list[counter++] = newObject;
	}


	private Boolean checkLimit() {
		for (GameObject object : this.iter())
			if (object.isAtLimit())
				return true;
		return false;
	}

	private void expandList() {
		GameObject newList[] = new GameObject[size * 2];
		size *= 2;

		for (int i = 0; i < counter; i++)
			newList[i] = list[i];
		list = newList;
	}
	
	private void generateUFO() {
		if (!ufo && game.randomChance(difficulty.getOvniFrequency())) {
			ufo = true;
			add(new UFOShip(game));
		}
	}
	
	public int getEnemyShipNumber() {
		int enemies = 0;
		for (int i = 0; i < counter; i++)
			if (list[i].getEntity() == Entity.SHIP) 
				enemies++;
		return enemies-1;
	}

	public MovementDirection getMovementDirection() {
		return limitReached ? MovementDirection.DOWN : movementDirection;
	}
	
	public UCMShip getPlayer() {
		return (UCMShip) list[0];
	}
	
	private boolean isTimeToMove() {
		int turn = game.getGameInfo()[1];
		return turn %  difficulty.getMovementSpeed() == 0 ||
				limitReached;
	}
	
	public GameObject[] iter() {
		GameObject copyList[] = new GameObject[counter];
		for (int i = 0; i < counter; i++)
			copyList[i] = list[i];
		return copyList;
	}
	
	public GameObjectList objectsAt(Location position) {
		GameObjectList newList = new GameObjectList(game);
		for (GameObject object: this.iter())
			if (object.getPosition().equals(position))
				newList.add(object);
		return newList;
	}

	
	void passTurn() {
		limitReached = checkLimit();
		if (limitReached) swapDirection();
		
		for(int i = 0; i < counter; i++)
			list[i].passTurn(isTimeToMove());
		generateUFO();
		processEvents(update());

	}
	
	private void processEvents(GameEventList events) {
		for (GameEvent event: events.iter())
			GameEvent.processEvent(game, event);
	}

	public void setUFO(boolean b) {
		ufo = b;
	}

	private void swapDirection() {
		movementDirection = (movementDirection == MovementDirection.LEFT ? 
				MovementDirection.RIGHT :	MovementDirection.LEFT);
	}

	public GameEventList update() {
		int i = 0;
		GameEventList events = new GameEventList();
		while (i < counter)  {
			list[i].update(events);
			if (!list[i].isValid() && i > 0) list[i] = list[--counter];
			else i++;
		}
		if (getEnemyShipNumber() == 0) events.add(GameEvent.NO_ENEMY_LEFT);
		return events;
	}

}

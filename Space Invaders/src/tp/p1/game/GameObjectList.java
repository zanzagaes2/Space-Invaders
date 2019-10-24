package tp.p1.game;

import tp.p1.naves.UCMShip;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class GameObjectList {
	private static int initialSize = 10;
	private int counter = 0;
	private GameObject list[];
	private int size;

	public GameObjectList() {
		list = new GameObject[initialSize];
		size = initialSize;
	}

	public GameObjectList(UCMShip player) {
		this();
		add(player);
	}

	public void add(GameObject newObject) {
		if (counter == size)
			this.expandList();
		list[counter++] = newObject;
	}

	private void deleteAt(int pos) {
		int i = pos;
		while (i < counter - 1) {
			list[i] = list[i + 1];
			i++;
		}
		counter--;
	}


	private void expandList() {
		GameObject newList[] = new GameObject[size * 2];
		size *= 2;

		for (int i = 0; i < counter; i++)
			newList[i] = list[i];
		list = newList;
	}

	public int getLength() {
		return counter;
	}
	
	public UCMShip getPlayer() {
		return (UCMShip) list[0];
	}
	
	public GameObject[] iter() {
		GameObject copyList[] = new GameObject[counter];
		for (int i = 0; i < counter; i++)
			copyList[i] = list[i];
		return copyList;
	}

	public GameObject[] iterEnemies() {
		GameObject copyList[] = new GameObject[counter-1];
		for (int i = 0; i < counter-1; i++)
			copyList[i] = list[i+1];
		return copyList;
	}

	public GameObjectList objectsAt(Location position) {
		GameObjectList newList = new GameObjectList();
		for (GameObject object: this.iter())
			if (object.getPosition().equals(position))
				newList.add(object);
		return newList;
	}
	
	public GameEventList update() {
		int i = 0;
		GameEventList events = new GameEventList();
		while (i < counter)  {
			list[i].update(events);
			if (!list[i].isValid() && i > 0) // Don't delete element 0: player ship
				deleteAt(i);
			else i++;
		}
		if (counter == 1) events.add(GameEvent.NO_ENEMY_LEFT);	// Esto hay que hacerlo bien
		return events;
	}
}

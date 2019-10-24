package tp.p1.game;

import tp.p1.game.info.Entity;
import tp.p1.game.info.Faction;
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


	private void expandList() {
		GameObject newList[] = new GameObject[size * 2];
		size *= 2;

		for (int i = 0; i < counter; i++)
			newList[i] = list[i];
		list = newList;
	}

	public int getEnemyShipNumber() {
		int enemies = 0;
		for (int i = 0; i < counter; i++)
			if (list[i].getFaction() == Faction.ENEMY && list[i].getEntity() == Entity.SHIP) 
				enemies++;
		return enemies;
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
			if (!list[i].isValid() && i > 0) list[i] = list[--counter];
			else i++;
		}
		if (getEnemyShipNumber() == 0) events.add(GameEvent.NO_ENEMY_LEFT);
		return events;
	}
}

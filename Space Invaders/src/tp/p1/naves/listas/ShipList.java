package tp.p1.naves.listas;

import tp.p1.naves.BaseShip;
import tp.p1.naves.UCMShip;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;

public class ShipList {
	private static int initialSize = 10;
	private BaseShip list[];
	private int counter = 0;
	private int size;

	public ShipList(UCMShip player) {
		list = new BaseShip[initialSize];
		size = initialSize;
		add(player);
	}

	public void add(BaseShip newShip) {
		if (counter == size)
			this.expandList();
		list[counter++] = newShip;
	}

	private void expandList() {
		BaseShip newList[] = new BaseShip[size * 2];
		size *= 2;

		for (int i = 0; i < counter; i++)
			newList[i] = list[i];
		list = newList;
	}


	public UCMShip getPlayer() {
		return (UCMShip) list[0];
	}

	public int getLength() {
		return counter;
	}
	
	public BaseShip[] iter() {
		BaseShip copyList[] = new BaseShip[counter];
		for (int i = 0; i < counter; i++)
			copyList[i] = list[i];
		return copyList;
	}
	
	public BaseShip[] iterEnemies() {
		BaseShip copyList[] = new BaseShip[counter-1];
		for (int i = 0; i < counter-1; i++)
			copyList[i] = list[i+1];
		return copyList;
	}

	public GameEventList update() {
		int i = 0;
		GameEventList events = new GameEventList();
		while (i < counter)  {
			events.concat(list[i].update());
			if (!list[i].isValid() && i > 0) // Don't delete element 0: player ship
				deleteAt(i);
			else i++;
		}
		if (counter == 1) events.add(GameEvent.NO_ENEMY_LEFT);
		return events;
	}

	private void deleteAt(int pos) {
		int i = pos;
		while (i < counter - 1) {
			list[i] = list[i + 1];
			i++;
		}
		counter--;
	}
}

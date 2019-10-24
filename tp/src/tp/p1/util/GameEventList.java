package tp.p1.util;

public class GameEventList {
	private static int initialSize = 10;
	private GameEvent list[];
	private int counter = 0;
	private int size;

	public GameEventList() {
		list = new GameEvent[initialSize];
		size = initialSize;
	}

	public void add(GameEvent gameEvent) {
		if (counter == size)
			this.expandList();
		list[counter++] = gameEvent;
	}
	
	public void concat(GameEventList list2) {
		for (GameEvent event: list2.iter())
			add(event);
	}
	
	private void expandList() {
		GameEvent newList[] = new GameEvent[size * 2];
		size *= 2;

		for (int i = 0; i < counter; i++)
			newList[i] = list[i];
		list = newList;
	}

	public int getLength() {
		return counter;
	}
	
	public GameEvent[] iter() {
		GameEvent copyList[] = new GameEvent[counter];
		for (int i = 0; i < counter; i++)
			copyList[i] = list[i];
		return copyList;
	}

}

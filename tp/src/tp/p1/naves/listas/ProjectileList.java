package tp.p1.naves.listas;

import tp.p1.naves.proyectiles.BaseProjectile;

public class ProjectileList {
	private static int initialSize = 10;
	private BaseProjectile list[];
	private int counter = 0;
	private int size;

	public ProjectileList() {
		list = new BaseProjectile[initialSize];
		size = initialSize;
	}

	public void add(BaseProjectile newProjectile) {
		if (counter == size) 	this.expandList();
		list[counter++] = newProjectile;
	}

	private void expandList() {
		BaseProjectile newList[] = new BaseProjectile[size * 2];
		for (int i = 0; i < counter; i++) newList[i] = list[i];
		list = newList;
	}

	public BaseProjectile[] iter() {
		BaseProjectile copyList[] = new BaseProjectile[counter];
		for (int i = 0; i < counter; i++)
			copyList[i] = list[i];
		return copyList;
	}

	public void update() {
		for (int i = 0; i < counter; i++)
			if (!list[i].isInBoard()) {
				list[i].informOwner();
				deleteAt(i);
			}
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

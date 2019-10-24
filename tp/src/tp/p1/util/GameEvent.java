package tp.p1.util;

public enum GameEvent {
	NO_ENEMY_LEFT, 
	OVNI_DESTROYED,
	PLAYER_KILLED,
	POINTS_EARNED;
	
	private int quantity = 0;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

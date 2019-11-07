package tp.p1.util;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import tp.p1.game.Game;
import tp.p1.game.info.GameStatus;
public enum GameEvent {
	ENEMY_REACHED_FIRST_LINE, 
	NO_ENEMY_LEFT,
	OVNI_DESTROYED,
	OVNI_OUT_OF_SCREEN,
	PLAYER_KILLED,
	POINTS_EARNED;
	
	static Map<GameEvent, BiConsumer<Game, GameEvent>> functions = new HashMap<>();

	static {
		functions.put(ENEMY_REACHED_FIRST_LINE, (Game game, GameEvent event) -> game.setGameStatus(GameStatus.COMPUTER_WINS) );
		functions.put(NO_ENEMY_LEFT, (Game game, GameEvent event) -> game.setGameStatus(GameStatus.PLAYER_WINS) );
		functions.put(PLAYER_KILLED, (Game game, GameEvent event) -> game.setGameStatus(GameStatus.COMPUTER_WINS) );
		functions.put(POINTS_EARNED, (Game game, GameEvent event) -> game.receivePoints(event.getQuantity()) );
		functions.put(OVNI_OUT_OF_SCREEN, (Game game, GameEvent event) -> game.getObjects().setUFO(false) );
		functions.put(OVNI_DESTROYED, (Game game, GameEvent event) -> {game.getObjects().setUFO(false); game.setShockwave(true);});
	}

	public static void processEvent(Game game, GameEvent event) {
		functions.get(event).accept(game, event);
	}
	
	private int quantity = 0;
	public int getQuantity() {
		return quantity;
	};
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

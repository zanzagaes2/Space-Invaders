package tp.p1.game;

import java.util.Random;

import tp.p1.game.info.Entity;
import tp.p1.game.info.GameStatus;
import tp.p1.game.info.MovementDirection;
import tp.p1.game.info.ProjectileType;
import tp.p1.game.info.ShipType;
import tp.p1.naves.DestroyerShip;
import tp.p1.naves.RegularShip;
import tp.p1.naves.UCMShip;
import tp.p1.naves.UFOShip;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.util.GameDifficulty;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class Game {
	public static Location boardDimension = new Location(8, 9);

	private GameDifficulty difficulty;
	private GameStatus gameStatus = GameStatus.IN_COURSE;

	private Random generator;
	private boolean limitReached = false;
	private MovementDirection movementDirection = MovementDirection.LEFT;
	private GameObjectList objects;
	
	private UCMShip player;
	private int points = 0;

	private int turn = 0;
	private Boolean ufo = false;
	
	Game(GameDifficulty difficulty, long seed) {
		objects = new GameObjectList(new UCMShip(this));
		player = objects.getPlayer();
		this.difficulty = difficulty;
		this.generator = new Random(seed);
		initGame();
	}

	public void addPoints(int quantity) {
		points += quantity;
	}

	public void addProjectile(BaseProjectile newProjectile) {
		objects.add(newProjectile);
	}

	 private Boolean checkLimit() {
		for (GameObject object : objects.iter())
			if (object.isAtLimit())
				return true;
		return false;
	}

	private void generateUFO() {
			if (!ufo && randomChance(difficulty.getOvniFrequency())) {
				ufo = true;
				objects.add(new UFOShip(this));
			}
		}
	 
	 public GameDifficulty getDifficulty() {
		return difficulty;
	}
	 
	 int[] getGameInfo() {
		/*
		 * Returns an array with information of the game:
		 * numbers of ships, points achieved, current turn,
		 * hp of the player and availability of Shockwave (1 = True, 0 = False)
		 */
		int info[] = {
				player.getHp(),
				turn,
				points,
				objects.getEnemyShipNumber()-1,
				player.getShockwave() ? 1 :0
		};
		return info;
	}

	public MovementDirection getMovementDirection() {
		return limitReached ? MovementDirection.DOWN : movementDirection;
	}
	
	
	public GameObjectList getObjects() {
		return objects;
	}

	private void initGame() {
			String map = difficulty.getMap();
			for (int i = 0; i < Game.boardDimension.getX(); i++)
				for (int j = 0; j < Game.boardDimension.getY(); j++) {
					String symbol = "" + map.charAt(i*boardDimension.getY() + j);
					if (symbol.equals(ShipType.REGULAR.getMapSymbol()))
						objects.add(new RegularShip (this, new Location (i,j)));
					else if (symbol.equals(ShipType.DESTROYER.getMapSymbol()))
						objects.add(new DestroyerShip (this, new Location (i,j)));
				}				
		}
	
	public Boolean isAtLimit(GameObject ship) {
		return ship.getPosition().getY() == Game.boardDimension.getY() - 1
				&& movementDirection == MovementDirection.RIGHT
				|| ship.getPosition().getY() == 0 && 
				movementDirection == MovementDirection.LEFT;
				
	}
	
	public boolean isTimeToMove() {
		return turn %  difficulty.getMovementSpeed() == 0 ||
				limitReached;
	}

	public void movePlayer(MovementDirection direction) {
		player.move(direction.getCoordinates());
	}

	public void playerShoot() {
		player.shoot();
	}
	
	private void processEvents(GameEventList events) {
		for (GameEvent event: events.iter())
			GameEvent.processEvent(this, event);
	}
	
	public Boolean randomChance(double probability) {
		return generator.nextFloat() < probability;
	}

	public void shockwave() {
		if (player.getShockwave())
			for (GameObject object: objects.iter())
				if (object.getEntity() == Entity.SHIP && object.getFaction() != player.getFaction())
					object.sufferHit(ProjectileType.SHOCKWAVE.getDmg());
		player.setShockwave(false);
		processEvents(objects.update());
	}
	
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public void setShockwave(boolean b) {
		objects.getPlayer().setShockwave(b);	
	}

	public void setUFO(boolean b) {
		ufo = b;
	}

	private void swapDirection() {
		movementDirection = (movementDirection == MovementDirection.LEFT ? 
				MovementDirection.RIGHT :	MovementDirection.LEFT);
	}

	GameStatus update() {
		limitReached = checkLimit();
		if (limitReached) swapDirection();
		
		for (GameObject object : objects.iter()) 
				object.passTurn();
		generateUFO();
		processEvents(objects.update());
		turn++;
		return gameStatus;
	}
}

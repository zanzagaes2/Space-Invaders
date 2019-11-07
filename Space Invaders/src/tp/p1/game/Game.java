package tp.p1.game;

import java.util.Random;

import tp.p1.game.info.GameStatus;
import tp.p1.game.info.MovementDirection;
import tp.p1.naves.UCMShip;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Shockwave;
import tp.p1.util.GameDifficulty;
import tp.p1.util.Location;

public class Game implements IPlayerController{
	public static Location boardDimension = new Location(8, 9);

	private GameDifficulty difficulty;
	private GameStatus gameStatus = GameStatus.IN_COURSE;

	private Random generator;
	private GameObjectList objects;
	
	private UCMShip player;
	private int points;
	private int turn;
	
	Game(GameDifficulty difficulty, long seed) {
		this.difficulty = difficulty;
		this.generator = new Random(seed);
		setNewGame();
	}
	
	public void setNewGame() {
		GameInitializer initializer = new GameInitializer();
		objects = initializer.initialize(difficulty, this);
		player = objects.getPlayer();
		points = 0;
		turn = 0;
		setShockwave(false);
	}


	public void addProjectile(BaseProjectile newProjectile) {
		objects.add(newProjectile);
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
				player.getShockwave() ? 1 :0,
				player.getSuperMissileNumber()
		};
		return info;
	}
	
	 public GameObjectList getObjects() {
		return objects;
	}
	 
	 public Boolean isAtLimit(GameObject ship) {
		return ship.getPosition().getY() == Game.boardDimension.getY() - 1
				&& objects.getMovementDirection() == MovementDirection.RIGHT
				|| ship.getPosition().getY() == 0 && 
				objects.getMovementDirection() == MovementDirection.LEFT;
				
	}	
	
	public boolean move(MovementDirection direction) {
		player.move(direction.getCoordinates());
		return true;
	}

	public Boolean randomChance(double probability) {
		return generator.nextFloat() < probability;
	}
		
	public void receivePoints(int quantity) {
		points += quantity;
	}
	
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public void setShockwave(boolean b) {
		objects.getPlayer().setShockwave(b);	
	}
	
	public boolean shockWave() {
		boolean success = player.getShockwave();
		if (success)
			addProjectile(new Shockwave(this, player));
		return success;
	}
	
	public boolean shootLaser(boolean superMissile) {
		boolean sucess = true;
		if (superMissile && player.getSuperMissileNumber() == 0) sucess = false;
		else player.shoot(superMissile);
		return sucess;
	}

	GameStatus update() {
		objects.passTurn();
		turn++;
		return gameStatus;
	}

	@Override
	public boolean buySuperMissile() {
		int superMissileCost = difficulty.getSuperMissileCost();
		boolean exito = false;
		if (points >= superMissileCost) {
			player.addSuperMissile();
			points -= superMissileCost;
			exito = true;
		}
		return exito;
	}
}

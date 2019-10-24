package tp.p1.game;

import java.util.Random;

import tp.p1.game.info.GameStatus;
import tp.p1.game.info.MovementDirection;
import tp.p1.game.info.ProjectileType;
import tp.p1.game.info.ShipType;
import tp.p1.naves.BaseShip;
import tp.p1.naves.DestroyerShip;
import tp.p1.naves.RegularShip;
import tp.p1.naves.UCMShip;
import tp.p1.naves.UFOShip;
import tp.p1.naves.listas.ProjectileList;
import tp.p1.naves.listas.ShipList;
import tp.p1.naves.proyectiles.BaseProjectile;
import tp.p1.naves.proyectiles.Missile;
import tp.p1.util.GameDifficulty;
import tp.p1.util.GameEvent;
import tp.p1.util.GameEventList;
import tp.p1.util.Location;

public class Game {
	public static Location boardDimension = new Location(8, 9);

	private GameDifficulty difficulty;
	private GameStatus gameStatus = GameStatus.IN_COURSE;
	private Random generator;
	private MovementDirection movementDirection = MovementDirection.LEFT;

	private UCMShip player;
	private int points = 0;
	
	private ProjectileList projectiles;
	private ShipList ships;
	private int turn = 0;

	private Boolean ufo = false;

	Game(GameDifficulty difficulty, long seed) {
		projectiles = new ProjectileList();
		ships = new ShipList(new UCMShip());
		player = ships.getPlayer();
		this.difficulty = difficulty;
		this.generator = new Random(seed);
		initGame(difficulty);
	}

	 private Boolean checkLimit() {
		for (BaseShip enemy : ships.iterEnemies())
			if (enemy.getType() != ShipType.OVNI && isAtLimit(enemy))
				return true;
		return false;
	}

	private void checkProjectileImpact() {
		for (BaseProjectile projectile: projectiles.iter())
			for (BaseProjectile projectile2: projectiles.iter())
				if (projectile != projectile2 &&
				projectile.getPosition().equals(projectile2.getPosition())) {
					projectile.markUnboard();
					projectile2.markUnboard();
					projectile.informOwner();
					projectile2.informOwner();
				}
		projectiles.update();
		processEvents(ships.update());
		
		for (BaseProjectile projectile: projectiles.iter())
			for (BaseShip ship: ships.iter())
				if (projectile.getPosition().equals(ship.getPosition()) &&
						isEnemyFire(projectile, ship)) {	
					ship.sufferHit(projectile.getDmg());
					projectile.markUnboard();
					projectile.informOwner();
				}
		projectiles.update();
		processEvents(ships.update());
	}
	
	private void checkShipsInFirstLine() {
		for (BaseShip enemy : ships.iterEnemies())
			if (enemy.getPosition().getX() == Game.boardDimension.getX() - 1 )
				gameStatus = GameStatus.COMPUTER_WINS;
	}

	private void generateUFO() {
		if (!ufo && randomChance(difficulty.getOvniFrequency())) {
			ufo = true;
			ships.add(new UFOShip());
		}
	}
	
	private void initGame(GameDifficulty difficulty) {
		String map = difficulty.getMap();
		for (int i = 0; i < Game.boardDimension.getX(); i++)
			for (int j = 0; j < Game.boardDimension.getY(); j++) {
				String symbol = "" + map.charAt(i*boardDimension.getY() + j);
				if (symbol.equals(ShipType.REGULAR.getMapSymbol()))
					ships.add(new RegularShip (new Location (i,j)));
				else if (symbol.equals(ShipType.DESTROYER.getMapSymbol()))
					ships.add(new DestroyerShip (new Location (i,j)));
			}				
	}
	
	private Boolean isAtLimit(BaseShip ship) {
		return ship.getPosition().getY() == Game.boardDimension.getY() - 1
				&& movementDirection == MovementDirection.RIGHT
				|| ship.getPosition().getY() == 0 && 
				movementDirection == MovementDirection.LEFT;
				
	}
	
	private Boolean isEnemyFire(BaseProjectile projectile, BaseShip ship) {
		return projectile.getType() ==  ProjectileType.PROJECTILE && 
				ship.getType() == ShipType.UCM ||
				projectile.getType() ==  ProjectileType.MISSILE && 
				ship.getType() != ShipType.UCM;
	}
	
	private void moveShips() {
		Boolean limitReached = checkLimit();
		if (limitReached) swapDirection();

		Location move = limitReached ? MovementDirection.DOWN.getCoordinates()
					: movementDirection.getCoordinates();
		
		for (BaseShip enemy : ships.iterEnemies()) 
				enemy.move(move, turn %  difficulty.getMovementSpeed() == 0 || limitReached);
		ships.update();
	}

	private void processEvents(GameEventList events) {
		for (GameEvent event: events.iter())
			if (event == GameEvent.OVNI_DESTROYED) {
				ufo = false; 
				player.setShockwave(true);
			}
			else if(event == GameEvent.NO_ENEMY_LEFT)
				gameStatus = GameStatus.PLAYER_WINS;
			else if (event == GameEvent.POINTS_EARNED)
				points += event.getQuantity();
			else if (event == GameEvent.PLAYER_KILLED)
				gameStatus = GameStatus.COMPUTER_WINS;
	}
	
	private Boolean randomChance(double probability) {
		return generator.nextFloat() < probability;
	}

	private void shootProjectiles() {
		for (BaseShip enemy : ships.iterEnemies()) {
			if (randomChance(difficulty.getShootFrequency())) {
				BaseProjectile newProjectile = enemy.shoot();
				if (newProjectile != null) {
					projectiles.add(newProjectile);
					newProjectile.move();
				}
			}
		}
	}
	
	private void swapDirection() {
		movementDirection = (movementDirection == MovementDirection.LEFT ? 
				MovementDirection.RIGHT :	MovementDirection.LEFT);
	}

	private void updateProjectiles() {
		for (BaseProjectile bomb : projectiles.iter()) {
			bomb.move();
			checkProjectileImpact();
		}
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
				ships.getLength()-1,
				player.getShockwave() ? 1 :0
		};
		return info;
	}

	BaseProjectile[] getProjectiles() {
		return projectiles.iter();
	}

	BaseShip[] getShips() {
		return ships.iter();
	}
	
	public void movePlayer(MovementDirection direction) {
		player.move(direction.getCoordinates());
	}
	
	public void playerShoot() {
		Missile newMissile = player.shoot();
		if (newMissile != null) projectiles.add(newMissile);
	}

	public void shockwave() {
		if (player.getShockwave())
			for (BaseShip enemies: ships.iterEnemies())
				enemies.sufferHit(ProjectileType.SHOCKWAVE.getDmg());
		processEvents(ships.update());
		player.setShockwave(false);
	}

	GameStatus update() {
		updateProjectiles();		
		checkProjectileImpact();
		moveShips();
		shootProjectiles();
		checkProjectileImpact();
		
		generateUFO();
		checkShipsInFirstLine();
		turn++;
		return gameStatus;
	}
}

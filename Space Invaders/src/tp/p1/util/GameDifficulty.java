package tp.p1.util;

public enum GameDifficulty {
	EASY(0.1, 3, 0.1, Maps.easyMap, 0.05, 20), 
	HARD(0.3, 2, 0.2, Maps.hardMap, 0.05, 20), 
	INSANE(0.5, 1, 0.1, Maps.insaneMap, 0.05, 20);

	
	private String map;
	private int movementSpeed;
	private double ovniFrequency;
	private double shootFrequency;
	private double explosiveTransformationFrequency;
	private int superMissileCost;

	private GameDifficulty(double shootFrequency, int movementSpeed,
			double ovniFrequency, String map, double explosiveTransformationFrequency,
			int superMissileCost) 
	{
		this.shootFrequency = shootFrequency;
		this.movementSpeed = movementSpeed;
		this.ovniFrequency = ovniFrequency;
		this.map = map;
		this.explosiveTransformationFrequency = explosiveTransformationFrequency;
		this.superMissileCost = superMissileCost;
	}

	public double getExplosiveTransformationFrequency() {
		return explosiveTransformationFrequency;
	}

	public String getMap() {
		return map;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public double getOvniFrequency() {
		return ovniFrequency;
	}

	public double getShootFrequency() {
		return shootFrequency;
	}

	public int getSuperMissileCost() {
		return superMissileCost;
	}
}

class Maps {
	static String easyMap = 
			"---------" +
			"---RRRR--" +
			"----DD---" +
			"---------" +
			"---------" +
			"---------" +
			"---------" +
			"---U-----";
	
	static String hardMap = 
			"---------" +
			"---RRRR--" +
			"---RRRR--" +
			"----DD---" +
			"---------" +
			"---------" +
			"---------" +
			"---U-----";
	
	static String insaneMap = 
			"---------" +
			"---RRRR--" +
			"---RRRR--" +
			"---DDDD--" +
			"---------" +
			"---------" +
			"---------" +
			"---U-----";
};

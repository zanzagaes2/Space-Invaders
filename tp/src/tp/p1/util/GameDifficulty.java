package tp.p1.util;

public enum GameDifficulty {
	EASY(0.1, 3, 0.1, Maps.easyMap), 
	HARD(0.3, 2, 0.2, Maps.hardMap), 
	INSANE(0.5, 1, 0.1, Maps.insaneMap);

	
	private String map;
	private int movementSpeed;
	private double ovniFrequency;
	private double shootFrequency;

	private GameDifficulty(double shootFrequency, int movementSpeed,
			double ovniFrequency, String map) 
	{
		this.shootFrequency = shootFrequency;
		this.movementSpeed = movementSpeed;
		this.ovniFrequency = ovniFrequency;
		this.map = map;
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

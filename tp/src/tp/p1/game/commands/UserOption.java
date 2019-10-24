package tp.p1.game.commands;

public enum UserOption {
	EXIT("E"), 
	HELP("H"), 
	LIST("L"), 
	MOVE("M"), 
	NONE("N"), 
	RESET("R"), 
	SHOCKWAVE("W"), 
	SHOOT("S"); 

	private String symbol;
	
	private UserOption(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

}

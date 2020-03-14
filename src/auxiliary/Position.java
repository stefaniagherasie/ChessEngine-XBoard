package auxiliary;

public class Position {
	private int letter, number;
	
	/**
	 * Constructor that converts a string to 2 numbers
	 * that convey a position on the board
	 * @param p the position in string format
	 */
	public Position(String p) {	
		letter = p.charAt(0) - 97;
		number = p.charAt(1) - 49;
	}
	
	public Position(int a, int b) {
		letter = a;
		number = b;
	}
	
	public Position(Position p, int a, int b) {
		letter = p.letter + a;
		number = p.number + b;
	}

	/**
	 * Checks if current position is legal
	 * @return legality of the position
	 */
	public final boolean legalPosition() {
		if (letter > 7 || letter < 0)
			return false;
		if (number > 7 || number < 0)
			return false;
		return true;
	}
	
	/**
	 * Checks if an other position is legal
	 * @return legality of the position
	 */
	public static final boolean legalPosition(String s) {
		Position p = new Position(s);
		return legalPosition(p);
	}
	
	/**
	 * Checks if an other position (written in string format) is legal
	 * @return legality of the position
	 */
	public static final boolean legalPosition(Position p) {
		if (p.letter > 7 || p.letter < 0)
			return false;
		if (p.number > 7 || p.number < 0)
			return false;
		return true;
	}
	
	public int getLetter() {
		return letter;
	}

	public void setLetter(int letter) {
		this.letter = letter;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Position [letter=" + (char)(letter + 97) + ", number=" + (char)(number + 49) + "]";
	}
}
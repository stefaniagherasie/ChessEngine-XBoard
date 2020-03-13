package auxiliary;

public class Position {
	int letter, number;
	String pos;
	
	public Position(String p) {		
		number = Integer.parseInt(String.valueOf(p.charAt(0) - 97));
		letter = Integer.parseInt(String.valueOf(p.charAt(1)));
		pos = p;
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

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}
	
	
}

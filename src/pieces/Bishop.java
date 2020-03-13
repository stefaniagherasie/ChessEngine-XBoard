package pieces;
import auxiliary.Position;
import main.*;

public class Bishop implements Piece {
	private Position pos;
	private boolean color;
	
	public Bishop (String color, String position) {
		pos = new Position(position);
		if (color.equals("white"))
			this.color = true;
		else
			this.color = false;
	}

	@Override
	public boolean verifyMove(String move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String move() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getColor() {
		return color;
	}

}

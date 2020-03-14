package pieces;
import java.util.ArrayList;

import auxiliary.Position;
import main.*;

public class Bishop extends AbstractPiece {
	
	public Bishop (String color, String position) {
		super(color, position);
	}

	@Override
	public void move(Position pos) {
		if (verifyMove(pos))
			super.pos = pos;
	}

	@Override
	public boolean verifyMove(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}

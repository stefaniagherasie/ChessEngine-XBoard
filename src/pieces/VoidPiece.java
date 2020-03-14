package pieces;

import java.util.ArrayList;

import auxiliary.Position;

/**
 * represents an empty place on the board
 * not to be confused with null
 */
public class VoidPiece extends AbstractPiece{
	
	public VoidPiece() {
		super(null, null);
	}

	@Override
	public boolean verifyMove(Position pos) {
		return false;
	}

	@Override
	public void move(Position pos) {
		
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		return null;
	}

}

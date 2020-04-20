package pieces;

import java.util.ArrayList;

import auxiliary.Position;

/**
 * represents an empty place on the board
 * not to be confused with null
 */
public class VoidPiece extends AbstractPiece{
	
	public VoidPiece(String pos) {
		super(null, pos);
		score = 0;
	}

	@Override
	public boolean verifyMove(Position pos) {
		return false;
	}

	@Override
	public void move(Position pos) {}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		return null;
	}
	
	public void resetScore() {
		score = 0;
	}
	
	public void incScore() {
		score++;
	}
	
	public void decScore() {
		score--;
	}
}

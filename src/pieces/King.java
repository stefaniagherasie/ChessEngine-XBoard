package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class King extends AbstractPiece {
	
	public King (String color, String position) {
		super(color, position);
	}

	@Override
	public boolean verifyMove(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void move(Position newPos) {
		ChessBoard b = ChessBoard.getInstance();
		b.setPiece(pos, new VoidPiece());
		b.setPiece(newPos, this);
		pos = newPos;
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}

package pieces;
import main.*;
import java.util.ArrayList;

import auxiliary.Position;

public class Pawn extends AbstractPiece{
	private boolean moved;
	private final int D;
	
	public Pawn (String color, String position) {
		super(color, position);
		if (super.getColor() == true) {
			D = 1;
		} else {
			D = -1; 
		}
		moved = false;
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		possibleMoves = new ArrayList<Position>();
		
		if (verifyMove(new Position(pos, 0, D*2))) {
			possibleMoves.add(new Position(pos, 0, D*2));
		}
		
		if (verifyMove(new Position(pos, 0, D*1))) {
			possibleMoves.add(new Position(pos, 0, D*1));
		}
		
		if (verifyMove(new Position(pos, D*1, D*1))) {
			possibleMoves.add(new Position(pos, D*1, D*1));
		}
		
		if (verifyMove(new Position(pos, D*(-1), D*1))) {
			possibleMoves.add(new Position(pos, D*(-1), D*1));
		}
		
		return possibleMoves;
	}
	
	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		if (moved == false && 
				board.getPiece(newPos) instanceof VoidPiece &&
				board.getPiece(new Position(newPos, 0, -D)) instanceof VoidPiece &&
				pos.getLetter() == newPos.getLetter()) {
			return true;
		}
		
		if (board.getPiece(newPos) != null &&
				!(board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).getColor() != super.getColor() &&
				pos.getLetter() != newPos.getLetter()) {
			return true;
		}
		
		if ((board.getPiece(newPos) instanceof VoidPiece) &&
				pos.getLetter() == newPos.getLetter() &&
				pos.getNumber() == newPos.getNumber() - D) {
			return true;
		}
		
		return false;
	}

	public void resetMoved() {
		moved = false;
	}
	
	@Override
	public void move(Position newPos) {
		moved = true;
		super.move(newPos);
	}
}

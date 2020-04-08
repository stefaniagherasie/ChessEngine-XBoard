package pieces;
import main.*;
import java.util.ArrayList;

import auxiliary.Position;

public class Pawn extends AbstractPiece{
	private boolean moved = false;
	private final int D;
	
	public Pawn (String color, String position) {
		super(color, position);
		if (super.getColor() == true) {
			D = 1;
		} else {
			D = -1; 
		}
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
		
		if (verifyMove(new Position(pos, (-1), D*(-1)))) {
			possibleMoves.add(new Position(pos, D*(-1), D*1));
		}
		
		return possibleMoves;
	}
	
	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		if (moved == false && 
				board.getPiece(newPos) instanceof VoidPiece &&
				board.getPiece(new Position(newPos, 0, -D)) instanceof VoidPiece) {
			return true;
		}
		
		if (board.getPiece(newPos) instanceof VoidPiece) {
			return true;
		}
		
		if (board.getPiece(newPos) != null &&
				!(board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).color != super.getColor()) {
			return true;
		}
		
		if (board.getPiece(newPos) != null &&
				!(board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).color !=super.getColor()) {
			return true;
		}
		
		return false;
	}

	@Override
	public void move(Position newPos) {
		ChessBoard b = ChessBoard.getInstance();
		b.setPiece(pos, new VoidPiece());
		b.setPiece(newPos, this);
		pos = newPos;
		if (moved == false)
			moved = true;
	}
}

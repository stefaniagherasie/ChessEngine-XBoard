package pieces;
import main.*;
import java.util.ArrayList;

import auxiliary.Position;

public class Pawn extends AbstractPiece{
	public boolean moved;
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
			board.getPiece(newPos).decSafety();
			return true;
		}
		
		if (board.getPiece(newPos) != null &&
				!(board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).getColor() == super.getColor() &&
				pos.getLetter() != newPos.getLetter()) {
			board.getPiece(newPos).incSafety();
			return false;
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
		ChessBoard board = ChessBoard.getInstance();
		
		/* Promote Pawn to white Queen if it reaches the end of the board  */
			if(super.getColor() && newPos.getNumber() == 7) {
			board.setPiece(pos, new VoidPiece(pos.toString()));
			board.recordMove(board.getPiece(newPos), pos);
			board.setPiece(newPos, new Queen("white", newPos.toString(), true));
		}
		/* Promote Pawn to black Queen if it reaches the end of the board */
		else if((!super.getColor()) && newPos.getNumber() == 0) {
			board.setPiece(pos, new VoidPiece(pos.toString()));
			board.recordMove(board.getPiece(newPos), pos);
			board.setPiece(newPos, new Queen("black", newPos.toString(), true));
		}
		else {
			super.move(newPos);
		}

		moved = true;
	}

	@Override
	public void resetScore() {
		score = 1;
	}
}

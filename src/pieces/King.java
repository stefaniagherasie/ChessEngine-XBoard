package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class King extends AbstractPiece {
	private boolean moved;
	
	public King (String color, String position) {
		super(color, position);
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {

		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				//cazul [0][0] nu se poate
				if(i == 0 && j == 0)
					continue;
				Position posToCheck = new Position(pos, i, j);

				if(board.getPiece(posToCheck) != null && verifyMove(posToCheck)) {
					possibleMoves.add(posToCheck);
				}
			}
		}

		if (verifyMove(new Position(pos, -2, 0))) {
			possibleMoves.add(new Position(pos, -2, 0));
		}
		
		if (verifyMove(new Position(pos, 3, 0))) {
			possibleMoves.add(new Position(pos, 3, 0));
		}

		return possibleMoves;
	}
	
	private void castling(Position pos) {
		ChessBoard board = ChessBoard.getInstance();
		
		/* kingside castling */
		if (pos.getLetter() == 1) {
			/* moving the rook */
			Position corner = new Position(0, pos.getNumber());
			Rook r = (Rook)board.getPiece(corner);
			r.pos = new Position(pos, 1, 0);
			board.setPiece(r.getPosition(), r);
			board.setPiece(corner, new VoidPiece(corner.toString()));
			
			/* moving the king */
			board.setPiece(pos, this);
			board.setPiece(super.getPosition(), new VoidPiece(super.getPosition().toString()));

			board.recordMove(this, super.pos);
			super.pos = pos;
		} else 
			/* queenside castling */
		{
			/* moving the rook */
			Position corner = new Position(7, pos.getNumber());
			Rook r = (Rook)board.getPiece(corner);
			r.pos = new Position(pos, -1, 0);
			board.setPiece(r.getPosition(), r);
			board.setPiece(corner, new VoidPiece(corner.toString()));
			
			/* moving the king */
			board.setPiece(pos, this);
			board.setPiece(super.getPosition(), new VoidPiece(super.getPosition().toString()));

			board.recordMove(this, super.pos);
			super.pos = pos;
		}
	}

	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		
		/* checking castling */
		if (moved == false && newPos.getLetter() == 1) {
			Position corner = new Position(0, newPos.getNumber());
			if (board.getPiece(corner) instanceof Rook) {
					if (board.getPiece(corner).getColor() == super.color &&
							((Rook)board.getPiece(corner)).isMoved() == false &&
							board.getPiece(new Position(corner, 1, 0)) instanceof VoidPiece &&
							board.getPiece(new Position(corner, 2, 0)) instanceof VoidPiece &&
							board.getPiece(new Position(corner, 3, 0)) instanceof VoidPiece &&
							ChessBoard.isInCheck() == false) {
						return true;
					}
			}
			return false;
		}
		
		if (moved == false && newPos.getLetter() == 6) {
			Position corner = new Position(7, newPos.getNumber());
			if (board.getPiece(corner) instanceof Rook) {
					if (board.getPiece(corner).getColor() == super.color &&
							((Rook)board.getPiece(corner)).isMoved() == false &&
							board.getPiece(new Position(corner, -1, 0)) instanceof VoidPiece &&
							board.getPiece(new Position(corner, -2, 0)) instanceof VoidPiece &&
							ChessBoard.isInCheck() == false) {
						return true;
					}
			}
			return false;
		}

		if(!(board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).color != super.getColor()){
				return true;
			}

		if((board.getPiece(newPos) instanceof VoidPiece)){
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
		if (Math.abs(pos.getLetter() - newPos.getLetter()) <= 1) {
			super.move(newPos);
		} else {
			castling(newPos);
		}
	}
}

package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class King extends AbstractPiece {
	public int movesMade;
	public boolean inCheck;
	
	public King (String color, String position) {
		super(color, position);
		movesMade = 0;
		inCheck = false;
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

		/* checking all castling positions */
		if (movesMade == 0) {
			if (verifyMove(new Position("c1"))) {
				possibleMoves.add(new Position("c1"));
			}
			
			if (verifyMove(new Position("c8"))) {
				possibleMoves.add(new Position("c8"));
			}
			
			if (verifyMove(new Position("g1"))) {
				possibleMoves.add(new Position("g1"));
			}
			
			if (verifyMove(new Position("g8"))) {
				possibleMoves.add(new Position("g8"));
			}
		}

		return possibleMoves;
	}
	
	private void castling(Position pos) {
		ChessBoard board = ChessBoard.getInstance();
		
		/* FIRST, moving the rook */
		Rook rook;
		Position corner;
		
		/* queenside castling */
		if (pos.getLetter() == 2) {
			/* getting the corner */
			if (super.color == true) {
				corner = new Position ("a1");
			} else {
				corner = new Position ("a8");
			}
			
			rook = (Rook)board.getPiece(corner);
			rook.pos = new Position(corner, 3, 0);
		} else 
			/* kingside castling */
		{
			/* getting the corner */
			if (super.color == true) {
				corner = new Position ("h1");
			} else {
				corner = new Position ("h8");
			}
			
			rook = (Rook)board.getPiece(corner);
			rook.setPosition(new Position(corner, -2, 0));
		}
		board.setPiece(rook.getPosition(), rook);
		board.setPiece(corner, new VoidPiece(corner.toString()));
		rook.movesMade++;
		
		/* SECOND, moving the king */
		board.setPiece(pos, this);
		board.setPiece(super.pos, new VoidPiece(super.pos.toString()));
		
		board.recordMove(this, super.pos);
		setPosition(pos);
		movesMade++;
	}

	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		
		if (movesMade == 0) {
			/* checking queenside castling */
			if ((newPos.equals(new Position("c1")) && super.color == true)) {
				Position corner = new Position ("a1");
				
				if (board.getPiece(corner) instanceof Rook) {
					if (board.getPiece(corner).getColor() == super.color &&
							((Rook)board.getPiece(corner)).movesMade == 0 &&
							board.getPiece(new Position("b1")) instanceof VoidPiece &&
							board.getPiece(new Position("c1")) instanceof VoidPiece &&
							board.getPiece(new Position("d1")) instanceof VoidPiece &&
							inCheck == false) {
						return true;
					}
				}
				return false;
			} else if ((newPos.equals(new Position("g1")) && super.color == true)) {
				/* checking kingside castling */
				Position corner = new Position ("h1");
				
				if (board.getPiece(corner) instanceof Rook &&
						newPos.getNumber() == super.pos.getNumber()) {
					if (board.getPiece(corner).getColor() == super.color &&
							((Rook)board.getPiece(corner)).movesMade == 0 &&
							board.getPiece(new Position("f1")) instanceof VoidPiece &&
							board.getPiece(new Position("g1")) instanceof VoidPiece &&
							inCheck == false) {
						return true;
					}
				}
				return false;
			} else if ((newPos.equals(new Position("c8")) && super.color == false)) {
				Position corner = new Position ("a8");
				
				if (board.getPiece(corner) instanceof Rook) {
					if (board.getPiece(corner).getColor() == super.color &&
							((Rook)board.getPiece(corner)).movesMade == 0 &&
							board.getPiece(new Position("b8")) instanceof VoidPiece &&
							board.getPiece(new Position("c8")) instanceof VoidPiece &&
							board.getPiece(new Position("d8")) instanceof VoidPiece &&
							inCheck == false) {
						return true;
					}
				}
				return false;
			} else if ((newPos.equals(new Position("g8")) && super.color == false)) {
				/* checking kingside castling */
				Position corner = new Position ("h8");
				
				if (board.getPiece(corner) instanceof Rook &&
						newPos.getNumber() == super.pos.getNumber()) {
					if (board.getPiece(corner).getColor() == super.color &&
							((Rook)board.getPiece(corner)).movesMade == 0 &&
							board.getPiece(new Position("f8")) instanceof VoidPiece &&
							board.getPiece(new Position("g8")) instanceof VoidPiece &&
							inCheck == false) {
						return true;
					}
				}
				return false;
			} else {
				if (Math.abs(super.pos.getLetter() - newPos.getLetter()) > 1) {
					return false;
				}
				
				if (!(board.getPiece(newPos) instanceof VoidPiece) &&
						board.getPiece(newPos).getColor() != super.getColor()){
						return true;
					}

				if((board.getPiece(newPos) instanceof VoidPiece)){
					return true;
				}
				
				return false;
			}
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
	
	@Override
	public void move(Position newPos) {
		if (Math.abs(super.pos.getLetter() - newPos.getLetter()) <= 1) {
			super.move(newPos);
		} else {
			castling(newPos);
		}
		movesMade++;
	}

	@Override
	public void resetScore() {
		score = 10000;
	}
}

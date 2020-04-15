package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class King extends AbstractPiece {
	public int movesMade;
	
	public King (String color, String position) {
		super(color, position);
		movesMade = 0;
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
		if (verifyMove(new Position("b1"))) {
			possibleMoves.add(new Position("b1"));
		}
		
		if (verifyMove(new Position("b8"))) {
			possibleMoves.add(new Position("b8"));
		}
		
		if (verifyMove(new Position("g1"))) {
			possibleMoves.add(new Position("g1"));
		}
		
		if (verifyMove(new Position("g8"))) {
			possibleMoves.add(new Position("g8"));
		}

		return possibleMoves;
	}
	
	private void castling(Position pos) {
		ChessBoard board = ChessBoard.getInstance();
		
		/* queenside castling */
		if (pos.getLetter() == 1) {
			/* getting the corner */
			Position corner;
			if (super.color == true) {
				corner = new Position ("a1");
			} else {
				corner = new Position ("a8");
			}
			
			Rook rook = (Rook)board.getPiece(corner);
			rook.setPos(new Position(corner, 2, 0));
			board.setPiece(rook.getPosition(), rook);
			board.setPiece(corner, new VoidPiece(corner.toString()));
			rook.movesMade++;
			
			/* moving the king */
			board.setPiece(pos, this);
			board.setPiece(super.pos, new VoidPiece(super.pos.toString()));

			board.recordMove(this, super.pos);
			super.pos = pos;
			movesMade++;
			System.out.println("queenside castling");
		} else 
			/* kingside castling */
		{
			/* getting the corner */
			Position corner;
			if (super.color == true) {
				corner = new Position ("h1");
			} else {
				corner = new Position ("h8");
			}
			
			Rook rook = (Rook)board.getPiece(corner);
			rook.setPos(new Position(corner, -2, 0));
			board.setPiece(rook.getPosition(), rook);
			board.setPiece(corner, new VoidPiece(corner.toString()));
			rook.movesMade++;
			
			/* moving the king */
			board.setPiece(pos, this);
			board.setPiece(super.pos, new VoidPiece(super.pos.toString()));

			board.recordMove(this, super.pos);
			super.pos = pos;
			movesMade++;
			System.out.println("kingside castling");
		}
		
		board.printBoard();
	}

	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		
		/* checking queenside castling */
		if (movesMade == 0 && newPos.getLetter() == 1) {
			Position corner;
			if (super.color == true) {
				corner = new Position ("a1");
			} else {
				corner = new Position ("a8");
			}
			
			if (board.getPiece(corner) instanceof Rook &&
					newPos.getNumber() == super.pos.getNumber()) {
				if (board.getPiece(corner).getColor() == super.color &&
						((Rook)board.getPiece(corner)).movesMade == 0 &&
						board.getPiece(new Position(corner, 1, 0)) instanceof VoidPiece &&
						board.getPiece(new Position(corner, 2, 0)) instanceof VoidPiece &&
						board.getPiece(new Position(corner, 3, 0)) instanceof VoidPiece &&
						ChessBoard.isInCheck() == false) {
					return true;
				}
			}
			return false;
		}
		
		/* checking kingside castling */
		if (movesMade == 0 && newPos.getLetter() == 6) {
			Position corner;
			if (super.color == true) {
				corner = new Position ("h1");
			} else {
				corner = new Position ("h8");
			}
			
			if (board.getPiece(corner) instanceof Rook &&
					newPos.getNumber() == super.pos.getNumber()) {
				if (board.getPiece(corner).getColor() == super.color &&
						((Rook)board.getPiece(corner)).movesMade == 0 &&
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
	
	@Override
	public void move(Position newPos) {
		if (Math.abs(pos.getLetter() - newPos.getLetter()) <= 1) {
			super.move(newPos);
		} else {
			castling(newPos);
		}
		movesMade++;
	}
}

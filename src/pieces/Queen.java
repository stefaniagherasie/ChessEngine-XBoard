package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class Queen extends AbstractPiece {
	
	/**
	 * true if the Queen is promoted from Pawn
	 */
	public boolean wasPawn;
	public int movesMade;
	
	public Queen (String color, String position) {
		super(color, position);
		wasPawn = false;
	}

	/**
	 * Constructor for a Queen promoted from Pawn
	 */
	public Queen (String color, String position, boolean wasPawn) {
		super(color, position);
		movesMade = 0;
		this.wasPawn = wasPawn;
	}


	@Override
	public ArrayList<Position> getPossibleMoves() {

		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0) 
					continue;

				int k = 1;
				while(board.getPiece(new Position(pos, k*i, k*j)) != null) {
					Position posToCheck = new Position(pos, k*i, k*j);
					if (verifyMove(posToCheck)) {
						possibleMoves.add(posToCheck);
						if (! (ChessBoard.getInstance().getPiece(posToCheck) instanceof VoidPiece)) {
							break;
						}
					} else {
						break;
					}
					
					k++;						
				}
			}
		}
		
		return possibleMoves;

	}

	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		
		if (!(board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).color == super.getColor()) {
			board.getPiece(newPos).incSafety();
			return false;
		}
		
		if (! (board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).color != super.getColor()) {
			board.getPiece(newPos).decSafety();
			return true;
		}
		
		if ((board.getPiece(newPos) instanceof VoidPiece)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void move(Position newPos) {
		super.move(newPos);
		movesMade++;
	}

	@Override
	public void resetScore() {
		score = 9.75;
	}
}
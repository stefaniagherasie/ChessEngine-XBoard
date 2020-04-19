package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class Knight extends AbstractPiece {
	
	public Knight (String color, String position) {
		super(color, position);
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();

		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				// obtinem doar pozitii de genul (|1|, |2|) sau (|2|, |1|)
				if(i != 0 && j != 0 && (Math.abs(i) != Math.abs(j))) {
					Position posToCheck = new Position(pos, i, j);
					if (board.getPiece(posToCheck) != null && verifyMove(posToCheck)) {
						possibleMoves.add(posToCheck);										
					}
				}
			}
		}

		return possibleMoves;
	}

	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		
		if (newPos.equals(ChessBoard.getOpponentsKing().getPosition()) ||
				newPos.equals(ChessBoard.getOurKing().getPosition())) {
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
}

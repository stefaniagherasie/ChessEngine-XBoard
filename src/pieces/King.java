package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class King extends AbstractPiece {
	
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

		return possibleMoves;
	}

	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();

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
		ChessBoard b = ChessBoard.getInstance();
		b.setPiece(pos, new VoidPiece());
		b.setPiece(newPos, this);
		pos = newPos;
	}


}

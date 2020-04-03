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
				if(i != 0 && j != 0 && (Math.abs(i) != Math.abs(j))
					&& board.getPiece(new Position(pos, i, j)) != null) {
						if(!(board.getPiece(new Position(pos, i, j)) instanceof VoidPiece) &&
							board.getPiece(new Position(pos, i, j)).color != board.isPlayingColor()){
							possibleMoves.add(new Position(pos, i, j));
						}
						if((board.getPiece(new Position(pos, i, j)) instanceof VoidPiece))
							possibleMoves.add(new Position(pos, i, j));					
					}
			}
		}

		return possibleMoves;
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

}

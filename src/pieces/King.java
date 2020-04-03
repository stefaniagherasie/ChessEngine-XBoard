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
		// TODO Auto-generated method stub
		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				//cazul [0][0] nu se poate csf
				if(i == 0 && j == 0)
					continue;
				
				if(board.getPiece(new Position(pos, i, j)) != null) {
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

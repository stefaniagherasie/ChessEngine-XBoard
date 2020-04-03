package pieces;
import main.*;

import java.util.ArrayList;

import auxiliary.Position;

public class Queen extends AbstractPiece {
	
	public Queen (String color, String position) {
		super(color, position);
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
					if(!(board.getPiece(new Position(pos, k*i, k*j)) instanceof VoidPiece) &&
						board.getPiece(new Position(pos, k*i, k*j)).color != board.isPlayingColor()){
						possibleMoves.add(new Position(pos, k*i, k*j));
						break;	
					}

					if(!(board.getPiece(new Position(pos, k*i, k*j)) instanceof VoidPiece) &&
						board.getPiece(new Position(pos, k*i, k*j)).color == board.isPlayingColor())
						break;

					if((board.getPiece(new Position(pos, k*i, k*j)) instanceof VoidPiece))
						possibleMoves.add(new Position(pos, k*i, k*j));
					
					k++;						
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
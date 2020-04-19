package engine;
import java.util.ArrayList;

import auxiliary.Pair;
import auxiliary.Position;
import main.ChessBoard;
import pieces.AbstractPiece;
import pieces.King;
import pieces.VoidPiece;

public class MainStrategy implements Strategy{

	@Override
	public int eval(boolean player) {
		return (int)(Math.random() * 100);
	}

	@Override
	public ArrayList<Pair<Position, Position>> nextMoves() {
		ChessBoard board = ChessBoard.getInstance();

		ArrayList<Pair<Position, Position>> result = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
			AbstractPiece p = board.getPiece(new Position(i, j));
				if(!(p instanceof VoidPiece) && ChessBoard.isPlayerTurn() == p.getColor()) {
					ArrayList<Position> possMoves = p.getPossibleMoves();
					
					ArrayList<Position> getsMeOutOfCheck = new ArrayList<>();
					for (Position pos: possMoves) {
						if (board.makeMoveAndCheckInCheck(p.getPosition(), pos)) {
							getsMeOutOfCheck.add(pos);
						}
					}
					
					if (getsMeOutOfCheck.size() != 0) {
						for (Position pp: getsMeOutOfCheck) {
							result.add(new Pair<>(p.getPosition(), pp));
						}
					}
				} 
			}
		}
		
		return result;
	}

	@Override
	public int getDepth() {
		return 4;
	}
}

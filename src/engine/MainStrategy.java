package engine;
import java.util.ArrayList;

import auxiliary.Pair;
import auxiliary.Position;
import main.ChessBoard;
import pieces.AbstractPiece;
import pieces.King;
import pieces.VoidPiece;

public class MainStrategy implements Strategy{
	
	private double boardScoreReset() {
		ChessBoard b = ChessBoard.getInstance();
		double result = 0;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				AbstractPiece piece = b.getPiece(new Position(i, j));
				piece.resetScore();
				
				try {
					if (piece.getColor() == ChessBoard.isPlayingColor()) {
						result += piece.getScore();
					} else {
						result -= piece.getScore();
					}
				} catch (NullPointerException e) {
					continue;
				}
			}
		}
		return result;
	}
	
	private double opponentsMoves() {
		double result = 0;
		ChessBoard board = ChessBoard.getInstance();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
			AbstractPiece piece = board.getPiece(new Position(i, j));
				if(!(piece instanceof VoidPiece) && ChessBoard.isPlayerTurn() != piece.getColor()) {
					result += piece.getPossibleMoves().size();
				}
			}
		}
		
		return result;
	}

	@Override
	public double eval(boolean player) {
		double result = boardScoreReset();
		
		result += nextMoves().size();
		
		result -= opponentsMoves();
		
		return result;
	}

	@Override
	public ArrayList<Pair<Position, Position>> nextMoves() {
		ChessBoard board = ChessBoard.getInstance();

		ArrayList<Pair<Position, Position>> result = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
			AbstractPiece piece = board.getPiece(new Position(i, j));
				if(!(piece instanceof VoidPiece) && ChessBoard.isPlayerTurn() == piece.getColor()) {
					ArrayList<Position> possMoves = piece.getPossibleMoves();
					
					for (Position pos: possMoves) {
						if (board.makeMoveAndCheckInCheck(piece.getPosition(), pos)) {
							result.add(new Pair<>(piece.getPosition(), pos));
						}
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public int getDepth() {
		return 3;
	}
}

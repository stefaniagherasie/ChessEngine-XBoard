package engine;
import java.util.ArrayList;

import auxiliary.Pair;
import auxiliary.Position;
import main.ChessBoard;
import pieces.AbstractPiece;
import pieces.Bishop;
import pieces.VoidPiece;

public class MainStrategy implements Strategy{
	private int DEPTH = 4;
	
	private double boardScoreReset() {
		ChessBoard b = ChessBoard.getInstance();
		double result = 0;
		int ourBishopPair = 0, oppBishopPair = 0;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				AbstractPiece piece = b.getPiece(new Position(i, j));
				piece.resetScore();
				piece.resetSafety();
				
				try {
					if (piece.getColor() == ChessBoard.isPlayingColor()) {
						result += piece.getScore();
						if (piece instanceof Bishop) {
							ourBishopPair++;
						}
					} else {
						result -= piece.getScore();
						if (piece instanceof Bishop) {
							oppBishopPair++;
						}
					}
				} catch (NullPointerException e) {
					continue;
				}
			}
		}
		
		if (ourBishopPair == 2) {
			result += 0.5;
		}
		if (oppBishopPair == 2) {
			result -= 0.5;
		}
		return result;
	}
	
	private double boardEvaluation(boolean player) {
		double boardOccupancyScore = 0;
		double dangerScore = 0;
		ChessBoard board = ChessBoard.getInstance();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
			AbstractPiece piece = board.getPiece(new Position(i, j));
				if(!(piece instanceof VoidPiece)) {
					ArrayList<Position> moves = piece.getPossibleMoves();
					
					for (Position m: moves) {
						if (board.getPiece(m) instanceof VoidPiece) {
							if (piece.getColor() == player) {
								board.getPiece(m).incSafety();
							} else {
								board.getPiece(m).decSafety();
							}
						}
					}
				}
			}
		}
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				AbstractPiece piece = board.getPiece(new Position(i, j));
				if (piece instanceof VoidPiece) {
					boardOccupancyScore += piece.getSafety();
				} else if (piece.getColor() == player) {
					if (piece.isInDanger()) {
						dangerScore += piece.getScore() * (-0.5);
					}
				} else {
					if (piece.isInDanger()) {
						dangerScore += piece.getScore() * (0.5);
					}
				}
			}
		}
		
		return boardOccupancyScore / 5 + dangerScore;
	}

	@Override
	public double eval(boolean player) {
		double result = boardScoreReset();
		
		result += boardEvaluation(player);
		
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
		return DEPTH;
	}
}

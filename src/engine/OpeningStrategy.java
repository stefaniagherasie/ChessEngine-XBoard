package engine;
import auxiliary.*;
import main.ChessBoard;
import opening.OpeningMove;

import java.util.*;

public class OpeningStrategy implements Strategy{
	/**
	 * Each chess board position that can  occur in the different opening lines 
	 * is stored together with the known next moves for that position
	 */
	private Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> gameStates;
	
	private int DEPTH;
	
	private List<OpeningMove> nextBest;

	public OpeningStrategy(Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
		DEPTH = 6;
		nextBest = new ArrayList<>();
	}


	@Override
	public int eval(boolean player) {
		return 0;
	}


	@Override
	public Pair<Position, Position> nextMove() {
		if (nextBest.size() == 0) {
			ChessBoard board = ChessBoard.getInstance();
			ArrayList<Pair<Position, Position>> gameHistory = board.convertHistory();
			
			List<OpeningMove> nextMoves = gameStates.get(gameHistory);
			
			int gain = 0;
			for (OpeningMove move: nextMoves) {
				if (move.getGain() == gain) {
					nextBest.add(move);
				} else if (move.getGain() > gain) {
					gain = move.getGain();
					nextBest = new ArrayList<>();
					nextBest.add(move);
				}
			}
			
			if (nextBest.size() == 0) {
				return null;
			}
			
			return nextBest.remove(0).getNextMove();
		} else {
			return nextBest.remove(0).getNextMove();
		}
	}


	@Override
	public int getDepth() {
		return DEPTH;
	}

}

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

	public OpeningStrategy(Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
		DEPTH = 6;
	}


	@Override
	public int eval(boolean player) {
		return (int)Math.random() * 100;
	}


	@Override
	public ArrayList<Pair<Position, Position>> nextMoves() {
		ChessBoard board = ChessBoard.getInstance();
		/* the history represents the ley for the hashmap */
		ArrayList<Pair<Position, Position>> gameHistory = board.convertHistory();
		
		List<OpeningMove> nextMoves = gameStates.get(gameHistory);
		
		if (nextMoves == null) {
			return null;
		}
		
		ArrayList<Pair<Position, Position>> nextBest = new ArrayList<>();
		int gain = 0;
		for (OpeningMove move: nextMoves) {
			if (move.getGain() == gain) {
				nextBest.add(move.getNextMove());
			} else if (move.getGain() > gain) {
				gain = move.getGain();
				nextBest = new ArrayList<>();
				nextBest.add(move.getNextMove());
			}
		}
		
		if (nextBest.size() == 0) {
			return null;
		}
		
		return nextBest;
	}


	@Override
	public int getDepth() {
		return DEPTH;
	}

}

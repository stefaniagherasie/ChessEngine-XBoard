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
	

	public OpeningStrategy(Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
	}


	@Override
	public int eval() {
		
		return 0;
	}


	@Override
	public Pair<Position, Position> bestNextMove() {
		ChessBoard board = ChessBoard.getInstance();
		ArrayList<Pair<Position, Position>> gameHistory = board.convertHistory();
		
		List<OpeningMove> nextMoves = gameStates.get(gameHistory);
		
		int gain = 0;
		List<Pair<Position, Position>> bestGainMoves = new ArrayList<>();
		for (OpeningMove move: nextMoves) {
			if (move.getGain() == gain) {
				bestGainMoves.add(move.getNextMove());
			} else if (move.getGain() > gain) {
				gain = move.getGain();
				bestGainMoves = new ArrayList<>();
				bestGainMoves.add(move.getNextMove());
			}
		}
		return null;
	}

}

package engine;
import java.io.IOException;
import java.util.ArrayList;

import auxiliary.*;
import main.ChessBoard;
import opening.OpeningParser;

public class Engine {
	private Strategy strategy;
	private final int INF = 123456789;
	
	public Engine() {
		try {
			strategy = OpeningParser.parse();
		} catch (IOException | LineErrorException e) {
			e.printStackTrace();
		}
	}
	
	public Pair<Position, Position> nextBestMove() {		
		return minmax(strategy.getDepth(), strategy, true).second;
	}
	
	private Pair<Integer, Pair<Position, Position>> minmax(int depth, Strategy strat, boolean ourTurn) {
		try {			
			if (depth == 0) {
				return new Pair<>(strat.eval(ChessBoard.isPlayerTurn()), new Pair<>());
			}

			ChessBoard board = ChessBoard.getInstance();
			int gameValue = -INF;
			Pair<Position, Position> bestMove = new Pair<>();
			
			ArrayList<Pair<Position, Position>> moves = strat.nextMoves();
			
			/** Depth is not 0, but the engine has run out of openings
			 *  So switching to MainStrategy for the rest of the depth
			 */
			if (moves == null && strat instanceof OpeningStrategy) {
				/**
				 * Forever switching to main strategy
				 */
				if (depth == strategy.getDepth()) {
					switchToMainStrategy();
					strat = strategy;
				} else {
					strat = new MainStrategy();
				}
				moves = strat.nextMoves();
				System.out.println("improvising");
				depth = Math.min(depth, strategy.getDepth());
			}
			bestMove = new Pair<>(moves.get(0));
			
			for (Pair<Position, Position> move: moves) {
				board.computeMove(move);
				
				board.reversePlayingColor();
				ChessBoard.updateOurInCheck();
				int gain = minmax(depth - 1, strat, !ourTurn).first;				
				board.undo();
				board.reversePlayingColor();
				
				if (gain > gameValue) {
					System.out.println("depth: " + depth + "; gain: " + gain + "; old gain:" + gameValue);
					gameValue = gain;
					bestMove = new Pair<>(move);
				}
			}
			
			return new Pair<>(gameValue, bestMove);
		} catch (IndexOutOfBoundsException e) {
			/**
			 * somebody is in check mate
			 */
			System.out.println("somebody is in check mate");
			if (ourTurn) {
				return new Pair<>(-INF, new Pair<>());
			} else {
				return new Pair<>(INF, new Pair<>());
			}
		} catch (Exception e) {
			
			//Temporara, sa prind greselile
			ChessBoard.getInstance().printBoard();
			System.out.println(ChessBoard.getInstance().convertHistory());
			e.printStackTrace();
			return null;
		}
	}
	
	private void switchToMainStrategy() {
		strategy = new MainStrategy();
	}
}

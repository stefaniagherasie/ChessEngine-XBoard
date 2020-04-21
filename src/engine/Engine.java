package engine;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import auxiliary.*;
import main.ChessBoard;
import opening.OpeningParser;

public class Engine {
	private Strategy strategy;
	private final double INF = 123456789;
	
	public Engine() {
		try {
			strategy = OpeningParser.parse();
		} catch (IOException | LineErrorException e) {
			e.printStackTrace();
		}
	}
	
	public Pair<Double, Pair<Position, Position>> nextBestMove() {		
		return minmax(strategy.getDepth(), strategy, true);
	}
	
	private Pair<Double, Pair<Position, Position>> minmax(int depth, Strategy strat, boolean ourTurn) {
		try {			
			if (depth == 0) {
				return new Pair<>(strat.eval(ChessBoard.isPlayerTurn()), new Pair<>());
			}

			ChessBoard board = ChessBoard.getInstance();
			double gameValue = INF;
			if (ourTurn) gameValue = -INF;
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
				depth = Math.min(depth, strategy.getDepth());
			}
			
			/**
			 * somebody is in check mate
			 */
			if (moves.size() == 0) {
				return new Pair<>(-INF, new Pair<>());
			}
			
			bestMove = new Pair<>(moves.get(0));
			
			for (Pair<Position, Position> move: moves) {
				board.computeMove(move);
				
				board.reversePlayingColor();
				ChessBoard.updateOurInCheck();
				double gain = minmax(depth - 1, strat, !ourTurn).first;				
				board.undo();
				board.reversePlayingColor();
				
				if (ourTurn) {
					if (gain > gameValue) {
						gameValue = gain;
						bestMove = new Pair<>(move);
					}
				} else if (gain < gameValue) {
					gameValue = gain;
				}
			}
			
			return new Pair<>(gameValue, bestMove);
		}  catch (NullPointerException e) { 
			return null;
		} catch (Exception e) {
			try {
				FileWriter pw = new FileWriter(new File("ERRlog.txt"));
				//Temporara, sa prind greselile
				pw.append(ChessBoard.getInstance().printBoard());
				pw.append(ChessBoard.getInstance().convertHistory().toString());
				pw.append(e.getStackTrace().toString());
				pw.close();
				return null;
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
	
	private void switchToMainStrategy() {
		strategy = new MainStrategy();
	}
}

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
		return minmax(strategy.getDepth(), strategy).second;
	}
	
	private Pair<Integer, Pair<Position, Position>> minmax(int depth, Strategy strat) {
		ChessBoard board = ChessBoard.getInstance();
		
		if (depth == 0) {
			return new Pair<>(strat.eval(ChessBoard.isPlayerTurn()), new Pair<>());
		}
		
		int gameValue = INF;
		Pair<Position, Position> bestMove = new Pair<>();
		
		ArrayList<Pair<Position, Position>> moves = strat.nextMoves();
		
		/** Depth is not 0, but the engine has run out of openings
		 *  So switching to MainStrategy for the resto of the depth
		 */
		if (moves == null && strat instanceof OpeningStrategy) {
			strat = new MainStrategy();
			moves = strat.nextMoves();
			System.out.println("improvising");
			
			/**
			 * Forever switching to main strategy
			 */
			if (depth == strategy.getDepth()) {
				switchToMainStrategy();
			}
			depth = Math.min(depth, strategy.getDepth());
		}
		bestMove = new Pair<>(moves.get(0));
		
		for (Pair<Position, Position> move: moves) {
			board.computeMove(move);
			
			board.reversePlayingColor();
			int moveValue = minmax(depth - 1, strat).first;
			board.reversePlayingColor();
			ChessBoard.updatePlayerTurn();
			
			board.undo();
			
			if (ChessBoard.isPlayerTurn() == ChessBoard.isPlayingColor()) {
				if (moveValue > gameValue || gameValue == INF) {
					bestMove = new Pair<>(move);
					gameValue = moveValue;
				} else if (moveValue < gameValue || gameValue == INF) {
					gameValue = moveValue;
				}
			}
		}
		
		return new Pair<>(0, bestMove);
	}
	
	private void switchToMainStrategy() {
		System.out.println("switching to main!");
		strategy = new MainStrategy();
	}
}

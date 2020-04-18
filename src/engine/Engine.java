package engine;
import java.io.IOException;
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
		return minmax(ChessBoard.isPlayerTurn(), strategy.getDepth()).second;
	}
	
	private Pair<Integer, Pair<Position, Position>> minmax(boolean player, int depth) {
		if (depth == 0) {
			return new Pair<>(strategy.eval(player), new Pair<>());
		}
		
		ChessBoard board = ChessBoard.getInstance();
		int gameValue = -INF;
		if (player != ChessBoard.isPlayingColor()) {
			gameValue *= -1;
		}
		Pair<Position, Position> bestMove = new Pair<>();
		
		
		Pair<Position, Position> move = strategy.nextMove();
		if (move == null && strategy instanceof OpeningStrategy) {
			strategy = new MainStrategy();
			move = strategy.nextMove();
		}
		bestMove = new Pair<>(move);
		
		while (move != null) {
			board.computeMove(move);
			
			int moveValue = minmax(!player, depth - 1).first;
			
			board.undo();
			
			if (player == ChessBoard.isPlayingColor()) {
				if (moveValue > gameValue) {
					bestMove = new Pair<>(move);
					gameValue = moveValue;
				} else if (moveValue < gameValue) {
					gameValue = moveValue;
				}
			}
			
			move = strategy.nextMove();
			
			if (strategy instanceof OpeningStrategy && move == null && depth != 0) {
				move = new MainStrategy().nextMove();
			}
		}
		
		return new Pair<>(0, bestMove);
	}
}

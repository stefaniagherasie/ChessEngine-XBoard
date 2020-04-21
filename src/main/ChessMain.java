package main;
import auxiliary.*;
import commands.*;
import engine.Engine;
import pieces.King;

public class ChessMain {
	public static void main(String[] args) {
		CommandReader reader = new CommandReader(System.in);
		
		playGame(reader);
	}
	
	public static void playGame (CommandReader reader) {
		Command command = reader.next();
		Engine engine = new Engine();
		
		while (!(command instanceof QuitCommand)) {
			command.execute();
			
			if (command instanceof UndoCommand) {
				System.out.println(ChessBoard.getInstance().printBoard());
			}
			
			//our turn to move
			if(ChessBoard.isPlayingColor() == ChessBoard.isPlayerTurn() && !ChessBoard.isforceMode()) {
				
				ChessBoard.getInstance().printBoard();
				Pair<Double, Pair<Position, Position>> move = engine.nextBestMove();
				if (!move.second.isEmpty()) {
					makeMove(move.second);
				} else {
					System.out.println("resign");
				}
			}

			command = reader.next();
		}
	}
	
	private static void makeMove(Pair<Position, Position> move) {
		ChessBoard board = ChessBoard.getInstance();
		board.computeMove(move);
		
		if (move.first.equals(new Position("e1")) || move.first.equals(new Position("e8"))) {
			if (board.getPiece(move.first) instanceof King) {
				if (move.second.getLetter() == 6) {
					System.out.println("O-O");
					return;
				} else if (move.second.getLetter() == 2) {
					System.out.println("O-O-O");
					return;
				}
			}
		}
		
		System.out.println("move " + move.first + move.second);
	}
}
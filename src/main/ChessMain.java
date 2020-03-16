package main;
import auxiliary.*;
import commands.*;
import pieces.*;
import java.util.ArrayList;

public class ChessMain {
	
	public static void main(String[] args) {
		
		ChessBoard b = ChessBoard.getInstance();
		
		CommandReader reader = new CommandReader(System.in);
		
		playGame(reader, b);
		
	}
	
	public static void playGame (CommandReader reader, ChessBoard board) {
		Command command = reader.next();
		
		while (!(command instanceof QuitCommand)) {
			command.execute();
				
			
			//our turn to move
			if(board.isPlayingColor() == board.isPlayerTurn()) 
			{
				ArrayList<Position> pawnFirstMoves = new ArrayList<Position>();
				for(Character c = 'a'; c <= 'h'; c++) {
					String pos = (Character.toString(c)).concat("2");
					Pawn pawn = new Pawn("white", pos);
					// ????
					pawnFirstMoves.addAll(pawn.getPossibleMoves());

				}

				Position random_move = pawnFirstMoves.get((int)Math.random() * (pawnFirstMoves.size() - 1) + 0);
			}
			


			command = reader.next();
		}
	}
}

package main;
import auxiliary.*;
import commands.*;

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
			
			command = reader.next();
		}
	}
}

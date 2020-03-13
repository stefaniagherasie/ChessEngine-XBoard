package main;
import pieces.*;
import auxiliary.*;
import commands.*;

public class ChessMain {
	public static void main(String[] args) {
		
		ChessBoard b = ChessBoard.getInstance();
		
		/*CommandReader reader = new CommandReader();
		
		playGame(reader);*/
		
	}
	
	public static void playGame (CommandReader reader) {
		Command com = reader.next();
		
		while (!(com instanceof QuitCommand)) {
			
		}
	}
}

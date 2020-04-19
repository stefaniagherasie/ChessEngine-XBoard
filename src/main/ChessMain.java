package main;
import auxiliary.*;
import commands.*;
import engine.Engine;
import pieces.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChessMain {
	public static void main(String[] args) {
		CommandReader reader = new CommandReader(System.in);
		
		playGame(reader);
	}
	
	public static void playGame (CommandReader reader) {
		Command command = reader.next();
		Engine engine = new Engine();
		
		ChessBoard.getInstance().printBoard();
		
		while (!(command instanceof QuitCommand)) {
			command.execute();
			
			if (command instanceof UndoCommand) {
				ChessBoard.getInstance().printBoard();
			}
			
			//our turn to move
			if(ChessBoard.isPlayingColor() == ChessBoard.isPlayerTurn() && !ChessBoard.isforceMode()) {
				
				ChessBoard.getInstance().printBoard();
				Pair<Position, Position> move = engine.nextBestMove();
				if (move != null) {
					ChessBoard.getInstance().computeMove(move);
					System.out.println("move " + move.first + move.second);
				} else {
					System.out.println("resign");
				}
				ChessBoard.getInstance().printBoard();
			}

			command = reader.next();
		}
	}
}
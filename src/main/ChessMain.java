package main;
import auxiliary.*;
import commands.*;
import pieces.*;
import java.util.*;

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
			if(board.isPlayingColor() == board.isPlayerTurn() && !board.isforceMode()) 
			{
				ArrayList<ArrayList <Position>> position = new ArrayList<ArrayList <Position>>();
				ArrayList<AbstractPiece> piece = new ArrayList<AbstractPiece>();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
					AbstractPiece p = board.getPiece(new Position(i, j));
						if(p instanceof Pawn && board.isPlayingColor() == p.getColor()) {
							piece.add(p);
							position.add(p.getPossibleMoves());
						}
					}
				}
				afisate_move(position, piece);
			}
			
			

			command = reader.next();
		}
	}
	
	public static void afisate_move(ArrayList<ArrayList <Position>> position, ArrayList<AbstractPiece> pieces) {
		int index = (int)(Math.random() * pieces.size());
		
		AbstractPiece piece = pieces.get(index);
		ArrayList<Position> possibleMoves = position.get(index);
		
		int index2 = (int)(Math.random() * possibleMoves.size());
		System.out.println("move " + piece.getPosition().toString() + possibleMoves.get(index2).toString());
		
		piece.move(possibleMoves.get(index2));
	}
}

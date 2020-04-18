package main;
import auxiliary.*;
import commands.*;
import pieces.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChessMain {
	
	static FileWriter pw;
	
	public static void main(String[] args) {
		
		try {
			pw  = new FileWriter (new File("log.txt"));
		} catch (IOException e) {}
		
		CommandReader reader = new CommandReader(System.in);
		
		playGame(reader);
		try {
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void playGame (CommandReader reader) {
		Command command = reader.next();
		
		ChessBoard.getInstance().printBoard();
		
		while (!(command instanceof QuitCommand)) {
			command.execute();
				
			if (!(command instanceof VoidCommand)) {
				try {
					pw.write(command.getClass().toString() + "\n");
				} catch (IOException e) {}
			}
			
			if (command instanceof UndoCommand) {
				ChessBoard.getInstance().printBoard();
			}
			
			//our turn to move
			if(ChessBoard.isPlayingColor() == ChessBoard.isPlayerTurn() && !ChessBoard.isforceMode()) 
			{
				/*
				ChessBoard.getInstance().printBoard();
				Pair<Position, Position> move = engine.nextBestMove();
				if (move != null) {
					ChessBoard.getInstance().computeMove(move);
					System.out.println("move " + move.first + move.second);
				} else {
					System.out.println("resign");
				}
				ChessBoard.getInstance().printBoard();
				*/
				
				
				ChessBoard.getInstance().printBoard();

				ChessBoard board = ChessBoard.getInstance();
				
				ArrayList<ArrayList <Position>> positions = new ArrayList<ArrayList <Position>>();
				ArrayList<AbstractPiece> pieces = new ArrayList<AbstractPiece>();
				
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
					AbstractPiece p = ChessBoard.getInstance().getPiece(new Position(i, j));
						if(!(p instanceof VoidPiece) && ChessBoard.isPlayingColor() == p.getColor()) {
							ArrayList<Position> possMoves = p.getPossibleMoves();
							
							ArrayList<Position> getsMeOutOfCheck = new ArrayList<>();
							for (Position pos: possMoves) {
								if (board.makeMoveAndCheckInCheck(p.getPosition(), pos)) {
									getsMeOutOfCheck.add(pos);
								}
							}
							
							if (getsMeOutOfCheck.size() != 0) {
								pieces.add(p);
								positions.add(getsMeOutOfCheck);
							}
						} 
					}
				}
				
				if (!showRandomMove(positions, pieces))
					 //resign
					 System.out.println("resign");
				
				System.out.println(ChessBoard.getInstance().convertHistory().toString());
				
				ChessBoard.getInstance().printBoard();
			}

			command = reader.next();
		}
	}
	
	/*
	 * metoda temorara doar pentru aceasta etapa
	 * primeste lista de pioni si posibilele lor miscari si alege sa faca una la intamplare
	 */
	public static boolean showRandomMove(ArrayList<ArrayList <Position>> positions, ArrayList<AbstractPiece> pieces) {
		//verifiacre daca mai exista miscari
		int totalNumberOfMoves = 0;
		for (ArrayList<Position> p: positions) {
			totalNumberOfMoves += p.size();
		}
		if (totalNumberOfMoves == 0)
			return false;
		
		
		AbstractPiece piece;
		ArrayList<Position> possibleMoves;
		
		do {
			int index = (int)(Math.random() * 10) % pieces.size();
			piece = pieces.get(index);
			possibleMoves = positions.get(index);
		} while (possibleMoves.size() == 0);
		
		int index2 = (int)(Math.random() * 10) % possibleMoves.size();
		System.out.println("move " + piece.getPosition().toString() + possibleMoves.get(index2).toString());
		try {
			pw.append("move " + piece.getPosition().toString() + possibleMoves.get(index2).toString() + "\n");
		} catch (IOException e) {}
		
		//updating board
		piece.move(possibleMoves.get(index2));
		//updating player turn
		ChessBoard.updatePlayerTurn();
		return true;
	}
}

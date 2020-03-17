package main;
import auxiliary.*;
import commands.*;
import pieces.*;
import java.util.ArrayList;

public class ChessMain {
	
	public static void main(String[] args) {
		
		CommandReader reader = new CommandReader(System.in);
		
		playGame(reader);
		
	}
	
	public static void playGame (CommandReader reader) {
		Command command = reader.next();
		
		while (!(command instanceof QuitCommand)) {
			command.execute();
				
			
			//our turn to move
			if(ChessBoard.isPlayingColor() == ChessBoard.isPlayerTurn() && !ChessBoard.isforceMode()) 
			{
				ArrayList<ArrayList <Position>> positions = new ArrayList<ArrayList <Position>>();
				ArrayList<AbstractPiece> pieces = new ArrayList<AbstractPiece>();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
					AbstractPiece p = ChessBoard.getInstance().getPiece(new Position(i, j));
						if(p instanceof Pawn && ChessBoard.isPlayingColor() == p.getColor()) {
							pieces.add(p);
							positions.add(p.getPossibleMoves());
						}
					}
				}
				
				if (!showRandomMove(positions, pieces))
					 //resign
					 System.out.println("resign");
			}

			command = reader.next();
		}
	}
	
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
		
		//updating board
		piece.move(possibleMoves.get(index2));
		//updating player turn
		ChessBoard.updatePlayerTurn();
		return true;
	}
}

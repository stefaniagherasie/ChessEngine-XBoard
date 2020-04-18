package engine;
import java.util.ArrayList;

import auxiliary.Pair;
import auxiliary.Position;
import main.ChessBoard;
import pieces.AbstractPiece;
import pieces.VoidPiece;

public class MainStrategy implements Strategy{

	@Override
	public int eval(boolean player) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Pair<Position, Position> nextMove() {
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
		
		return showRandomMove(positions, pieces);
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Pair<Position, Position> showRandomMove(ArrayList<ArrayList <Position>> positions, ArrayList<AbstractPiece> pieces) {
		//verifiacre daca mai exista miscari
		int totalNumberOfMoves = 0;
		for (ArrayList<Position> p: positions) {
			totalNumberOfMoves += p.size();
		}
		if (totalNumberOfMoves == 0)
			return null;
		
		
		AbstractPiece piece;
		ArrayList<Position> possibleMoves;
		
		do {
			int index = (int)(Math.random() * 10) % pieces.size();
			piece = pieces.get(index);
			possibleMoves = positions.get(index);
		} while (possibleMoves.size() == 0);
		
		int index2 = (int)(Math.random() * 10) % possibleMoves.size();
		System.out.println("move " + piece.getPosition().toString() + possibleMoves.get(index2).toString());
		
		piece.move(possibleMoves.get(index2));
		ChessBoard.updatePlayerTurn();
		
		return new Pair<>(piece.getPosition(), possibleMoves.get(index2));
	}

}

package pieces;
import main.*;
import java.util.ArrayList;

import auxiliary.Position;

public class Pawn extends AbstractPiece{
	private boolean moved = false;
	
	public Pawn (String color, String position) {
		super(color, position);
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();
		int d; //d pentru directie de verificare in functie de culoarea jucata
		if (ChessBoard.isPlayingColor() == true)
			d = 1;
		else
			d = -1;
		
		if (moved == false && 
				board.getPiece(new Position(pos, 0, d*1)) instanceof VoidPiece &&
				board.getPiece(new Position(pos, 0, d*2)) instanceof VoidPiece)
			possibleMoves.add(new Position(pos, 0, d*2));
		
		if (board.getPiece(new Position(pos, 0, d*1)) instanceof VoidPiece)
			possibleMoves.add(new Position(pos, 0, d*1));
		
		if (board.getPiece(new Position(pos, d*1, d*1)) != null &&
				board.getPiece(new Position(pos, d*1, d*1)).color != board.isPlayingColor())
			possibleMoves.add(new Position(pos, d*1, d*1));
		
		if (board.getPiece(new Position(pos, d*(-1), d*1)) != null &&
				board.getPiece(new Position(pos, d*(-1), d*1)).color != board.isPlayingColor())
			possibleMoves.add(new Position(pos, d*(-1), d*1));
		
		return possibleMoves;
	}
	
	@Override
	public boolean verifyMove(Position newPos) {
		return true;
	}

	@Override
	public void move(Position pos) {
		this.pos = pos;
		if (moved == false)
			moved = true;
	}
}

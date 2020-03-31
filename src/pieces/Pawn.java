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
		
		//the direction that the pawn has to go is different for white and black
		int d;
		if (ChessBoard.isPlayingColor() == true)
			d = 1;
		else
			d = -1;
		
		if (moved == false && 
				board.getPiece(new Position(pos, 0, d*1)) instanceof VoidPiece &&
				board.getPiece(new Position(pos, 0, d*2)) instanceof VoidPiece)
			possibleMoves.add(new Position(pos, 0, d*2));
		
		if (board.getPiece(new Position(pos, 0, d*1)) instanceof VoidPiece )
			possibleMoves.add(new Position(pos, 0, d*1));
		
		if (board.getPiece(new Position(pos, d*1, d*1)) != null &&
				!(board.getPiece(new Position(pos, d*(1), d*1)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, d*1, d*1)).color != board.isPlayingColor())
			possibleMoves.add(new Position(pos, d*1, d*1));
		
		if (board.getPiece(new Position(pos, d*(-1), d*1)) != null &&
				!(board.getPiece(new Position(pos, d*(-1), d*1)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, d*(-1), d*1)).color != board.isPlayingColor())
			possibleMoves.add(new Position(pos, d*(-1), d*1));
		
		return possibleMoves;
	}
	
	//momentan nefolosita
	@Override
	public boolean verifyMove(Position newPos) {
		if (getPossibleMoves().contains(newPos))
			return true;
		return false;
	}

	@Override
	public void move(Position newPos) {
		ChessBoard b = ChessBoard.getInstance();
		b.setPiece(pos, new VoidPiece());
		b.setPiece(newPos, this);
		pos = newPos;
		if (moved == false)
			moved = true;
	}
}

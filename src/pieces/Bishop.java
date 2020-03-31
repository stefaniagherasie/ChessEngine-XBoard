package pieces;
import java.util.ArrayList;

import auxiliary.Position;
import main.*;

public class Bishop extends AbstractPiece {
	
	public Bishop (String color, String position) {
		super(color, position);
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		// TODO Auto-generated method stub
		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();
	
		int i = 1;
		while(board.getPiece(new Position(pos, i, i)) != null) {
			if(!(board.getPiece(new Position(pos, i, i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, i, i)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, i, i));
				break;	
			}
			if(!(board.getPiece(new Position(pos, i, i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, i, i)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, i, i)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, i, i));
				i++;
		}

		i = 1;
		while(board.getPiece(new Position(pos, -i, -i)) != null) {
			if(!(board.getPiece(new Position(pos, -i, -i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, -i, -i)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, -i, -i));
				break;
			}
			if(!(board.getPiece(new Position(pos, -i, -i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, -i, -i)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, -i, -i)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, -i, -i));
				i++;
		}
		
		i = 1;
		while(board.getPiece(new Position(pos, i, -i)) != null) {
			if(!(board.getPiece(new Position(pos, i, -i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, i, -i)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, i, -i));
				break;
			}
			if(!(board.getPiece(new Position(pos, i, -i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, i, -i)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, i, -i)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, i, -i));
				i++;
		}
		
		i = 1;
		while(board.getPiece(new Position(pos, -i, i)) != null) {
			if(!(board.getPiece(new Position(pos, -i, i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, -i, i)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, -i, i));
				break;
			}
			if(!(board.getPiece(new Position(pos, -i, i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, -i, i)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, -i, i)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, -i, i));
				i++;
		}
		
		return possibleMoves;

	}
	
	@Override
	public void move(Position newPos) {
		ChessBoard b = ChessBoard.getInstance();
		b.setPiece(pos, new VoidPiece());
		b.setPiece(newPos, this);
		pos = newPos;
	}

	@Override
	public boolean verifyMove(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

}

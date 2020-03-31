package pieces;
import java.util.ArrayList;

import auxiliary.Position;
import main.*;

public class Rook extends AbstractPiece {
	private boolean moved = false;
	public Rook (String color, String position) {
		super(color, position);
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		// TODO Auto-generated method stub
		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();
		
		//an spate
		int i = 1;
		while(board.getPiece(new Position(pos, 0, i)) != null) {
			if(!(board.getPiece(new Position(pos, 0, i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, 0, i)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, 0, i));
				break;	
			}
			if(!(board.getPiece(new Position(pos, 0, i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, 0, i)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, 0, i)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, 0, i));
				i++;
		}
		
		//la stanga
		i = 1;
		while(board.getPiece(new Position(pos, -i, 0)) != null) {
			if(!(board.getPiece(new Position(pos, -i, 0)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, -i, 0)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, -i, 0));
				break;
			}
			if(!(board.getPiece(new Position(pos, i, 0)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, -i, 0)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, -i, 0)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, -i, 0));
				i++;
		}
		
		
		//la dreapta
		i = 1;
		while(board.getPiece(new Position(pos, i, 0)) != null) {
			if(!(board.getPiece(new Position(pos, i, 0)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, i, 0)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, i, 0));
				break;
			}
			if(!(board.getPiece(new Position(pos, i, 0)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, i, 0)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, i, 0)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, i, 0));
			i++;
		}
		
		//tura merge an fatza
		i = 1;
		while(board.getPiece(new Position(pos, 0, -i)) != null) {
			if(!(board.getPiece(new Position(pos, 0, -i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, 0, -i)).color != board.isPlayingColor()){
				possibleMoves.add(new Position(pos, 0, -i));
				break;
			}
			if(!(board.getPiece(new Position(pos, 0, -i)) instanceof VoidPiece) &&
				board.getPiece(new Position(pos, 0, -i)).color == board.isPlayingColor())
				break;
			if((board.getPiece(new Position(pos, 0, -i)) instanceof VoidPiece))
				possibleMoves.add(new Position(pos, 0, -i));
			i++;
		}
		
		return possibleMoves;
	}
	
	@Override
	public boolean verifyMove(Position pos) {
		// TODO Auto-generated method stub
		if (getPossibleMoves().contains(pos))
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
package pieces;
import java.util.ArrayList;

import auxiliary.Position;
import main.*;

public class Rook extends AbstractPiece {
	protected boolean moved;
	public Rook (String color, String position) {
		super(color, position);
	}

	@Override
	public ArrayList<Position> getPossibleMoves() {
		// TODO Auto-generated method stub
		possibleMoves = new ArrayList<Position>();
		ChessBoard board = ChessBoard.getInstance();
		
		int i = 1;
		while(board.getPiece(new Position(pos, 0, i)) != null) {
			Position posToCheck = new Position(pos, 0, i);
			
			if (verifyMove(posToCheck)) {
				possibleMoves.add(posToCheck);
				if (! (ChessBoard.getInstance().getPiece(posToCheck) instanceof VoidPiece)) {
					break;
				}
			} else {
				break;
			}
			i++;
		}
		
		i = 1;
		while(board.getPiece(new Position(pos, -i, 0)) != null) {
			Position posToCheck = new Position(pos, -i, 0);
			
			if (verifyMove(posToCheck)) {
				possibleMoves.add(posToCheck);
				if (! (ChessBoard.getInstance().getPiece(posToCheck) instanceof VoidPiece)) {
					break;
				}
			} else {
				break;
			}
			i++;
		}
		
		i = 1;
		while(board.getPiece(new Position(pos, i, 0)) != null) {
			Position posToCheck = new Position(pos, i, 0);
			
			if (verifyMove(posToCheck)) {
				possibleMoves.add(posToCheck);
				if (! (ChessBoard.getInstance().getPiece(posToCheck) instanceof VoidPiece)) {
					break;
				}
			} else {
				break;
			}
			i++;
		}
		
		i = 1;
		while (board.getPiece(new Position(pos, 0, -i)) != null) {
			Position posToCheck = new Position(pos, 0, -i);
			
			if (verifyMove(posToCheck)) {
				possibleMoves.add(posToCheck);
				if (! (ChessBoard.getInstance().getPiece(posToCheck) instanceof VoidPiece)) {
					break;
				}
			} else {
				break;
			}
			i++;						
		}
		
		return possibleMoves;
	}
	
	@Override
	public boolean verifyMove(Position newPos) {
		ChessBoard board = ChessBoard.getInstance();
		
		if (! (board.getPiece(newPos) instanceof VoidPiece) &&
				board.getPiece(newPos).color != super.getColor()) {
				return true;
			}
		
		if ((board.getPiece(newPos) instanceof VoidPiece)) {
			return true;
		}
		
		return false;
	}
	
	public void resetMoved() {
		moved = false;
	}
	
	public boolean isMoved() {
		return moved;
	}
	
	@Override
	public void move(Position newPos) {
		moved = true;
		super.move(newPos);
	}
}
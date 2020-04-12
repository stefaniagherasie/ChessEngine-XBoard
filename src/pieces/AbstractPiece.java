package pieces;
import java.util.ArrayList;
import auxiliary.Position;
import main.ChessBoard;

public abstract class AbstractPiece {
	protected Position pos;
	protected boolean color;
	protected ArrayList<Position> possibleMoves;
	
	protected AbstractPiece(String color, String position) {
		if (color != null) {
			if (color.equals("white"))
				this.color = true;
			else
				this.color = false;
		}
		pos = new Position(position);
	}
	
	
	@Override
	public String toString() {
		return "AbstractPiece [pos=" + pos + ", color=" + color + "]";
	}


	public abstract ArrayList<Position> getPossibleMoves();
	
	/**
	 * checks if the piece can be moved to the specified position
	 * @param pos the new position to be moved to
	 */
	public abstract boolean verifyMove(Position pos);
	
	/**
	 * changes the piece's position if the move is valid
	 * @param newPos = new Position of the piece
	 */
	public void move(Position newPos) {
		ChessBoard b = ChessBoard.getInstance();
		b.recordMove(b.getPiece(newPos), pos);
		b.setPiece(pos, new VoidPiece(pos.toString()));
		b.setPiece(newPos, this);
		pos = newPos;
	}
	
	/**
	 * @return the color of the piece
	 */
	public boolean getColor() {
		return color;
	}
	
	public Position getPosition() {
		return pos;
	}
}

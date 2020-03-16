package pieces;
import java.util.ArrayList;
import auxiliary.Position;

public abstract class AbstractPiece {
	protected Position pos;
	protected boolean color;
	protected ArrayList<Position> possibleMoves;
	
	protected AbstractPiece(String color, String position) {
		if (color != null && position != null) {
			pos = new Position(position);
			if (color.equals("white"))
				this.color = true;
			else
				this.color = false;
		}
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
	 * @param pos
	 */
	public abstract void move(Position pos);
	
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

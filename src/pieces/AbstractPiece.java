package pieces;
import java.util.ArrayList;
import auxiliary.Position;
import main.ChessBoard;

public abstract class AbstractPiece {
	protected Position pos;
	protected boolean color;
	protected ArrayList<Position> possibleMoves;
	protected double score;
	protected int safety;
	
	protected AbstractPiece(String color, String position) {
		if (color != null) {
			if (color.equals("white"))
				this.color = true;
			else
				this.color = false;
		}
		pos = new Position(position);
		safety = 0;
		resetScore();
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
	
	public abstract void resetScore();
	
	public void resetSafety() {
		safety = 0;
	}
	
	public void incSafety() {
		safety++;
	}
	
	public void decSafety() {
		safety--;
	}
	
	public int getSafety() {
		return safety;
	}
	
	public boolean isInDanger() {
		return (safety < 0);
	}
	
	public void setPosition(Position p) {
		pos = p;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public Position getPosition() {
		return pos;
	}

	public double getScore() {
		return score;
	}
}

package pieces;

public class PiecesFactory {
	public static Piece createPiece(String s) {
		if (s.equals("a8"))
			return new Rook("black", "a8");
		if (s.equals("h8"))
			return new Rook("black", "h8");
		
		return null;
	}
}

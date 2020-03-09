
public class ChessBoard {
	private static ChessBoard uniqueInstance = null;
	private Piece[][] board;
	
	private ChessBoard() {
		board = new Piece[8][8];
	}
	
	public static ChessBoard getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ChessBoard();
		return uniqueInstance;
	}
	
	//primeste b7
	public Piece getPiece(String s) {
		if (s.charAt(0) < 'a' && s.charAt(0) > 'h')
			return null;
		if (s.charAt(1) < '1' && s.charAt(1) > '8')
			return null;
		
		return board[Integer.parseInt(String.valueOf(s.charAt(0))) - 97][Integer.parseInt(String.valueOf(s.charAt(1))) - 49];
	}
}

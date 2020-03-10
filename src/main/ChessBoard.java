package main;
import pieces.*;

public class ChessBoard {
	private static ChessBoard uniqueInstance = null;
	private Piece[][] board;
	
	private ChessBoard() {
		board = new Piece[8][8];
		reset();
	}
	
	public static ChessBoard getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ChessBoard();
		return uniqueInstance;
	}
	
	//steaza boardul la pozitia initiala
	public void reset() {
		char[] pos = {'a', '1'};
		for (int i = 0; i < 8; i++, pos[0]++)
			for (int j = 0; j < 8; pos[1]++)
				board[i][j] = PiecesFactory.createPiece(new String (pos));
	}
	
	  
	
	/*public Piece getPiece(String s) {
		if (s.charAt(0) < 'a' && s.charAt(0) > 'h')
			return null;
		if (s.charAt(1) < '1' && s.charAt(1) > '8')
			return null;
		
		return board[Integer.parseInt(String.valueOf(s.charAt(0))) - 97][Integer.parseInt(String.valueOf(s.charAt(1))) - 49];
	}*/
}

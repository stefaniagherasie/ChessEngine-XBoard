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
		PiecesFactory pf = new PiecesFactory();
		char[] pos = {'a', '1'};
		for (int i = 0; i < 8; i++, pos[1]++) {
			for (int j = 0; j < 8; j++, pos[0]++) 
				board[i][j] = pf.createPiece(new String (pos));
			pos[0] = 'a';
		}
	}
	
	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] instanceof Pawn)
					System.out.print("P ");
				if (board[i][j] instanceof Rook)
					System.out.print("R ");
				if (board[i][j] instanceof Bishop)
					System.out.print("B ");
				if (board[i][j] instanceof Knight)
					System.out.print("k ");
				if (board[i][j] instanceof Queen)
					System.out.print("Q ");
				if (board[i][j] instanceof King)
					System.out.print("K ");
				if (board[i][j] == null)
					System.out.print("0 ");
			}
			System.out.println();
		}
	}
	
	/*public Piece getPiece(String s) {
		if (s.charAt(0) < 'a' && s.charAt(0) > 'h')
			return null;
		if (s.charAt(1) < '1' && s.charAt(1) > '8')
			return null;
		
		return board[Integer.parseInt(String.valueOf(s.charAt(0))) - 97][Integer.parseInt(String.valueOf(s.charAt(1))) - 49];
	}*/
}

package main;
import auxiliary.PiecesFactory;
import auxiliary.Position;
import pieces.*;

public class ChessBoard {
	private static ChessBoard uniqueInstance = null;
	private AbstractPiece[][] board;
	//true = white; false = black
	private static boolean playingColor;
	//true = albul trb sa miste, false = negru trb sa mute
	private static boolean playerTurn;
	private static boolean forceMode;
	
	private ChessBoard() {
		board = new AbstractPiece[8][8];
		playingColor = false;
		playerTurn = true;
		reset();
	}
	
	public static ChessBoard getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ChessBoard();
		return uniqueInstance;
	}
	
	//steaza boardul la pozitia initiala
	public void reset() {
		PiecesFactory pf = PiecesFactory.getInstance();
		char[] pos = {'a', '1'};
		for (int i = 0; i < 8; i++, pos[1]++) {
			for (int j = 0; j < 8; j++, pos[0]++) 
				board[j][i] = pf.createPiece(new String (pos));
			pos[0] = 'a';
		}
	}
	
	public AbstractPiece getPiece(Position pos) {
		if (pos.legalPosition())
			return board[pos.getLetter()][pos.getNumber()];
		return null;
	}
	
	public void setPiece(Position pos, AbstractPiece p) {
		if (pos.legalPosition())
			board[pos.getLetter()][pos.getNumber()] = p;
	}
	
	//afiseaza la stdout masa
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
				if (board[i][j] instanceof VoidPiece)
					System.out.print("0 ");
			}
			System.out.println();
		}
	}
	
	public static void updatePlayerTurn() {
		playerTurn = !playerTurn;
	}

	public static boolean isPlayingColor() {
		return playingColor;
	}

	public void setPlayingColor(boolean playingColor) {
		this.playingColor = playingColor;
	}

	public static boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		ChessBoard.playerTurn = playerTurn;
	}
	
	public static boolean isforceMode() {
		return forceMode;
	}
	
	public void setforceMode(boolean forceMode) {
		ChessBoard.forceMode = forceMode;
	}
}

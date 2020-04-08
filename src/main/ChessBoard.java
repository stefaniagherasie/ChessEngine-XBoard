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
	private static boolean inCheck;
	
	private static AbstractPiece ourKing, opponentsKing;
	
	private ChessBoard() {
		board = new AbstractPiece[8][8];
		playerTurn = true;
		inCheck = false;
		reset();
	}
	
	public static ChessBoard getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ChessBoard();
		return uniqueInstance;
	}
	
	//seteaza boardul la pozitia initiala
	public void reset() {
		PiecesFactory pf = PiecesFactory.getInstance();
		char[] pos = {'a', '1'};
		for (int i = 0; i < 8; i++, pos[1]++) {
			for (int j = 0; j < 8; j++, pos[0]++) {
				board[j][i] = pf.createPiece(new String (pos));
				if (board[j][i] instanceof King) {
					if (board[j][i].getColor() == true) ourKing = board[j][i];
					else opponentsKing = board[j][i];
				}
			}
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
	
	public static void updateInCheck() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				AbstractPiece p = ChessBoard.getInstance().getPiece(new Position(i, j));
				if (!(p instanceof VoidPiece) && p.getColor() != playingColor) {
					if (p.getPossibleMoves().contains(ourKing.getPosition())) {
						inCheck = true;
						return;
					}
				}
			}
		}
		inCheck = false;
	}

	
	//afiseaza la stdout masa
	public void printBoard() {
		System.out.println("  1  2  3  4  5  6  7  8");
		
		for (int i = 0; i < 8; i++) {
			char line = (char)(i + 'a');
			System.out.print(line + ":");
			for (int j = 0; j < 8; j++) {
				if (board[i][j] instanceof Pawn)
					System.out.print("P");
				if (board[i][j] instanceof Rook)
					System.out.print("R");
				if (board[i][j] instanceof Bishop)
					System.out.print("B");
				if (board[i][j] instanceof Knight)
					System.out.print("k");
				if (board[i][j] instanceof Queen)
					System.out.print("Q");
				if (board[i][j] instanceof King)
					System.out.print("K");
				if (board[i][j] instanceof VoidPiece) {
					System.out.print("0  ");
					continue;
				}
				if (board[i][j].getColor())
					System.out.print("a ");
				else
					System.out.print("b ");
			}
			System.out.println();
		}
	}
	
	public static boolean isInCheck() {
		return inCheck;
	}
	
	public static void updatePlayerTurn() {
		playerTurn = !playerTurn;
	}

	public static boolean isPlayingColor() {
		return playingColor;
	}

	public void setPlayingColor(boolean playingC) {
		playingColor = playingC;
	}

	public static boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerT) {
		playerTurn = playerT;
	}
	
	public static boolean isforceMode() {
		return forceMode;
	}
	
	public void setforceMode(boolean forceM) {
		forceMode = forceM;
	}
}

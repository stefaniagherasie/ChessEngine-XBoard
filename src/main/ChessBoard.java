package main;
import java.util.ArrayList;

import auxiliary.*;
import pieces.*;

public class ChessBoard {
	private static ChessBoard uniqueInstance = null;
	private AbstractPiece[][] board;
	
	private static boolean playingColor;
	private static boolean playerTurn;
	
	private static boolean forceMode;
	
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
	
	/**
	 * Section that deals with the engine being in check
	 */
	
	private static boolean inCheck;
	private static AbstractPiece ourKing, opponentsKing;
	
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
	
	/**
	 * Section that deals with the game moves
	 * Function undo that sets game to one move back
	 */

	/**
	 * gameMoves records the moves that occured this game
	 * it remembers the piece that was taken and the position of the piece that took it
	 */
	private static ArrayList<Pair<AbstractPiece, Position>> gameMoves;
	
	public void undo() {
		try {
			Pair<AbstractPiece, Position> move = gameMoves.remove(0);
			AbstractPiece takenPiece = move.first;
			
			/* this was the king castling */
			if (takenPiece instanceof King) {
				
			}
			
			AbstractPiece p = getPiece(takenPiece.getPosition());
			Position takenPiecePos = p.getPosition();
			p.move(move.second);
			gameMoves.remove(0);
			setPiece(takenPiecePos, takenPiece);
			
			/* setting player's turn */
			playerTurn = p.getColor();
			
			/* Resetting pawn/king/rook first move variable */
			if (p instanceof Pawn) {
				if (p.getColor() == true) {
					if (p.getPosition().getNumber() == 1) ((Pawn) p).resetMoved();
				} else {
					if (p.getPosition().getNumber() == 6) ((Pawn) p).resetMoved();
				}
			}
			if (p instanceof King) {
				if (p.getColor() == true) {
					if (p.getPosition().getNumber() == 0) ((King) p).resetMoved();
				} else {
					if (p.getPosition().getNumber() == 7) ((King) p).resetMoved();
				}
			}
			if (p instanceof Rook) {
				if (p.getColor() == true) {
					if (p.getPosition().getNumber() == 0) ((Rook) p).resetMoved();
				} else {
					if (p.getPosition().getNumber() == 7) ((Rook) p).resetMoved();
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Game is in its original state.");
		}
	}
	
	public void recordMove (AbstractPiece p, Position originalPos) {
		gameMoves.add(0, new Pair<>(p, originalPos));
	}
	
	/**
	 * Method that is given a move, executes the move,
	 * checks if the engine is still in check and then undoes the move
	 * @param pos = position of the piece to be moved
	 * @param newPos = new position of the piece
	 * @return true if the move gets the engine out of check, false otherwise
	 */
	public boolean makeMoveAndCheckInCheck(Position pos, Position newPos) {
		AbstractPiece piece = getPiece(pos);
		
		piece.move(newPos);
		updateInCheck();
		undo();
		
		boolean ans = !inCheck;
		inCheck = true;
		return ans;
	}

	/**
	 * Section thta deals with the state of the board
	 */
	
	public void reset() {
		gameMoves = new ArrayList<>();
		PiecesFactory pf = PiecesFactory.getInstance();
		char[] pos = {'a', '1'};
		for (int i = 0; i < 8; i++, pos[1]++) {
			for (int j = 0; j < 8; j++, pos[0]++) {
				board[j][i] = pf.createPiece(new String (pos));
				if (board[j][i] instanceof King) {
					if (board[j][i].getColor() == playingColor) ourKing = board[j][i];
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
					System.out.print("w ");
				else
					System.out.print("b ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Setters and getters
	 */
	
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
		AbstractPiece temp = ourKing;
		ourKing = opponentsKing;
		opponentsKing = temp;
		updateInCheck();
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

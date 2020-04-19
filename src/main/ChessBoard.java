package main;
import java.util.ArrayList;

import auxiliary.*;
import pieces.*;

public class ChessBoard {
	private static ChessBoard uniqueInstance = null;
	private AbstractPiece[][] board;
	private final PiecesFactory pf;
	
	private static boolean playingColor;
	private static boolean playerTurn;
	
	private static boolean forceMode;
	
	private ChessBoard() {
		board = new AbstractPiece[8][8];
		pf = PiecesFactory.getInstance();
		playerTurn = true;
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
	
	private static King ourKing, opponentsKing;
	
	public static void updateOurInCheck() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				AbstractPiece p = ChessBoard.getInstance().getPiece(new Position(i, j));
				if (!(p instanceof VoidPiece) && p.getColor() != playingColor) {
					if (p.getPossibleMoves().contains(ourKing.getPosition())) {
						ourKing.inCheck = true;
						return;
					}
				}
			}
		}
		ourKing.inCheck = false;
	}
	
	/**
	 * Section that deals with the game moves
	 * Function undo that sets game to one move back
	 */

	/**
	 * gameMoves records the moves that occurred this game
	 * it remembers the piece that was taken and the position of the piece that took it;
	 * if the taken piece is the king this means that the king castled - special case
	 */
	private static ArrayList<Pair<AbstractPiece, Position>> gameMoves;
	
	public void undo() {
		try {			
			Pair<AbstractPiece, Position> move = gameMoves.remove(0);
			AbstractPiece takenPiece = move.first;
			
			/* this was the king castling */
			if (takenPiece instanceof King) {
				Rook rook;
				
				/* kindside */
				if (takenPiece.getPosition().getLetter() == 6) {
					if (takenPiece.getColor() == true) {
						rook = (Rook) getPiece(new Position("f1"));
						board[4][0] = takenPiece; takenPiece.setPosition(new Position ("e1"));
						board[5][0] = new VoidPiece("f1");
						board[6][0] = new VoidPiece("g1");
						board[7][0] = rook; rook.setPosition(new Position ("h1"));
					} else {
						rook = (Rook) getPiece(new Position("f8"));
						board[4][7] = takenPiece; takenPiece.setPosition(new Position ("e8"));
						board[5][7] = new VoidPiece("f8");
						board[6][7] = new VoidPiece("g8");
						board[7][7] = rook; rook.setPosition(new Position ("h8"));
					}
				} else 
				/* queenside */
				{
					if (takenPiece.getColor() == true) {
						rook = (Rook) getPiece(new Position("d1"));
						board[0][0] = rook; rook.setPosition(new Position("a1"));
						board[1][0] = new VoidPiece("b1");
						board[2][0] = new VoidPiece("c1");
						board[3][0] = new VoidPiece("d1");
						board[4][0] = takenPiece; takenPiece.setPosition(new Position("e1"));
					} else {
						rook = (Rook) getPiece(new Position("d8"));
						board[0][7] = rook; rook.setPosition(new Position("a8"));
						board[1][7] = new VoidPiece("b8");
						board[2][7] = new VoidPiece("c8");
						board[3][7] = new VoidPiece("d8");
						board[4][7] = takenPiece; takenPiece.setPosition(new Position("e8"));
					}
				}
				rook.movesMade = 0;
				((King)takenPiece).movesMade = 0;
				
				 /* setting player's turn */
				playerTurn = takenPiece.getColor();
				 
				return;
			}
			
			AbstractPiece movedPiece = getPiece(takenPiece.getPosition());
			movedPiece.move(move.second);
			gameMoves.remove(0);
			setPiece(takenPiece.getPosition(), takenPiece);
			
			/* setting player's turn */
			playerTurn = movedPiece.getColor();
			
			/* Resetting pawn/king/rook first move variable */
			if (movedPiece instanceof Pawn) {
				if (movedPiece.getColor() == true) {
					if (movedPiece.getPosition().getNumber() == 1) ((Pawn) movedPiece).resetMoved();
				} else {
					if (movedPiece.getPosition().getNumber() == 6) ((Pawn) movedPiece).resetMoved();
				}
			}
			if (movedPiece instanceof King) {
				((King) movedPiece).movesMade -= 2;
			}
			if (movedPiece instanceof Rook) {
				((Rook) movedPiece).movesMade -= 2;
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Game is in its original state.");
		}
	}
	
	/**
	 * adds move to the history of the moves of the game
	 * @param p = piece that was taken
	 * @param originalPos = original position of piece that moved onto the square of the taken piece
	 */
	public void recordMove (AbstractPiece p, Position originalPos) {
		gameMoves.add(0, new Pair<>(p, originalPos));
	}
	
	public ArrayList<Pair<Position, Position>> convertHistory () {
		ArrayList<Pair<Position, Position>> history = new ArrayList<>();
		
		for (Pair<AbstractPiece, Position> move: gameMoves) {
			history.add(0, new Pair<>(move.second, move.first.getPosition()));
		}
		
		return history;
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
		
		boolean originalInCheck = ourKing.inCheck;
		piece.move(newPos);
		updateOurInCheck();
		undo();
		
		boolean ans = !ourKing.inCheck;
		ourKing.inCheck = originalInCheck;
		return ans;
	}

	/**
	 * Section that deals with the state of the board
	 */
	
	public void reset() {
		gameMoves = new ArrayList<>();
		char[] pos = {'a', '1'};
		for (int i = 0; i < 8; i++, pos[1]++) {
			for (int j = 0; j < 8; j++, pos[0]++) {
				board[j][i] = pf.createPiece(new String (pos));
				if (board[j][i] instanceof King) {
					if (board[j][i].getColor() == playingColor) ourKing = ((King) board[j][i]);
					else opponentsKing = ((King) board[j][i]);
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
	
	public void computeMove(Pair<Position, Position> move) {
		AbstractPiece piece = getPiece(move.first);
		piece.move(move.second);
		updatePlayerTurn();
	}

	public void printBoard() {
		System.out.println();
		System.out.println("========================");
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
					System.out.print("T");
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
	public void reversePlayingColor() {
		setPlayingColor(!playingColor);
	}
	
	public static boolean isInCheck() {
		return ourKing.inCheck;
	}
	
	public static void updatePlayerTurn() {
		playerTurn = !playerTurn;
	}

	public static boolean isPlayingColor() {
		return playingColor;
	}

	public void setPlayingColor(boolean playingC) {
		if (playingColor != playingC) {
			King temp = ourKing;
			ourKing = opponentsKing;
			opponentsKing = temp;
		}
		
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

	public static King getOurKing() {
		return ourKing;
	}

	public static King getOpponentsKing() {
		return opponentsKing;
	}
	
	
}

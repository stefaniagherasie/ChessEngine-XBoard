package auxiliary;

import pieces.*;

public class PiecesFactory {
	private static PiecesFactory uniqueInstance;
	
	private PiecesFactory() {}
	
	public static PiecesFactory getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new PiecesFactory();
		return uniqueInstance;
	}
	
	public AbstractPiece createPiece(String s) {
		/* white section */
		if (s.equals("a1"))
			return new Rook("white", "a1");	
		if (s.equals("h1"))
			return new Rook("white", "h1");
		if (s.equals("b1"))
			return new Knight("white", "b1");
		if (s.equals("g1"))
			return new Knight("white", "g1");
		if (s.equals("c1"))
			return new Bishop("white", "c1");
		if (s.equals("f1"))
			return new Bishop("white", "f1");	
		if (s.equals("d1"))
			return new Queen("white", "d1");
		if (s.equals("e1"))
			return new King("white", "e1");
		if (s.equals("a2"))
			return new Pawn("white", "a2");
		if (s.equals("b2"))
			return new Pawn("white", "b2");
		if (s.equals("c2"))
			return new Pawn("white", "c2");
		if (s.equals("d2"))
			return new Pawn("white", "d2");
		if (s.equals("e2"))
			return new Pawn("white", "e2");
		if (s.equals("f2"))
			return new Pawn("white", "f2");
		if (s.equals("g2"))
			return new Pawn("white", "g2");
		if (s.equals("h2"))
			return new Pawn("white", "h2");
		
		/* black section */
		if (s.equals("a8"))
			return new Rook("black", "a8");
		if (s.equals("h8"))
			return new Rook("black", "h8");
		/*if (s.equals("b8"))
			return new Knight("black", "b8");
		if (s.equals("g8"))
			return new Knight("black", "g8");	
		if (s.equals("c8"))
			return new Bishop("black", "c8");
		if (s.equals("f8"))
			return new Bishop("black", "f8");		
		if (s.equals("d8"))
			return new Queen("black", "d8");*/
		if (s.equals("e8"))
			return new King("black", "e8");	
		/*if (s.equals("a7"))
			return new Pawn("black", "a7");
		if (s.equals("b7"))
			return new Pawn("black", "b7");
		if (s.equals("c7"))
			return new Pawn("black", "c7");
		if (s.equals("d7"))
			return new Pawn("black", "d7");
		if (s.equals("e7"))
			return new Pawn("black", "e7");
		if (s.equals("f7"))
			return new Pawn("black", "f7");
		if (s.equals("g7"))
			return new Pawn("black", "g7");
		if (s.equals("h7"))
			return new Pawn("black", "h7");*/
		return new VoidPiece(s);
	}
}

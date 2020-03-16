package commands;
import auxiliary.Position;
import pieces.AbstractPiece;
import pieces.VoidPiece;
import main.ChessBoard;

public class MoveCommand extends Command{
	private Position startPos, endPos;
	
	public MoveCommand(String arg) {
		startPos = new Position(arg.substring(0, 2));
		endPos = new Position(arg.substring(2));
	}
	
	@Override
	public void execute() {
		ChessBoard b = ChessBoard.getInstance();
		
		//changing the board
		AbstractPiece movedPiece = b.getPiece(startPos);
		movedPiece.move(endPos);
		b.setPiece(startPos, new VoidPiece());
		b.setPiece(endPos, movedPiece);
		
		//if(!b.isforceMode()) {
			//next player's turn
			b.updatePlayerTurn();
			
			
			/* DEBUG */
			b.printBoard();
			//System.out.println(movedPiece.getPossibleMoves().toString());
	//	}
	}
}

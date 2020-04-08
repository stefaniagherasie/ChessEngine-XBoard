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
		/* changing the board */
		ChessBoard.getInstance().getPiece(startPos).move(endPos);
		
		/* checking if the engine is in check */
		ChessBoard.updateInCheck();
		
		/* next player's turn */
		ChessBoard.updatePlayerTurn();
	}
}

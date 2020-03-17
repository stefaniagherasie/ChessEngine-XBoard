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
		//changing the board
		ChessBoard.getInstance().getPiece(startPos).move(endPos);
		
		//next player's turn
		ChessBoard.updatePlayerTurn();
	}
}

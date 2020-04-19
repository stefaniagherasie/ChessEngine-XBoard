package commands;
import auxiliary.Pair;
import auxiliary.Position;
import main.ChessBoard;

public class MoveCommand extends Command{
	private Pair<Position, Position> move;
	
	public MoveCommand(String arg) {
		move = new Pair<>();
		move.first = new Position(arg.substring(0, 2));
		move.second = new Position(arg.substring(2));
	}
	
	@Override
	public void execute() {
		/* changing the board */
		ChessBoard.getInstance().computeMove(move);
		
		/* checking if the engine is in check */
		ChessBoard.updateOurInCheck();
	}
}

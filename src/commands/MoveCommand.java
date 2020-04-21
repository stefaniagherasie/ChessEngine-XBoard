package commands;
import auxiliary.Pair;
import auxiliary.Position;
import main.ChessBoard;

public class MoveCommand extends Command{
	private Pair<Position, Position> move;
	
	public MoveCommand(String arg) {
		move = new Pair<>();
		
		if (arg.equals("O-O")) {
			if (ChessBoard.isPlayingColor()) {
				move.first = new Position("e8");
				move.second = new Position("g8");
			} else {
				move.first = new Position("e1");
				move.second = new Position("g1");
			}
		} else if (arg.equals("O-O-O")) {
			if (ChessBoard.isPlayingColor()) {
				move.first = new Position("e8");
				move.second = new Position("c8");
			} else {
				move.first = new Position("e1");
				move.second = new Position("c1");
			}
		} else {
			move.first = new Position(arg.substring(0, 2));
			move.second = new Position(arg.substring(2));
		}
	}
	
	@Override
	public void execute() {
		/* changing the board */
		ChessBoard.getInstance().computeMove(move);
		
		/* checking if the engine is in check */
		ChessBoard.updateOurInCheck();
	}
}

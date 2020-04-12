package commands;

import main.ChessBoard;

public class UndoCommand extends Command{

	@Override
	public void execute() {
		ChessBoard.getInstance().undo();
	}
	
}

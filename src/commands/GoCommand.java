package commands;

import main.ChessBoard;

public class GoCommand extends Command {
	
	@Override
	public void execute() {
		ChessBoard.getInstance().setforceMode(false);
	}

}

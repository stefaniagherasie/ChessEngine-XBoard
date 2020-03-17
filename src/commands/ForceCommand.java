package commands;

import main.ChessBoard;

public class ForceCommand extends Command{
	
	@Override
	public void execute() {
		ChessBoard.getInstance().setforceMode(true);
	}

}

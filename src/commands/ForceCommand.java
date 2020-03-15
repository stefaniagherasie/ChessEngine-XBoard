package commands;

import main.ChessBoard;

public class ForceCommand extends Command{
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChessBoard b = ChessBoard.getInstance();
		b.setforceMode(true);
	}

}

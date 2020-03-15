package commands;

import main.ChessBoard;

public class GoCommand extends Command {
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChessBoard b = ChessBoard.getInstance();
		b.setforceMode(false);
		b.setPlayingColor(b.isPlayerTurn());
	}

}

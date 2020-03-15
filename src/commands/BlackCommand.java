package commands;

import main.ChessBoard;

public class BlackCommand extends Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChessBoard b = ChessBoard.getInstance();
		b.setPlayingColor(false);
	}

}

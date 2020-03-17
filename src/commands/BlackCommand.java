package commands;

import main.ChessBoard;

public class BlackCommand extends Command{

	@Override
	public void execute() {
		ChessBoard.getInstance().setPlayingColor(false);
	}

}

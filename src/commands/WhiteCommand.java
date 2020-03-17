package commands;

import main.ChessBoard;

public class WhiteCommand extends Command{
	@Override
	public void execute() {
		ChessBoard.getInstance().setPlayingColor(true);
	}

}

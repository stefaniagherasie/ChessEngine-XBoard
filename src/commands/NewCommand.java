package commands;
import main.*;

public class NewCommand extends Command{
	@Override
	public void execute() {
		ChessBoard b = ChessBoard.getInstance();
		b.reset();
		b.setPlayerTurn(true);
	}

}

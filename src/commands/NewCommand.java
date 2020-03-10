package commands;
import main.*;

public class NewCommand extends Command{
	
	public NewCommand() {
		execute();
		print();
	}
	
	@Override
	protected void print() {
		System.out.println("new");
	}
	
	@Override
	protected void execute() {
		ChessBoard.getInstance().reset();
	}

}

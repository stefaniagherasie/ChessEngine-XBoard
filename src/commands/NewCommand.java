package commands;
import main.*;

public class NewCommand extends Command{
	
	public NewCommand(String args) {
		super(args);
		// TODO Auto-generated constructor stub
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

package commands;
import main.*;

public class NewCommand extends Command{
	
	public NewCommand(String args) {
		super(args);
	}

	@Override
	protected void print() {
		System.out.println("new");
	}
	
	@Override
	protected void execute() {
		
	}

}

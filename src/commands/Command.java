package commands;

public abstract class Command {
	private String args;
	
	public Command(String args) {
		this.args = args;
	}
	
	/**
	 * Method that prints the command in accordance with xboard's needs
	 */
	protected abstract void print();
	/**
	 * Method that makes the internal changes for the engine
	 */
	protected abstract void execute();
	
	public String getArgs() {
		return args;
	}
}

package commands;

public abstract class Command {
	/**
	 * Method that prints the command in accordance with xboard's needs
	 */
	protected abstract void print();
	/**
	 * Method that makes the internal changes for the engine
	 */
	protected abstract void execute();
}

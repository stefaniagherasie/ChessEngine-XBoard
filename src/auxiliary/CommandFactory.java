package auxiliary;
import commands.*;

public class CommandFactory {
	private static CommandFactory uniqueInstance;
	
	private CommandFactory() {}
	
	public static CommandFactory getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new CommandFactory();
		return uniqueInstance;
	}
	
	public Command createCommand(String[] s) {
		if (s[0].equals("xboard"))
			return new XBoardCommand(s[1]);
		if (s[0].equals("new"))
			return new NewCommand(s[1]);
		if (s[0].equals("force"))
			return new ForceCommand(s[1]);
		if (s[0].equals("go"))
			return new GoCommand(s[1]);
		if (s[0].equals("white"))
			return new WhiteCommand(s[1]);
		if (s[0].equals("black"))
			return new BlackCommand(s[1]);
		if (s[0].equals("quit"))
			return new QuitCommand(s[1]);
		if (s[0].equals("resign"))
			return new ResignCommand(s[1]);
		if (s[0].equals("move"))
			return new MoveCommand(s[1]);
		
		return null;
	}
}

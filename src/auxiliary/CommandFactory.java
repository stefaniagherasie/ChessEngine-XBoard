package auxiliary;
import commands.*;
import main.ChessBoard;

public class CommandFactory {
	private static CommandFactory uniqueInstance;
	
	private CommandFactory() {}
	
	public static CommandFactory getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new CommandFactory();
		return uniqueInstance;
	}
	
	public Command createCommand(String s) {
		
		if(s.equals("protover"))
			return new ProtoverCommand();
		if (s.equals("xboard"))
			return new XBoardCommand();
		if (s.equals("new"))
			return new NewCommand();
		if (s.equals("force"))
			return new ForceCommand();
		if (s.equals("go"))
			return new GoCommand();
		if (s.equals("white"))
			return new WhiteCommand();
		if (s.equals("black"))
			return new BlackCommand();
		if (s.equals("quit"))
			return new QuitCommand();
		if (s.equals("resign"))
			return new ResignCommand();
		if (s.equals("undo"))
			return new UndoCommand();
		if (isMoveCommand(s))
			return new MoveCommand(s);
		
		
		return new VoidCommand();
	}
	
	private boolean isMoveCommand(String s) {
		if (s.equals("O-O")) {
			return true;
		} else if (s.equals("O-O-O")) {
			return true;
		}
		
		if(s.length() >= 4)
			return Position.legalPosition(s.substring(0,2)) && Position.legalPosition(s.substring(2));
		else return false;
	}
}

package auxiliary;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import commands.Command;

public class CommandReader {
	
	private final BufferedReader in;
	private CommandFactory cf;
	
	public CommandReader (InputStream in) {
		this.in = new BufferedReader(new InputStreamReader(in));
		cf = CommandFactory.getInstance();
	}
	
	/**
	 * Works like an Iterator's next command
	 * @return the command read from xboard
	 */
	public Command next() {
		try {
			/*FileWriter fw = new FileWriter("fisier.txt", true);
			String[] a = split(read());
			fw.write(a[0] + " " + a[1] + "\n");
			fw.close();
			return cf.createCommand(a);*/
			return cf.createCommand(split(read())[0]);
		}
		catch (LineErrorException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Method that splits the xboard line
	 * 
	 * @param line = the line as read from xboard
	 * 
	 * @return a string array with the first position as the command itself and
	 * the second one as the arguments of the command
	 */
	private String[] split(String line) throws LineErrorException{
		String[] command;
		int i = line.indexOf(' ');
		if (i == -1) {
			command = new String[2];
			command[0] = new String(line);
			command[1] = null;
		}
		else{
			command = line.split(" ");
		}
		
		return command;
	}
	
	/**
	 * Reads the next line outputed by xboard
	 */
	private String read() throws LineErrorException{
		try {
			String line = in.readLine();
			if (line == null)
				throw new LineErrorException();
			return line.trim();			
		}
		catch (IOException e) {
			throw new LineErrorException();
		}
	}
	
}

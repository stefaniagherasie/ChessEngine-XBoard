package auxiliary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import commands.Command;

public class CommandReader {
	
	private final BufferedReader in;
	
	public CommandReader (InputStream in) {
		this.in = new BufferedReader(new InputStreamReader(in));
	}
	
	public Command next() {
		return null;
	}
	
	/**
	 * Method that splits the xboard line
	 * 
	 * @param line = the line as read from xboard
	 * 
	 * @return a string array with the first position as the command itself and
	 * the second one as the arguments of the command
	 */
	private String[] split(String line) {
		if (line == null)
			return null;
		
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
	private String read() {
		try {
			String line = in.readLine();
			return line.trim();			
		}
		catch (IOException e) {
			return null;
		}
	}
	
}

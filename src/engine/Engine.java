package engine;
import java.io.IOException;
import auxiliary.LineErrorException;
import opening.OpeningParser;

public class Engine {
	private Strategy strategy;
	
	public Engine() {
		try {
			strategy = OpeningParser.parse();
		} catch (IOException | LineErrorException e) {
			
			e.printStackTrace();
		}
	}
}

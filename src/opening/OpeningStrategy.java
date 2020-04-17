package opening;
import auxiliary.*;
import java.util.*;

public class OpeningStrategy {
	/**
	 * Each chess board position that can  occur in the different opening lines 
	 * is stored together with the known next moves for that position
	 */
	private Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> gameStates;
	

	public OpeningStrategy(Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
	}
	
	// Should treat the gain of the next moves if needed. 
}

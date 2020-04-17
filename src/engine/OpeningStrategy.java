package engine;
import auxiliary.*;
import opening.OpeningMove;

import java.util.*;

public class OpeningStrategy implements Strategy{
	/**
	 * Each chess board position that can  occur in the different opening lines 
	 * is stored together with the known next moves for that position
	 */
	private Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> gameStates;
	

	public OpeningStrategy(Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
	}


	@Override
	public int eval() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// Should treat the gain of the next moves if needed. 
}

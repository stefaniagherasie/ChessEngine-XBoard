package opening;
import auxiliary.*;
import main.*;
import commands.*;
import pieces.*;
import java.util.*;

public class OpeningStrategy {
	private Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> gameStates;
	
	public OpeningStrategy(Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
	}
}

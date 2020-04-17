package opening;
import auxiliary.*;
import main.*;
import commands.*;
import pieces.*;
import java.util.*;

public class OpeningStrategy {
	private Map<Pair<Position, Position>, List<OpeningMove>> gameStates;
	
	public OpeningStrategy(Map<Pair<Position, Position>, List<OpeningMove>> gameStates) {
		this.gameStates = gameStates;
	}
}

package engine;

import auxiliary.Pair;
import auxiliary.Position;

public interface Strategy {
	public int eval(boolean player);
	public Pair<Position, Position> nextMove();
	public int getDepth();
}

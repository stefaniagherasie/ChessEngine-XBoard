package engine;

import auxiliary.Pair;
import auxiliary.Position;

public interface Strategy {
	public int eval();
	public Pair<Position, Position> bestNextMove();
}

package engine;

import java.util.ArrayList;

import auxiliary.Pair;
import auxiliary.Position;

public interface Strategy {
	public double eval(boolean player);
	public ArrayList<Pair<Position, Position>> nextMoves();
	public int getDepth();
}

package opening;
import auxiliary.*;



public class OpeningMove {
	/**
	 * Stores the recommended next moves from a certain board state.
	 */
	Pair<Position, Position> nextMove;
    /**
     * The gain of the move which represents its significance.
     */
    private int gain;

    public OpeningMove(String move, int gain) {
    	nextMove = new Pair<Position, Position>(new Position(move.substring(0, 1)),
    											new Position(move.substring(2, 3)));
        this.gain = gain;
    }
    
    public Pair<Position, Position> getNextMove() {
        return this.nextMove;
    }
    
    public int getGain() {
    	return this.gain;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gain;
		result = prime * result + ((nextMove == null) ? 0 : nextMove.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpeningMove other = (OpeningMove) obj;
		if (gain != other.gain)
			return false;
		if (nextMove == null) {
			if (other.nextMove != null)
				return false;
		} else if (!nextMove.equals(other.nextMove))
			return false;
		return true;
	}

    
}

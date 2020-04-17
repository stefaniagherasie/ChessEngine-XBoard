package opening;
import auxiliary.*;
import main.*;
import commands.*;
import pieces.*;
import java.util.Objects;

public class OpeningMove {
	private Position fromPos;
	private Position toPos;	
    private int gain;

    public OpeningMove(String move, int gain) {
        this.fromPos = new Position(move.substring(0, 1));
        this.toPos = new Position(move.substring(2, 3));
        this.gain = gain;
    }
    
    public Position getFromPos() {
        return this.fromPos;
    }

    public Position getToPOs() {
        return this.toPos;
    }
    
    public int getGain() {
    	return this.gain;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromPos == null) ? 0 : fromPos.hashCode());
		result = prime * result + gain;
		result = prime * result + ((toPos == null) ? 0 : toPos.hashCode());
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
		if (fromPos == null) {
			if (other.fromPos != null)
				return false;
		} else if (!fromPos.equals(other.fromPos))
			return false;
		if (gain != other.gain)
			return false;
		if (toPos == null) {
			if (other.toPos != null)
				return false;
		} else if (!toPos.equals(other.toPos))
			return false;
		return true;
	}
    
}

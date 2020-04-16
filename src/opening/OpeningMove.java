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
    public boolean equals(Object o) {
    	if(this == o) return true;
    	OpeningMove obj = (OpeningMove)o;
    	return (this.fromPos == obj.fromPos) && (this.toPos == obj.toPos) && (this.gain == obj.gain);
    }
    
    @Override 
    public int hashCode() {
    	return Objects.hash(fromPos, toPos, gain);
    }
}

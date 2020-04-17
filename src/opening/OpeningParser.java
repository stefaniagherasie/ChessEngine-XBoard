package opening;
import auxiliary.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class OpeningParser {
	
    /**
     * Parses the opening strategies from the csv file.
     * @param file "book.csv" - contains moves for opening strategies
     * @return A HashMap with game states from well known chess strategies.
     */
    public static OpeningStrategy parse(File file) throws IOException, LineErrorException {
    	// Strategies under the format <List of past moves, List of next moves and their gain>
        Map<ArrayList<Pair<Position, Position>>, List<OpeningMove>> gameStates;

        try {
        	// Read all lines and remove empty lines and comments
            List<String> lines = Files.lines(file.toPath())
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .collect(toList());
            // Obtain moves from each valid line
            gameStates = parseLines(lines);
        } catch (IOException error) {
            throw error;
        }
        
        // TO WE NEED IT??
        // Do some counting 
        /*Set<Integer> hashCodes = gameStates.keySet().stream().map(ChessBoard::hashCode).collect(toSet());;*/
        return new OpeningStrategy(gameStates);
    }
    
    /**
     * Treat the moves from each line
     * @return A HashMap with game states from well known chess strategies.
     */
    static Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> parseLines(List<String> lines) throws LineErrorException {
    	// Strategies under the format <List of past moves, List of next moves and their gain>
    	Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates = new HashMap<>();

        for (String line : lines) {
            String[] fields = line.split(";", -1);
            if (fields.length != 3) {
                throw new LineErrorException();
            }
            
            // Past moves
            String[] moves = fields[0].trim().split(" ");
            // Next move and gain
            String nextMove = fields[1].trim();
            
            // Store the past moves into an ArrayList
            ArrayList <Pair<Position, Position>> pastMoves = new ArrayList <Pair<Position, Position>>();
            
            if (moves.length == 1 && moves[0].isBlank()) {   
            	// pastMoves remains empty
            } else {
            	// Add all past moves as Pairs
            	for(String move : moves) {
               		pastMoves.add(new Pair<>(new Position(move.substring(0, 1)), new Position(move.substring(2, 3))));
               	}
            }
            
            // From the past moves we have a list of possible next moves
            List<OpeningMove> nextMoves = gameStates.computeIfAbsent(pastMoves, key -> new ArrayList<>());
            
            String[] moveAndGain = nextMove.split(("/"));
            
            // Save the next move as an OpeningMove (Pair<Position, Position> and gain)
            nextMoves.add(new OpeningMove(moveAndGain[0], Integer.parseInt(moveAndGain[1])));
        }

        return gameStates;
    }
}



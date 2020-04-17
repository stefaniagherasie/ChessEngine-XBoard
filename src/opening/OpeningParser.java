package opening;
import auxiliary.*;
import main.*;
import commands.*;
import pieces.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class OpeningParser {
    public static OpeningStrategy parse(File file) throws IOException {
        Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> gameStates;

        try {
            List<String> lines = Files.lines(file.toPath())
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .collect(toList());
            gameStates = parseLines(lines);
        } catch (IOException e) {
            throw e;
        }
        // Do some counting
        /*Set<Integer> hashCodes = gameStates.keySet().stream().map(ChessBoard::hashCode).collect(toSet());;*/
        return new OpeningStrategy(gameStates);
    }
    
    static Map< ArrayList<Pair<Position, Position>>, List<OpeningMove>> parseLines(List<String> lines) {
        Map< ArrayList <Pair<Position, Position>>, List<OpeningMove>> gameStates = new HashMap<>();

        for (String line : lines) {
            String[] fields = line.split(";", -1);
            if (fields.length != 3) {
                //eroare
            }

            String[] moves = fields[0].trim().split(" ");
            String nextMove = fields[1].trim();
           
            ArrayList <Pair<Position, Position>> pairMoves = new ArrayList <Pair<Position, Position>>();
            if (moves.length == 1 && moves[0].isBlank()) {   
            	//nu stiu exact ce trebuie aici
            } else {
            	for(String move : moves) {
               		pairMoves.add(new Pair<>(new Position(move.substring(0, 1)), new Position(move.substring(2, 3))));
               	}
            }
            
            List<OpeningMove> nextMoves = gameStates.computeIfAbsent(pairMoves, key -> new ArrayList<>());
            String[] moveAndGain = nextMove.split(("/"));
            nextMoves.add(new OpeningMove(moveAndGain[0], Integer.parseInt(moveAndGain[1])));
        }

        return gameStates;
    }
}



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
        Map< Pair<Position, Position>, List<OpeningMove>> gameStates;

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
    
    static Map<Pair<Position, Position>, List<OpeningMove>> parseLines(List<String> lines) {
        Map<Pair<Position, Position>, List<OpeningMove>> gameStates = new HashMap<>();

        for (String line : lines) {
            String[] fields = line.split(";", -1);
            if (fields.length != 3) {
                //eroare
            }

            String[] moves = fields[0].trim().split(" ");
            String nextMove = fields[1].trim();

            AbstractPiece[][] gameState;
            ChessBoard board = ChessBoard.getInstance();
            try {
                if (moves.length == 1 && moves[0].isBlank()) {
                    gameState = board.start(); 
                } else {
                    gameState = board.setStates(asList(moves)); //returneaza boardul dupa efectuarea miscarilor
                }
            } catch (IllegalMoveException e) {
                continue;
                //verificare miscare ilegala
            }

            // Get/create the list of possible moves for this position
            List<OpeningMove> nextMoves = gameStates.computeIfAbsent(gameState, key -> new ArrayList<>());

            // Create book move and add to list
            try {
                String[] moveAndGain = nextMove.split(("/"));
                nextMoves.add(new OpeningMove(moveAndGain[0], Integer.parseInt(moveAndGain[1]));
            } catch (IllegalMoveException e) {
              //eroare
            }
        }

        return gameStates;
    }
}



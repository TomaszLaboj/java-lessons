package org.example.boggle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

//https://excalidraw.com/#json=nCQftgvXbKEsbdCeHWh0f,PwNFDG5Zx7Sn7oBD44Pohg
public class Boggle2_1 {
    static private Character[][] grid = {
            {'C', 'A', 'T', 'I'},
            {'P', 'S', 'A', 'K'},
            {'A', 'P', 'H', 'D'},
            {'T', 'A', 'A', 'T'}
    };

    static String[] testWords = {"CAT", "TAP", "PAT"};

    public static void main(String[] args) {
        List<String> foundWords = findWords();

        System.out.println(foundWords);
    }

    static List<String> findWords() {
        List<String> foundWords = new ArrayList<>();

        for (String word : testWords) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if(checkSurroundingLetters(new Point(row, col), word, new ArrayList<>())) {
                        foundWords.add(word);
                    }
                }
            }

        }
        return foundWords;
    }

    private static boolean checkSurroundingLetters(Point coordinate, String word, List<Point> previousLetterCoordinates) {
        if (word.length() == 0) {
            return true;
        }
        Character letterToSearch = word.toCharArray()[0];
        if (letterToSearch.equals(grid[coordinate.x][coordinate.y])) {

            List<Point> updatedPreviousLetterCoordinates = new ArrayList<>(List.copyOf(previousLetterCoordinates));
            updatedPreviousLetterCoordinates.add(new Point(coordinate.x, coordinate.y));

            List<Point> coordinates = calculateSurroundingCoordinates(coordinate.x, coordinate.y);
            List<Point> coordinatesToCheck = coordinates
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < grid.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < grid[0].length)
                    .filter(point -> !updatedPreviousLetterCoordinates.contains(point))
                    .collect(Collectors.toList());

            for (Point coordinateToCheck : coordinatesToCheck) {
                boolean found = checkSurroundingLetters(coordinateToCheck, word.substring(1), updatedPreviousLetterCoordinates);
                if (found) {
                    return true;
                }
            }

        }

    return false;
    };

    static private List<Point> calculateSurroundingCoordinates(int row, int col) {
        List<Point> result = new ArrayList<>();
        result.add(new Point(row, col + 1));
        result.add(new Point(row + 1, col + 1));
        result.add(new Point(row + 1, col));
        result.add(new Point(row + 1, col - 1));
        result.add(new Point(row, col - 1));
        result.add(new Point(row - 1, col - 1));
        result.add(new Point(row - 1, col));
        result.add(new Point(row - 1, col + 1));
        return result;
    };
}

package org.example.boggle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

//https://excalidraw.com/#json=nCQftgvXbKEsbdCeHWh0f,PwNFDG5Zx7Sn7oBD44Pohg
public class Boggle2_1 {
    static private String[][] grid = {
            {"C", "A", "T", "I"},
            {"P", "S", "A", "K"},
            {"A", "R", "H", "D"},
            {"T", "C", "A", "T"}
    };

    static String[] testWords = {"CAT", "TAP", "PAT"};

    public static void main(String[] args) {
        List<String> foundWords = findWords();

        System.out.println(foundWords);
    }

    static List<String> findWords() {
        List<String> foundWords = new ArrayList<>();

        for (String word : testWords) {

            Queue<String> splitWord = new LinkedList<>(Arrays.asList(word.split("")));


            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if(checkSurroundingLetters(new Point(row, col), splitWord, new ArrayList<>())) {
                        foundWords.add(word);
                    }
                }
            }

        }
        return foundWords;
    }

    private static boolean checkSurroundingLetters(Point coordinate, Queue<String> word, List<Point> previousLetterCoordinates) {
        if (word.size() == 0) {
            return true;
        }
        Queue<String> wordCopy = new LinkedList<>(word); // T
        String letterToSearch = wordCopy.poll(); // A

        if (letterToSearch.equals(grid[coordinate.x][coordinate.y])) {


            List<Point> updatedPreviousLetterCoordinates = new ArrayList<>(List.copyOf(previousLetterCoordinates)); // 0,0 , 0,1
            updatedPreviousLetterCoordinates.add(new Point(coordinate.x, coordinate.y));

            List<Point> coordinates = calculateSurroundingCoordinates(coordinate.x, coordinate.y);
            List<Point> coordinatesToCheck = coordinates  // 0,2 , 1,2 , 1,1 , 1,0
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < grid.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < grid[0].length)
                    .filter(point -> pointIsNotInTheList(point, updatedPreviousLetterCoordinates))
                    .collect(Collectors.toList());

            for (Point coordinateToCheck : coordinatesToCheck) {
                return checkSurroundingLetters(coordinateToCheck, wordCopy, updatedPreviousLetterCoordinates);
            }

        }

    return false;
    };

    static boolean pointIsNotInTheList(Point point, List<Point> listOfPoints) {
        boolean flag = true;
        for (Point pointFromTheList : listOfPoints) {
            if (pointsAreTheSame(point.x, point.y, pointFromTheList.x, pointFromTheList.y)) {
                flag = false;
            }
        }
        return flag;
    }

    static boolean pointsAreTheSame(int x, int y, int secondX, int secondY) {
        boolean flag = false;
        if (x == secondX) {
            if (y == secondY) {
                flag = true;
            }
        }
        return flag;
    }
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

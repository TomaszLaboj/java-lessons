package org.example.boggle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
//https://excalidraw.com/#json=nCQftgvXbKEsbdCeHWh0f,PwNFDG5Zx7Sn7oBD44Pohg
public class Boggle2 {
    static private String[][] grid = {
            {"P", "A", "E", "I"},
            {"P", "S", "A", "K"},
            {"A", "R", "H", "D"},
            {"T", "P", "A", "T"}
    };

    static String[] testWords = {"PAT", "TAP", "PAT"};

    public static void main(String[] args) {
        List<String> foundWords = findWords();

        System.out.println(foundWords);
    }

    static List<String> findWords() {
        List<String> foundWords = new ArrayList<>();

        for (String word : testWords) {
            List<Point> letterCoordinates = new ArrayList<>();
            Queue<String> splitWord = new LinkedList<>(Arrays.asList(word.split("")));
            String firstLetter = splitWord.poll();
            List<Point> previousLetterCoordinates = new ArrayList<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (firstLetter.equals(grid[row][col])) {
                        letterCoordinates.add(new Point(row, col));
                        previousLetterCoordinates.add(new Point(row, col));
                    }
                }
            }

            if (checkSurroundingLetters(letterCoordinates, splitWord, previousLetterCoordinates)) {
                foundWords.add(word);
            }
        }
        return foundWords;
    }

    private static boolean checkSurroundingLetters(List<Point> lettersCoordinates, Queue<String> word, List<Point> previousLetterCoordinates) {
        if (word.size() == 0) {
            return true;
        }
        Queue<String> wordCopy = new LinkedList<>(word);
        String letterToSearch = wordCopy.poll();

        List<Point> coordinatesWithFoundLetters = new ArrayList<>();
        List<Point> updatedPreviousLetterCoordinates = new ArrayList<>(List.copyOf(previousLetterCoordinates));
        boolean letterFound = false;
        for (Point letterCoordinate : lettersCoordinates) {
            List<Point> coordinates = calculateSurroundingCoordinates(letterCoordinate.x, letterCoordinate.y);
            List<Point> coordinatesToCheck = coordinates
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < grid.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < grid[0].length)
                    .filter(point -> pointIsNotInTheList(point, updatedPreviousLetterCoordinates))
                    .collect(Collectors.toList());

            for (Point coordinateToCheck : coordinatesToCheck) {
                if (grid[coordinateToCheck.x][coordinateToCheck.y].equals(letterToSearch)) {
                    letterFound = true;
                    coordinatesWithFoundLetters.add(new Point(coordinateToCheck.x, coordinateToCheck.y));
                    updatedPreviousLetterCoordinates.add(new Point(coordinateToCheck.x, coordinateToCheck.y));
                }
            }

            if (letterFound) {
                for (Point coordinateWithFoundLetter : coordinatesWithFoundLetters) {
                    if (checkSurroundingLetters(coordinatesWithFoundLetters, wordCopy, updatedPreviousLetterCoordinates)) {
                        return checkSurroundingLetters(coordinatesWithFoundLetters, wordCopy, updatedPreviousLetterCoordinates);
                    }
                }
            }

        }
        return letterFound;
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

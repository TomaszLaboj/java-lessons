package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BoggleForCodeWars {
    static private char[][] boardToCheck;
    String wordToCheck;


    public BoggleForCodeWars(final char[][] board, final String word) {
        this.boardToCheck = board;
        this.wordToCheck = word;
    }

    public boolean check() {
        List<Point> letterCoordinates = new ArrayList<>();
        List<Point> previousLetterCoordinates = new ArrayList<>();
        char[] splitWord = wordToCheck.toCharArray();
        char firstLetter = splitWord[0];
        splitWord = removeFirst(splitWord);
        for (int row = 0; row < boardToCheck.length; row++) {
            for (int col = 0; col < boardToCheck[0].length; col++) {
                if (firstLetter == boardToCheck[row][col]) {
                    letterCoordinates.add(new Point(row, col));
                    previousLetterCoordinates.add(new Point(row, col));
                }
            }
        }


        return checkSurroundingLetters(letterCoordinates, splitWord, previousLetterCoordinates);
    }

    static char[] removeFirst(char[] array) {
        char[] result = new char[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            result[i - 1] = array[i];
        }
        return result;
    }

    private static boolean checkSurroundingLetters(List<Point> lettersCoordinates, char[] word, List<Point> previousLetterCoordinates) {
        if (word.length == 0) {
            return true;
        }
        char[] wordCopy = word.clone();
        char letterToSearch = wordCopy[0];
        wordCopy = removeFirst(wordCopy);
        List<Point> coordinatesWithFoundLetters = new ArrayList<>();
        List<Point> updatedPreviousLetterCoordinates = new ArrayList<>(List.copyOf(previousLetterCoordinates));
        boolean letterFound = false;
        for (Point letterCoordinate : lettersCoordinates) {
            List<Point> coordinates = calculateSurroundingCoordinates(letterCoordinate.x, letterCoordinate.y);
            List<Point> coordinatesToCheck = coordinates
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < boardToCheck.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < boardToCheck[0].length)
                    .filter(point -> pointIsNotInTheList(point, updatedPreviousLetterCoordinates))
                    .collect(Collectors.toList());

            for (Point coordinateToCheck : coordinatesToCheck) {
                if (boardToCheck[coordinateToCheck.x][coordinateToCheck.y] == letterToSearch) {
                    letterFound = true;
                    coordinatesWithFoundLetters.add(new Point(coordinateToCheck.x, coordinateToCheck.y));
                    updatedPreviousLetterCoordinates.add(new Point(coordinateToCheck.x, coordinateToCheck.y));
                }
            }

            if (letterFound) {
                for (Point coordinateWithFoundLetter : coordinatesWithFoundLetters) {
                    return checkSurroundingLetters(coordinatesWithFoundLetters, wordCopy, updatedPreviousLetterCoordinates);
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

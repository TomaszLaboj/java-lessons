package org.example.boggle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Boggle4 {
    static private char[][] grid = {
            {'P', 'A', 'E', 'I'},
            {'P', 'S', 'A', 'K'},
            {'A', 'R', 'H', 'D'},
            {'T', 'P', 'A', 'T'}
    };

    static String[] testWords = {"RAT"};

    public static void main(String[] args) {
        List<String> foundWords = new ArrayList<>();
        for (String word : testWords) {
            if(checkWord(word))
            foundWords.add(word);
        }
        System.out.println(foundWords);
    }

    static boolean checkWord(String word) {
        List<Point> coordinatesToCheck = new ArrayList<>();
        for (int row = 0; row < grid.length; row++ ) {
            for (int col = 0; col < grid[0].length; col++) {
                coordinatesToCheck.add(new Point(row, col));
            }
        }
        return check(word, coordinatesToCheck, new ArrayList<>());
    }

    static boolean check(String word, List<Point> coordinatesToCheck, List<Point> coordinatesToAvoid) {
        if (word.length() == 0) {
            return true;
        }
        char letterToFind = word.toCharArray()[0];
        List<Point> updatedCoordinatesToAvoid = new ArrayList<>(coordinatesToAvoid);
        List<Point> updatedCoordinatesToCheck =  coordinatesToCheck.stream()
                .filter(point -> point.x >= 0)
                .filter(point -> point.x < grid.length)
                .filter(point -> point.y >= 0)
                .filter(point -> point.y < grid[0].length)
                .filter(point -> pointIsNotInTheList(point, updatedCoordinatesToAvoid))
                .collect(Collectors.toList());

        for (Point coordinateToCheck : updatedCoordinatesToCheck) {
            int row = coordinateToCheck.x;
            int col = coordinateToCheck.y;
            if (grid[row][col] == letterToFind) {
                updatedCoordinatesToAvoid.add(coordinateToCheck);
                return check(
                        removeFirstLetter(word),
                        calculateSurroundingCoordinates(row, col),
                        updatedCoordinatesToAvoid
                        );
            }
        }
        return false;
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

    static String removeFirstLetter(String word) {
        String result = "";
        for (int i = 1; i < word.length(); i++) {
            result = result + word.toCharArray()[i];
        }
        return result;
    }

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
}

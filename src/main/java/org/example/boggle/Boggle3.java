package org.example.boggle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Boggle3 {
    static private char[][] grid = {
            {'P', 'A', 'E', 'I'},
            {'P', 'S', 'A', 'K'},
            {'A', 'R', 'H', 'D'},
            {'T', 'P', 'A', 'T'}
    };

    static String[] testWords = {"RAT", "CAT", "TARAS"};

    public static void main(String[] args) {
        List<String> foundWords = new ArrayList<>();
        for (String word : testWords) {
            if (checkWord(word)) {
                foundWords.add(word);
            }
        }
        System.out.println(foundWords);
    }

    static boolean checkWord(String word) {
        return check(word, 0, new ArrayList<>());
    }

    static boolean check(String word, int iteration, List<Point> coordinates) {
        if (word.length() == iteration) return true;
        List<Point> updatedCoordinates = coordinates;

        if (iteration == 0) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                  if (word.toCharArray()[iteration] == grid[row][col] && pointIsNotInTheList(new Point(row, col), updatedCoordinates)) {
                      updatedCoordinates.add(new Point(row, col));
                      return check(word, iteration + 1, updatedCoordinates);
                  }
                }
            }
        } else if (iteration > 0) {
            List<Point> surroundingSquares = calculateSurroundingCoordinates(updatedCoordinates.get(updatedCoordinates.size() -1 ).x,updatedCoordinates.get(updatedCoordinates.size() -1 ).y )
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < grid.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < grid[0].length)
                    .filter(point -> pointIsNotInTheList(point, updatedCoordinates))
                    .collect(Collectors.toList());
            for (Point coordinate : surroundingSquares) {
                if (word.toCharArray()[iteration] == grid[coordinate.x][coordinate.y] && pointIsNotInTheList(coordinate, updatedCoordinates)) {
                    updatedCoordinates.add(coordinate);
                    return check(word, iteration + 1, updatedCoordinates);
                }
            }
            return check(word, iteration - 1, updatedCoordinates);
        }
        return false;
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

package org.example.boggle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Boggle {
    static private String[][] grid = {
            {"P", "A", "A", "A"},
            {"A", "P", "C", "A"},
            {"T", "A", "A", "A"},
            {"A", "A", "A", "T"}
    };

    static  String[] listOfWords = {"PAT", "TAP", "CAT"};

    public static void main(String[] args) {
        List<String> foundWords = findWords(grid);
        System.out.println(foundWords);
    }

    static List<String> findWords(String[][] letterGrid){
        List<String> foundWords = new ArrayList<>();
        Queue<Point> firstLetterCoordinates;
        for (String word : listOfWords) {
            firstLetterCoordinates = new LinkedList<>();
            Queue<String> splitWordQueue = new LinkedList<>(Arrays.asList(word.split("")));
            String firstLetter = splitWordQueue.poll();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (firstLetter.equals(grid[row][col])) {
                        firstLetterCoordinates.offer(new Point(row, col));
                    }


                }
            }
            for (Point p : firstLetterCoordinates) {
                if(checkSurroundingLetters(p, splitWordQueue, letterGrid, firstLetterCoordinates.peek())) {
                    foundWords.add(word);
                }
            }
        }
        List<String> removedDuplicates = new ArrayList<>();

        for (String word : foundWords) {
            if (!removedDuplicates.contains(word)) {
                removedDuplicates.add(word);
            }
        };

        return removedDuplicates;
    };

    private static boolean checkSurroundingLetters(Point firstLetterCoordinate, Queue<String> word, String[][] letterGrid, Point previousLetterCoordinate) {
        Queue<String> wordCopy = new LinkedList<>(word);
        String letterToSearch = wordCopy.poll();
        boolean letterFound = false;
        boolean wordFound = false;
        List<Point> coordinatesWithFoundLetters = new ArrayList<>();

        List<Point> coordinates = calculateSurroundingCoordinates(firstLetterCoordinate.x, firstLetterCoordinate.y);
        List<Point> coordinatesToCheck = coordinates
                .stream()
                .filter(point -> point.x >= 0)
                .filter(point -> point.x < letterGrid.length)
                .filter(point -> point.y >= 0)
                .filter(point -> point.y < letterGrid[0].length)
                .filter(point -> pointIsNotPreviousPoint(point.x, point.y, previousLetterCoordinate.x, previousLetterCoordinate.y))
                .collect(Collectors.toList());

        for (Point p2 : coordinatesToCheck) {
            if (wordCopy.size() == 0 && letterGrid[p2.x][p2.y].equals(letterToSearch)) {
                wordFound = true;
            }
            if (wordCopy.size() != 0 && letterGrid[p2.x][p2.y].equals(letterToSearch)) {
                letterFound = true;
                coordinatesWithFoundLetters.add(new Point(p2.x, p2.y));
            }
        }

        if (wordFound) {
            return true;
        }

        if (letterFound) {
            for (Point p3 : coordinatesWithFoundLetters) {
                if (checkSurroundingLetters(p3, wordCopy, letterGrid, firstLetterCoordinate)) {
                    return checkSurroundingLetters(p3, wordCopy, letterGrid, firstLetterCoordinate);
                }
            }
        }
        return false;
    };

    static boolean pointIsNotPreviousPoint(int x, int y, int prevX, int prevY) {
        boolean flag = true;
        if (x == prevX) {
            if (y == prevY) {
                flag = false;
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


/*
  pseudocode
  version 1 (above):
  LOOP OVER THE WORDS FROM DICTIONARY {
      1. Take a word from the dictionary
      2. check if the 1st letter is in the grid
        recursively
        if yes, take the next letter and check if it's present in the surrounding of the previous letter
            check if out of bounds
            don't check position of the previous letter as the same letter cannot be reused in a single word
  }
    version2;
    brute force
    create words from the grid, 2 words first , then 3 ,
    then compare all the new words with the dictionary
 */
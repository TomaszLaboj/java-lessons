package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Boggle {
    static private String[][] grid = {
            {"A", "A", "A", "A"},
            {"A", "P", "A", "A"},
            {"A", "A", "A", "A"},
            {"A", "A", "A", "T"}
    };

    static  String[] listOfWords = {"PAT", "TYPE", "TEPID", "HOUSE", "APPLE", "USE", "PET", "PINS", "GOAT", "JAVA", "NOT", "TAP"};


    public static void main(String[] args) {
        List<String> foundWords = findWords(grid);
        System.out.println(foundWords);
    }

    static List<String> findWords(String[][] letterGrid){
        List<String> foundWords = new ArrayList<>();
        for (String word : listOfWords) {
            Queue<String> splitWordQueue = new LinkedList<>(Arrays.asList(word.split("")));
            String firstLetter = splitWordQueue.poll();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (firstLetter.equals(grid[row][col])) {
                        if(checkLetters(row, col, splitWordQueue, letterGrid, row, col)) {
                            foundWords.add(word);
                        }
                    }
                }
            }
        }
        return foundWords;
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

    static private boolean checkLetters(List<Point> allCoordinatesWithLetters,Queue<String> word, String[][] letterGrid, int prevRow, int prevCol) {
        Queue<String> wordCopy = new LinkedList<>(word);
        String letter = wordCopy.poll();
        boolean letterFound = false;
        boolean wordFound = false;
        List<Point> coordinatesWithFoundLetters = new ArrayList<>();
        Point examinedPoint = new Point();

        for (Point p1 : allCoordinatesWithLetters) {
            examinedPoint.x = p1.x;
            examinedPoint.y = p1.y;
            List<Point> coordinates = calculateCoordinates(p1.x, p1.y);
            List<Point> coordinatesToCheck = coordinates
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < letterGrid.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < letterGrid[0].length)
                    .filter(point -> pointIsNotPreviousPoint(point.x, point.y, prevRow, prevCol))
                    .collect(Collectors.toList());

            for (Point p2 : coordinatesToCheck) {
                if (wordCopy.size() == 0 && letterGrid[p2.x][p2.y].equals(letter)) {
                    wordFound = true;
                }
                if (wordCopy.size() != 0 && letterGrid[p2.x][p2.y].equals(letter)) {
                    letterFound = true;
                    coordinatesWithFoundLetters.add(new Point(p2.x, p2.y));
                }
            }
        }

        if (letterFound) {
            return checkLetters(coordinatesWithFoundLetters, wordCopy, letterGrid, examinedPoint.x, examinedPoint.y);
        }
        return wordFound;
    };

    static private List<Point> calculateCoordinates(int row, int col) {
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
  version 1:
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
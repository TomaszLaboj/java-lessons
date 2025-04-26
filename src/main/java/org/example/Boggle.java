package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Boggle {
    static private String[][] grid = {
            {"A", "T", "E", "E"},
            {"A", "P", "Y", "O"},
            {"T", "I", "N", "U"},
            {"E", "D", "S", "E"}
    };

    static  String[] listOfWords = {"PAT"};


    public static void main(String[] args) {
        Queue<String> splitWordQueue = new LinkedList<>(Arrays.asList("AT".split("")));
        boolean wordFound = checkLetters(1, 1, splitWordQueue, grid, 1, 1);
        System.out.println(wordFound);
//        List<String> foundWords = findWords(grid);
//        System.out.println(foundWords);
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

    /*
    0,0 0,1, 0,2
    1,0      1,2
    2,0 2,1, 2,2     1, 1
     */

    static boolean pointIsNotPreviousPoint(int x, int y, int prevX, int prevY) {
        boolean flag = true;
        if (x == prevX) {
            if (y == prevY) {
                flag = false;
            }
        }
        return flag;
    }
    static private boolean checkLetters(int row, int col,Queue<String> word, String[][] letterGrid, int prevRow, int prevCol) {
        Queue<String> wordCopy = new LinkedList<>(word);
        String letter = wordCopy.poll();
        List<Point> coordinates = calculateCoordinates(row, col);
        List<Point> coordinatesToCheck = coordinates
                .stream()
                .filter(point -> point.x >= 0)
                .filter(point -> point.x < letterGrid.length)
                .filter(point -> point.y >= 0)
                .filter(point -> point.y < letterGrid[0].length)
                .filter(point -> pointIsNotPreviousPoint(point.x, point.y, prevRow, prevCol))
                .collect(Collectors.toList());
        System.out.println(coordinates);
        System.out.println(coordinatesToCheck);
        System.out.println(wordCopy.size());
        boolean wordFound = false;

        for (Point p : coordinatesToCheck) {
            if (wordCopy.size() == 0 && letterGrid[p.x][p.y].equals(letter)) {
                wordFound = true;
                coordinatesToCheck.clear();
                break;
            }else if (letterGrid[p.x][p.y].equals(letter)) {
                checkLetters(p.x, p.y, wordCopy, letterGrid, row, col);
            }
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
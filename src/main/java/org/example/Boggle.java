package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Boggle {
    static private String[][] grid = {
            {"A", "T", "E", "E"},
            {"A", "P", "Y", "O"},
            {"T", "I", "N", "U"},
            {"E", "D", "S", "E"}
    };

    static private String[] dictionary;


    public static void main(String[] args) {
        System.out.println("boggle");
    }

    static List<String> findWords(String[][] letterGrid){
        List<String> foundWords = new ArrayList<>();
        for (String word : dictionary) {
            String[] splitWord = word.split("");
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (splitWord[0].equals(grid[row][col])) {

                        if(checkLetters(row, col, letter)) {
                            foundWords.add(word);
                        }
                    }
                }
            }
        }
        return foundWords;
    };

    static boolean checkLetters(int row, int col,char letter) {
        for (int newRow = row - 1; newRow < 2; newRow++) {
            for (int newCol = col - 1; newCol < 2; newCol++) {
                if(grid[newRow][newCol].equals(letter));
                return true;
            }
        }
        return false;
    };
}


/*
  pseudo code
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
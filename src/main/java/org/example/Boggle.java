package org.example;

public class Boggle {
    static private String[][] grid = {
            {"A", "T", "E", "E"},
            {"A", "P", "Y", "O"},
            {"T", "I", "N", "U"},
            {"E", "D", "S", "E"}
    };

    static private String[] dictionary;

    static private String[] foundWords;

    public static void main(String[] args) {
        System.out.println("boggle");
    }

    static String[] findWords(String[][] letterGrid){
        for (String word : dictionary) {
            String[] splitWord = word.split("");
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (splitWord[0].equals(grid[row][col])) {
                        
                    }
                }
            }
        }
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
            don't check position of the previous letter
  }

 */
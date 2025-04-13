package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    static List<String> words = Arrays.asList("cat", "dog", "house", "approach", "summer", "learn", "initialise", "programming");
    static List<String> images = Arrays.asList(
            "",
            "|\n|\n|\n|\n|\n|\n|\n-",
            "_______\n|/\n|\n|\n|\n|\n|\n|\n-",
            "_______\n|/    |\n|\n|\n|\n|\n|\n|\n-",
            "_______\n|/    |\n|     O\n|\n|\n|\n|\n|\n-",
            "_______\n|/    |\n|     O\n|     |\n|\n|\n|\n|\n-",
            "_______\n|/    |\n|     O\n|    /|\n|\n|\n|\n|\n-",
            "_______\n|/    |\n|     O\n|    /|\\\n|\n|\n|\n|\n-",
            "_______\n|/    |\n|     O\n|    /|\\\n|     |\n|\n|\n|\n-",
            "_______\n|/    |\n|     O\n|    /|\\\n|     |\n|    /\n|\n|\n-",
            "_______\n|/    |\n|     O\n|    /|\\\n|     |\n|    / \\\n|\n|\n-");
    static String randomWord;
    static int incorrectGuesses;
    static boolean runGame;
    static Scanner scanner = new Scanner(System.in);
    static List<String> guessedLetters;
    static String hiddenWord;


    public static void main(String[] args) {
        startGame();
        while (runGame) {
            System.out.println(hiddenWord);
            String userGuess = scanner.nextLine();


            if (userGuess.length() == 0) {
                System.out.println("Type a single letter or entire word and press enter");
            } else if (userGuess.length() > 1) {
                if (randomWord.equals(userGuess.toString())) {
                    handleGameWon();
                } else {
                    handleIncorrectGuess();
                }
            } else if (userGuess.length() == 1) {
                if (randomWord.contains(userGuess)) {
                    System.out.println("Well done");
                   guessedLetters.add(userGuess);
                   hiddenWord = createHiddenWord(randomWord, guessedLetters);
                    if (randomWord.equals(hiddenWord.toString())) {
                        handleGameWon();
                    }
                } else {
                    handleIncorrectGuess();
                }
            }
        }
    }

    static void startGame() {
        randomWord = words.get((int) (Math.random() * words.size()));
        incorrectGuesses = 0;
        runGame = true;
        guessedLetters = new ArrayList<>(randomWord.length());
        hiddenWord = createHiddenWord(randomWord, guessedLetters);
        System.out.println("Guess a single letter or entire word");
    }

    static String createHiddenWord(String word, List<String> guessedLetters) {
        List<String> hiddenWord = Arrays.asList(word.split(""));
        for (int i = 0; i < hiddenWord.size(); i++) {
            if (!guessedLetters.contains(hiddenWord.get(i))) {
                hiddenWord.set(i, "-");
            }
        }
        return String.join("", hiddenWord);
    }

    static void createImage() {
        System.out.println(images.get(incorrectGuesses));
    }

    static void handleGameWon() {
        System.out.println(hiddenWord);
        System.out.println("Congratulations you have guessed the word!");
        playAgain();
    }

    static void handleIncorrectGuess() {
        System.out.println("I'm afraid this is incorrect guess.");
        incorrectGuesses++;
        createImage();
        if (incorrectGuesses >= 10) {
            runGame = false;
            System.out.println("The word was: " + randomWord + ". Game over.");
            playAgain();
        }
    }

    static void playAgain() {
        System.out.println("Do you want to play again? (Y/N)");
        String answer = scanner.nextLine();
        if (answer.toLowerCase().equals("y")) {
            startGame();
        } else if(answer.toLowerCase().equals("n")) {
            System.out.println("Goodbye");
            runGame = false;
        }
    }
}

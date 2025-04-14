package org.example;

import java.util.*;

public class Hangman2 {
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
    static Set<String> guessedLetters;

    public static void main(String[] args) {

        startGame();
        while (runGame) {
            String hiddenWord = createHiddenWord(randomWord, guessedLetters);
            System.out.println(hiddenWord);
            String userGuess = scanner.nextLine();


            if (userGuess.length() == 0) {
                System.out.println("Type a single letter or try to guess the entire word and press enter");
            } else if (userGuess.length() > 1 && randomWord.equals(userGuess.toString())) {
                    showGameWonMessage();
                    runGame = askIfPlayAgain();
                    if (runGame) {startGame();}
            } else if (userGuess.length() == 1 && randomWord.contains(userGuess)) {
                System.out.println("Well done");
                guessedLetters.add(userGuess);
                hiddenWord = createHiddenWord(randomWord, guessedLetters);
                if (hiddenWord.equals(randomWord)) {
                    showGameWonMessage();
                    runGame = askIfPlayAgain();
                    if (runGame) {startGame();}
                }
            } else if ( userGuess.length() >= 1
                    && (!randomWord.equals(userGuess.toString()) || randomWord.contains(userGuess))) {
                handleIncorrectGuess();
                if (incorrectGuesses >= 10) {
                    System.out.println("The word was: " + randomWord + ". Game over.");
                    runGame = askIfPlayAgain();
                    if (runGame) {startGame();}
                }
            }
        }
    }

    static void startGame() {
        randomWord = words.get((int) (Math.random() * words.size()));
        incorrectGuesses = 0;
        runGame = true;
        guessedLetters = new HashSet<>(randomWord.length());
        System.out.println("Guess a single letter or entire word");
    }

    static String createHiddenWord(String word, Set<String> guessedLetters) {
        List<String> hiddenWord = Arrays.asList(word.split(""));
        for (int i = 0; i < hiddenWord.size(); i++) {
            if (!guessedLetters.contains(hiddenWord.get(i))) {
                hiddenWord.set(i, "-");
            }
        }
        return String.join("", hiddenWord);
    }

    static void printGallowsImage() {
        System.out.println(images.get(incorrectGuesses));
    }

    static void showGameWonMessage() {
        System.out.println(randomWord);
        System.out.println("Congratulations you have guessed the word!");
    }

    static void handleIncorrectGuess() {
        System.out.println("I'm afraid this is incorrect guess.");
        incorrectGuesses++;
        printGallowsImage();

    }

    static boolean askIfPlayAgain() {
        System.out.println("Do you want to play again? Press Y and enter if yes. Any key and enter to exit");
        String answer = scanner.nextLine();
        if (answer.toLowerCase().equals("y")) {
            return true;
        }
        System.out.println("Goodbye");
        return false;
    }
}

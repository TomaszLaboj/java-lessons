package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    static List<String> words = Arrays.asList("cat", "dog", "house", "approach", "summer", "learn", "initialise", "programming");
    static String randomWord;
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

    public static void main(String[] args) {
        randomWord = words.get((int) (Math.random() * words.size()));
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        int incorrectGuesses = 0;
        List<String> guessedLetters = new ArrayList<>(randomWord.length());

        while (!flag) {
            String message = "Guess a single letter or entire word";
            System.out.println(message);
            System.out.println(createHiddenWord(randomWord, guessedLetters));
            System.out.println(createImage(incorrectGuesses, images));
            String userGuess = scanner.nextLine();


            if (userGuess.length() == 0) {
                System.out.println(message);
            } else if (userGuess.length() > 1) {
                if (randomWord.equals(userGuess.toString())) {
                    System.out.println("Congratulations you have guessed the word!");
                } else {
                    System.out.println("I'm afraid this is incorrect guess");
                    if (incorrectGuesses == 10) {
                        flag = true;
                        System.out.println("you are hanged, game over");
                    }
                    incorrectGuesses++;
                }
            } else if (userGuess.length() == 1) {
                if (randomWord.contains(userGuess)) {
                    System.out.println("Well done, ");
                    guessedLetters.add(userGuess);
                } else {
                    System.out.println("I'm afraid this is incorrect guess");
                    if (incorrectGuesses == 10) {
                        flag = true;
                        System.out.println("The word was: " + randomWord + ". You are hanged, game over");
                    }
                    incorrectGuesses++;
                }
            }
        }
    }

    static String chooseRandomWord(List<String> words) {
        int randomNumber = (int) (Math.random() * words.size());
        return words.get(randomNumber);
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

    static String createImage(int incorrectGuesses, List<String> images) {
        return images.get(incorrectGuesses);
    }
}

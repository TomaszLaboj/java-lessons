package org.example;

import java.util.*;
import java.util.regex.Pattern;

public class CountWords {
    public static void main(String[] args) {
        String string = "this is long list of words. Apple, banana, orange, apple, banana, orange, apple, apple, banana, banana";
        Pattern pattern = Pattern.compile("\\p{Punct}");
        String newString = string.replaceAll(pattern.toString(), "");

        List<String> list = Arrays.asList(newString.split(" "));
        Map<String, Integer> wordData = new HashMap<>();

        for (String word : list) {
            if (wordData.containsKey(word)) {
                int count = wordData.get(word);
                wordData.put(word, count + 1);
            } else {
                wordData.put(word, 1);
            }
        } //for
        for (Map.Entry<String, Integer> entry : wordData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}



package org.example.boggle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            File listOfWords = new File("src/main/java/org/example/boggle/word-list.txt");
            Scanner scanner = new Scanner(listOfWords);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("cannot find file!");
            e.printStackTrace();
        }
    }
}

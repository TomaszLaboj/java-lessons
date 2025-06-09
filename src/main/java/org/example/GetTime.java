package org.example;
import java.time.LocalTime; // import the LocalTime class

public class GetTime {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.of(6,0)) && time.isBefore(LocalTime.of(22,0))) {
            System.out.println("It's day");
        }
        if (time.isAfter(LocalTime.of(22,0)) || time.isBefore(LocalTime.of(6,0))) {
            System.out.println("It's night");
        }
        System.out.println(time);
    }
}

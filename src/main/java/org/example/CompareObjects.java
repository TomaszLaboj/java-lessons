package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class CompareObjects {
    static Point point1 = new Point(1,0);
    static Point point2 = new Point(0,1);

    static List<Point> listOfPoints = new ArrayList<>();

    public static void main(String[] args) {
        listOfPoints.add(point1);
        listOfPoints.add(point2);
        System.out.println(listOfPoints.contains(new Point(1, 0)));
    }
}

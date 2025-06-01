package org.example;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
    static HashMap<Point, HashMap> map = new HashMap<>();
    public static void main(String[] args) {
        Point point1 = new Point(1,1);
        map.put(point1, new HashMap<>());

        Point point2 = new Point(2,1);
        Point point3 = new Point(1,2);
        map.get(point1).put(point2, new HashMap<>());
        map.get(point1).put(point3, new HashMap<>());
        map.get(point3).put(new Point(3,3), new HashMap<>());
    }
}

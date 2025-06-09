package org.example.mazesolver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MazeSolverBruteForce {

    static class MyPoint extends Point {
        public MyPoint parent;

        public MyPoint(){};

        public MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public MyPoint(int x, int y, MyPoint parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        public MyPoint getParent() {
            return parent;
        }

        public void setParent(MyPoint parent) {
            this.parent = parent;
        }
    }

    static Character[][] maze = {
            {'#', '#', '#', '#', '#'},
            {'#', 'S', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', 'E', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'},
    };

    public static void main(String[] args) {
        List<MyPoint> path = findStartingPointAndPaths();
        System.out.println("path is: " + path + ", length is: " + path.size());
    }

    static List<MyPoint> findStartingPointAndPaths() {

        MyPoint startingPoint = new MyPoint();
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if(maze[row][col].equals('S')) {
                    startingPoint.setLocation(row, col);
                }
            }
        }

        List<List<MyPoint>> allPaths = findPaths(startingPoint, new ArrayList<>(), new ArrayList<>());
        for (List<MyPoint> path : allPaths) {
            System.out.println(path);
        }

        List<MyPoint> shortesPath = allPaths
                .stream()
                .filter(list -> list.size() > 0)
                .min(Comparator.comparingInt(List::size))
                .orElse(new ArrayList<>());
        return shortesPath;
    }

    static List<List<MyPoint>> findPaths(MyPoint currentCoordinate, List<MyPoint> coordinatesHistory, List<List<MyPoint>> allPaths) {

        coordinatesHistory.add(currentCoordinate);
        if (maze[currentCoordinate.x][currentCoordinate.y] == 'E') {
            List<MyPoint> path = backtrackPath(coordinatesHistory, currentCoordinate);
            allPaths.add(path);
            return findPaths(currentCoordinate.parent, coordinatesHistory, allPaths);
        }

        List<MyPoint> surroundingCoordinates = calculateSurroundingCoordinates(currentCoordinate.x, currentCoordinate.y)
                .stream()
                .filter(point -> point.x >= 0)
                .filter(point -> point.x < maze.length)
                .filter(point -> point.y >= 0)
                .filter(point -> point.y < maze[0].length)
                .filter(point -> !maze[point.x][point.y].equals('#'))
                .filter(point -> !coordinatesHistory.contains(point))
                .collect(Collectors.toList());

        if (surroundingCoordinates.size() == 0 && currentCoordinate.parent != null) {
            return findPaths(currentCoordinate.parent, coordinatesHistory, allPaths);
        } else if (surroundingCoordinates.size() == 0 && currentCoordinate.parent == null) {

            return allPaths;
        }


        return findPaths(
                new MyPoint(
                        surroundingCoordinates.get(0).x,
                        surroundingCoordinates.get(0).y,
                        currentCoordinate
                ),
                coordinatesHistory,
                allPaths
        );
    }

    static List<MyPoint> backtrackPath(List<MyPoint> coordinates, MyPoint exitCoordinate) {
        List<MyPoint> path = new ArrayList<>();
        path.add(exitCoordinate);
        MyPoint parent = exitCoordinate.parent;
        while (parent != null) {
            MyPoint finalParent = parent;
            MyPoint current = coordinates.stream().filter(coordinate -> coordinate.equals(finalParent)).collect(Collectors.toList()).get(0);
            path.add(current);
            parent = current.parent;
        }
        return path;
    }

    static private List<MyPoint> calculateSurroundingCoordinates(int row, int col) {
        List<MyPoint> result = new ArrayList<>();
        result.add(new MyPoint(row, col + 1, new MyPoint(row, col)));
        result.add(new MyPoint(row + 1, col, new MyPoint(row, col)));
        result.add(new MyPoint(row, col - 1, new MyPoint(row, col)));
        result.add(new MyPoint(row - 1, col, new MyPoint(row, col)));
        return result;
    };
}

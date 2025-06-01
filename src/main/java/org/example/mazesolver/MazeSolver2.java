package org.example.mazesolver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MazeSolver2 {

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
            {'#', ' ', ' ', 'E', '#'},
            {'#', '#', '#', '#', '#'},
    };

    public static void main(String[] args) {
        List<MyPoint> path = findShortestPath();
        System.out.println("path is: " + path + ", length is: " + path.size());
    }

    static List<MyPoint> findShortestPath() {

        MyPoint startingPoint = new MyPoint();
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if(maze[row][col].equals('S')) {
                    startingPoint.setLocation(row, col);
                }
            }
        }

        List<MyPoint> coordinatesToCheck = new ArrayList<>();
        coordinatesToCheck.add(startingPoint);

        if (coordinatesToCheck.size() == 0) {
            return coordinatesToCheck;
        }

        return findShortestPath(coordinatesToCheck, new ArrayList<>(), 1);
    }

    static List<MyPoint> findShortestPath(List<MyPoint> coordinatesToCheck, List<MyPoint> previousCoordinates, int count) {

        List<MyPoint> newCoordinatesToCheck = new ArrayList<>();
        List<MyPoint> updatedPreviousCoordinates = new ArrayList<>(List.copyOf(previousCoordinates));

        for (MyPoint coordinate : coordinatesToCheck) {
            updatedPreviousCoordinates.add(coordinate);
        }

        for (MyPoint coordinate : coordinatesToCheck) {
            List<MyPoint> surroundingCoordinates = calculateSurroundingCoordinates(coordinate.x, coordinate.y)
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < maze.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < maze[0].length)
                    .filter(point -> !maze[point.x][point.y].equals('#'))
                    .filter(point -> !updatedPreviousCoordinates.contains(point))
                    .collect(Collectors.toList());
            for (MyPoint surroundingCoordinate : surroundingCoordinates) {
                newCoordinatesToCheck.add(surroundingCoordinate);
            }
        }
        System.out.println(newCoordinatesToCheck);
        for (MyPoint newCoordinate : newCoordinatesToCheck) {

            if (maze[newCoordinate.x][newCoordinate.y].equals('E')) {

                return backtrackPath(updatedPreviousCoordinates, newCoordinate);
            }
        }

        if (newCoordinatesToCheck.size() == 0) {
            return newCoordinatesToCheck;
        }

        return findShortestPath(newCoordinatesToCheck, updatedPreviousCoordinates, count + 1);
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

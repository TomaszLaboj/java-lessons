package org.example.mazesolver;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MazeSolver {

    static Character[][] maze = {
            {'#', '#', '#', '#', '#'},
            {'#', 'S', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', ' ', 'E', ' ', '#'},
            {'#', '#', '#', '#', '#'},
    };

    public static void main(String[] args) {

        System.out.println(findShortesPath());
    }

    static int findShortesPath() {

        Point startingPoing = new Point();
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if(maze[row][col].equals('S')) {
                    startingPoing.setLocation(row, col);
                }
            }
        }

        List<Point> coordinatesToCheck = new ArrayList<>();
        coordinatesToCheck.add(startingPoing);

        int shortest = findShortestPath(coordinatesToCheck, new ArrayList<>(), 1);
        return shortest;
    }

    static int findShortestPath(List<Point> coordinatesToCheck, List<Point> previousCoordinates, int count) {

        List<Point> newCoordinatesToCheck = new ArrayList<>();
        List<Point> updatedPreviousCoordinates = new ArrayList<>(List.copyOf(previousCoordinates));

        for (Point coordinate : coordinatesToCheck) {
            updatedPreviousCoordinates.add(coordinate);
            List<Point> surroundingCoordinates = calculateSurroundingCoordinates(coordinate.x, coordinate.y)
                    .stream()
                    .filter(point -> point.x >= 0)
                    .filter(point -> point.x < maze.length)
                    .filter(point -> point.y >= 0)
                    .filter(point -> point.y < maze[0].length)
                    .filter(point -> !maze[point.x][point.y].equals('#'))
                    .filter(point -> !updatedPreviousCoordinates.contains(point))
                    .collect(Collectors.toList());
            for (Point surroundingCoordinate : surroundingCoordinates) {
                newCoordinatesToCheck.add(surroundingCoordinate);
            }
        }

        for (Point newCoordinate : newCoordinatesToCheck) {
            if (maze[newCoordinate.x][newCoordinate.y].equals('E')) {
                return count;
            }
        }

        if (newCoordinatesToCheck.size() == 0) {
            return 0;
        }

        return findShortestPath(newCoordinatesToCheck, updatedPreviousCoordinates, count + 1);
    }

    static private List<Point> calculateSurroundingCoordinates(int row, int col) {
        List<Point> result = new ArrayList<>();
        result.add(new Point(row, col + 1));
        result.add(new Point(row + 1, col));
        result.add(new Point(row, col - 1));
        result.add(new Point(row - 1, col));
        return result;
    };
}

package org.example.mazesolver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MazeSolverBruteForce2 {

    static Character[][] maze = {
            {'#', '#', '#', '#', '#'},
            {'#', 'S', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', 'E', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'},

    };

    public static void main(String[] args) {
        List<Point> path = findStartingPointAndPaths();
        System.out.println("path is: " + path + ", length is: " + path.size());
    }

    static List<Point> findStartingPointAndPaths() {

        Point startingPoint = new Point();
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if(maze[row][col].equals('S')) {
                    startingPoint.setLocation(row, col);
                }
            }
        }

        List<List<Point>> allPaths = findPaths(startingPoint, new ArrayList<>(), new ArrayList<>());
        for (List<Point> path : allPaths) {
            System.out.println(path);
        }

        List<Point> shortesPath = allPaths
                .stream()
                .filter(list -> list.size() > 0)
                .min(Comparator.comparingInt(List::size))
                .orElse(new ArrayList<>());
        return shortesPath;
    }

    static List<List<Point>> findPaths(Point currentCoordinate, List<Point> coordinatesHistory, List<List<Point>> allPaths) {

        coordinatesHistory.add(currentCoordinate);

        if (maze[currentCoordinate.x][currentCoordinate.y] == 'E') {
            List<Point> path = new ArrayList<>(coordinatesHistory);
            allPaths.add(path);
            coordinatesHistory.remove(currentCoordinate); // backtrack
            return allPaths;
        }

        List<Point> surroundingCoordinates = calculateSurroundingCoordinates(currentCoordinate.x, currentCoordinate.y)
                .stream()
                .filter(point -> point.x >= 0 && point.x < maze.length)
                .filter(point -> point.y >= 0 && point.y < maze[0].length)
                .filter(point -> !maze[point.x][point.y].equals('#'))
                .filter(point -> !coordinatesHistory.contains(point))
                .collect(Collectors.toList());

        for (Point neighbor : surroundingCoordinates) {
            findPaths(neighbor, coordinatesHistory, allPaths);
        }

        coordinatesHistory.remove(currentCoordinate); // backtrack step

        return allPaths;
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

package org.example.mazesolver;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
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

        System.out.println(findShortestPathLength());
    }

    static int findShortestPathLength() {
        List<List<Point>> paths = new ArrayList<>();
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if(maze[row][col].equals('S')) {
                    List<Point> path = new ArrayList<>();
                    path.add(new Point(row, col));
                    paths.add(findPath(new Point(row, col), path));
                }
            }
        }
        int shortest = paths.get(0).size();
        for (List<Point> path : paths) {
            if (path.size() < shortest) {
                shortest = path.size();
            }
        }
        return shortest;
    }

    static List<Point> findPath(Point coordinate, List<Point> path) {
        if (maze[coordinate.x][coordinate.y].equals('E')) {
            return path;
        }

        List<Point> coordinatesToCheck = calculateSurroundingCoordinates(coordinate.x, coordinate.y)
                .stream()
                .filter(point -> point.x >= 0)
                .filter(point -> point.x < maze.length)
                .filter(point -> point.y >= 0)
                .filter(point -> point.y < maze[0].length)
                .filter(point -> !maze[point.x][point.y].equals('#'))
                .filter(point -> !path.contains(point))
                .collect(Collectors.toList());
        for (Point coordinateToCheck : coordinatesToCheck) {
            path.add(coordinateToCheck);
            return findPath(coordinateToCheck, path);
        }

        return path;
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

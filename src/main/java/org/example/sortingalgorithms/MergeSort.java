package org.example.sortingalgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MergeSort {
    public static void main(String[] args) {
        int[] list = {1, 6, 34, 9, 0, 2, 8, 23, 23, -15, 1234, -99, 55, 1, 765};
        List<Queue<Integer>> splittedList = split(list);
        List<Queue<Integer>> sorted = sort(splittedList);
        int[] sortedList = convert(sorted);
        for (int i : sortedList) {
            System.out.println(i);
        }
    }

    static List<Queue<Integer>> split(int[] list) {
        List<Queue<Integer>> listToSort = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            Queue<Integer> data = new LinkedList<>();
            data.offer(list[i]);
            listToSort.add(data);
        }
        return listToSort;
    }

    static int[] convert(List<Queue<Integer>> list) {
        int size = list.get(0).size();
        Queue<Integer> queue = list.remove(0);
        int[] returnList = new int[size];
        for (int i = 0; i < size; i++) {
            try {
                returnList[i] = queue.poll();
            } catch (Exception e) {};
        }
        return returnList;
    }

    static List<Queue<Integer>> sort(List<Queue<Integer>> listToSort) {

        List<Queue<Integer>> sorted = new ArrayList<>();

        int numberOfPairs;
        if (listToSort.size() % 2 == 0) {
            numberOfPairs = Math.floorDiv(listToSort.size(), 2);
        } else {
            numberOfPairs = Math.floorDiv(listToSort.size(), 2) + 1;
        }

        for (int i = 0, index = 0; i < numberOfPairs; i++, index = index + 2) {
            Queue<Integer> sortedQueue = new LinkedList<>();

            if (index + 1 == listToSort.size()) {
                try {
                    Queue<Integer> queue = listToSort.get(index);

                    sortedQueue = queue;
                } catch (Exception e) {}
            } else {
                Queue<Integer> left = listToSort.get(index);
                Queue<Integer> right = listToSort.get(index + 1);
                while (left.size() > 0 && right.size() > 0) {
                    try {
                        Integer leftNumber = (Integer) left.peek();
                        Integer rightNumber = (Integer) right.peek();
                        if (leftNumber <= rightNumber) {
                            left.poll();
                            sortedQueue.offer(leftNumber);
                        } else {
                            right.poll();
                            sortedQueue.offer(rightNumber);
                        }
                    } catch (Exception e) {};
                }
                if (left.size() == 0 && right.size() != 0) {
                    while (right.size() > 0) {
                        try {
                            Integer number = right.poll();
                            sortedQueue.offer(number);
                        } catch (Exception e) {};
                    }
                } else if (left.size() != 0 && right.size() == 0) {
                    while (left.size() > 0) {
                        try {
                            Integer number = left.poll();
                            sortedQueue.offer(number);
                        } catch (Exception e) {}
                    }
                }
            }

            sorted.add(i, sortedQueue);

        }

        if (sorted.size() > 1) {
            return sort(sorted);
        } else {
            return sorted;
        }
    }
}

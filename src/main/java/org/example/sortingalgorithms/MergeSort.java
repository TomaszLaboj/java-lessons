package org.example.sortingalgorithms;

import org.example.MyQueue;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int[] list = {1,6,34,9,0,2,8};
        List<MyQueue<Integer>> newList = sort(split(list));
        int[] sortedList = converse(newList);
        System.out.println(sortedList);
    }

    static List<MyQueue<Integer>> split(int[] list) {
        List<MyQueue<Integer>> listToSort = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            listToSort.add(new MyQueue<Integer>());
            MyQueue data = listToSort.get(i);
            data.enqueue(list[i]);
            listToSort.add(data);
        }
        return listToSort;
    }

    static int[] converse(List<MyQueue<Integer>> list) {
        int[] returnList = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            returnList[i] = (Integer) list.get(i).peek();
        }
        return returnList;
    }

    static List<MyQueue<Integer>> sort(List<MyQueue<Integer>> listToSort) {

        List<MyQueue<Integer>> sorted = new ArrayList<>();

        int numberOfPairs;
        if (listToSort.size() % 2 == 0) {
            numberOfPairs = Math.floorDiv(listToSort.size(), 2);
        } else {
            numberOfPairs = Math.floorDiv(listToSort.size(), 2) + 1;
        }

        for (int i = 0, index = 0; i < numberOfPairs; i++, index = index + 2) {
            MyQueue<Integer> sortedQueue = new MyQueue<>();

            if (index + 1 == listToSort.size()) {
                sorted.add(index, listToSort.get(index));
            } else {
                MyQueue<Integer> left = listToSort.get(index);
                MyQueue<Integer> right = listToSort.get(index + 1);
                while (left.size() > 0 && right.size() > 0) {
                    try {
                        Integer leftNumber = left.dequeue();
                        Integer rightNumber = right.dequeue();
                        if (leftNumber < rightNumber) {
                            sortedQueue.enqueue(leftNumber);
                            sortedQueue.enqueue(rightNumber);
                        } else {
                            sortedQueue.enqueue(rightNumber);
                            sortedQueue.enqueue(leftNumber);
                        }
                    } catch (Exception e) {};
                }
                if (left.size() == 0) {
                    while (right.size() > 0) {
                        try {
                            Integer number = right.dequeue();
                            sortedQueue.enqueue(number);
                        } catch (Exception e) {};
                    }
                } else if (right.size() == 0) {
                    while (left.size() > 0) {
                        try {
                            Integer number = left.dequeue();
                            sortedQueue.enqueue(number);
                        } catch (Exception e) {}
                    }
                }
            }
            sorted.add(index, sortedQueue);
        }
        if (sorted.size() > 1) {
            return sort(sorted);
        } else {
            return sorted;
        }
    }
}

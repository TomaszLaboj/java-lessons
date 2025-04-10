package org.example.sortingalgorithms;

import org.example.MyQueue;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int[] list = {1,6,34,9,0,2,8};
        List<MyQueue<Integer>> splittedList = split(list);
        List<MyQueue<Integer>> sorted = sort(splittedList);
        int[] sortedList = convert(sorted);
        for (int i : sortedList) {
            System.out.println(i);
        }
    }

    static List<MyQueue<Integer>> split(int[] list) {
        List<MyQueue<Integer>> listToSort = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            MyQueue<Integer> data = new MyQueue<>();
            data.enqueue(list[i]);
            listToSort.add(data);
        }
        return listToSort;
    }

    static int[] convert(List<MyQueue<Integer>> list) {
        int size = list.get(0).size();
        MyQueue<Integer> queue = list.remove(0);
        int[] returnList = new int[size];
        for (int i = 0; i < size; i++) {
            try {
                returnList[i] = queue.dequeue();
            } catch (Exception e) {};
        }
        return returnList;
    }

    static List<MyQueue<Integer>> sort(List<MyQueue<Integer>> listToSort) {

        List<MyQueue<Integer>> sorted = new ArrayList<>();
        for (MyQueue<Integer> q : listToSort) {
            System.out.println("after sort" + q.toString());
        }
        int numberOfPairs;
        if (listToSort.size() % 2 == 0) {
            numberOfPairs = Math.floorDiv(listToSort.size(), 2);
        } else {
            numberOfPairs = Math.floorDiv(listToSort.size(), 2) + 1;
        }

        for (int i = 0, index = 0; i < numberOfPairs; i++, index = index + 2) {
            MyQueue<Integer> sortedQueue = new MyQueue<>();

            if (index + 1 == listToSort.size()) {
                try {
                    sortedQueue.enqueue(listToSort.get(index).dequeue());
                } catch (Exception e) {}
            } else {
                MyQueue<Integer> left = listToSort.get(index);
                MyQueue<Integer> right = listToSort.get(index + 1);
                while (left.size() > 0 && right.size() > 0) {
                    try {
                        Integer leftNumber = (Integer) left.peek();
                        Integer rightNumber = (Integer) right.peek();
                        if (leftNumber < rightNumber) {
                            left.dequeue();
                            sortedQueue.enqueue(leftNumber);
                        } else {
                            right.dequeue();
                            sortedQueue.enqueue(rightNumber);
                        }
                    } catch (Exception e) {};
                }
                if (left.size() == 0 && right.size() != 0) {
                    while (right.size() > 0) {
                        try {
                            Integer number = right.dequeue();
                            sortedQueue.enqueue(number);
                        } catch (Exception e) {};
                    }
                } else if (left.size() != 0 && right.size() == 0) {
                    while (left.size() > 0) {
                        try {
                            Integer number = left.dequeue();
                            sortedQueue.enqueue(number);
                        } catch (Exception e) {}
                    }
                }
            }

            sorted.add(i, sortedQueue);
            for(MyQueue<Integer> q : sorted) {
                System.out.println(index + q.toString());
            }
        }

        if (sorted.size() > 1) {
            return sort(sorted);
        } else {
            return sorted;
        }
    }
}

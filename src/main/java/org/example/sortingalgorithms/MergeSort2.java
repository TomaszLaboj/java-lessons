package org.example.sortingalgorithms;

import org.example.MyQueue;

import java.util.ArrayList;
import java.util.List;

public class MergeSort2 {
    public static void main(String[] args) {
        int[] list = {1,6,34,9,0};
        int[] newList = sort(list);
        for(int num : newList) {
            System.out.println(num);
        }
    }

    static int[] sort(int[] list) {
        int[] returnList = list.clone();
        List<MyQueue<Integer>> merged = new ArrayList<>();

       int numberOfPairs = Math.floorDiv(returnList.length, 2);

       for (int i = 0; i <= numberOfPairs; i = i + 2) {
           if (returnList[i] > returnList[i+1]) {
               int store = returnList[i];
               returnList[i] = returnList[i+1];
               returnList[i+1] = store;
           }
       }

       return returnList;
    }
    // unfinished
}

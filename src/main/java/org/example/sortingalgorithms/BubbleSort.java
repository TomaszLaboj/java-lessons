package org.example.sortingalgorithms;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    public static void main(String[] args) {
        Integer[] list = {5,2,35,4,2,55,1,11234,0,-12,-223};
        System.out.println(sort(List.of(list)));
    }

   static List<Integer> sort(List<Integer> list) {
        List<Integer> returnList = new ArrayList<>(list);

        int passesLeft = returnList.size() - 1;

        for (int i = 0; i < passesLeft; i++) {
            for (int j = 0; j < passesLeft; j++) {
                if (returnList.get(j) > returnList.get(j + 1)) {
                    int store = returnList.get(j);
                    returnList.set(j, returnList.get(j + 1));
                    returnList.set(j + 1, store);
                }
            }
        }

        return returnList;
   }
}

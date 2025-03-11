package org.example;

public class MyArrayList implements MyList {
    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Integer[] data;

    public MyArrayList() {
        data = new Integer[INITIAL_CAPACITY];
    }

    public MyArrayList(int initialSize) { data = new Integer[initialSize]; };


    @Override
    public void add(int element) {
        data[size] = element;
        size++;
    }

    @Override
    public void remove(int index) {
        for(int i = index; i < size; i++) {
            data[i] = data[i+1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean contains(int element) {
        for(int i = 0; i < size; i++) {
            if(element == data[i]) {
                return true;
            }
        }
        return false;
    }


}


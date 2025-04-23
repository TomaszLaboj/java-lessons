package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyQueue<T> implements MyQueueInterface {
    int CAPACITY = 10;
    int size = 0;
    T[] data;
    public MyQueue(){
        this.data = (T[]) new Object[CAPACITY];
    }

    @Override
    public void enqueue(Object element) {
        if (size == CAPACITY) {
            CAPACITY = CAPACITY * 2;

            T[] dataTransfer = data.clone();
            data = (T[]) new Object[CAPACITY];
            for (int i = 0; i < size; i++) {
                data[i] = dataTransfer[i];
            }
        }
        data[size] = (T) element;
        size++;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "size=" + size +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public T dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("queue is empty");
        }
        T element = data[0];
        T[] dataTransfer = data.clone();
        data = (T[]) new Object[CAPACITY];
        for (int i = 0; i < size; i++) {
            data[i] = dataTransfer[i + 1];
        }
        size--;
        return element;
    };

    @Override
    public Object peek() {
        return data[0];
    }

    @Override
    public int size() {
        return size;
    }
}

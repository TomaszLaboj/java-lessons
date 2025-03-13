package org.example;

public class StackImplementation implements MyStack {
    int CAPACITY = 10;
    int[] data;
    int size = 0;
    public StackImplementation() {
        data = new int[CAPACITY];
    }
    public void push(int element) {
        if (size == CAPACITY) {
            int[] dataTransfer = new int[CAPACITY];

            for (int i = 0; i < data.length; i++) {
                dataTransfer[i] = data[i];
            }

            CAPACITY = CAPACITY * 2;

            data = new int[CAPACITY];

            for (int i = 0; i < dataTransfer.length; i++) {
                data[i] = dataTransfer[i];
            }

            data[size] = element;
        } else {
            data[size] = element;
            size++;
        }
    }

    public int top() throws Exception {
        if (size == 0) {
            throw new Exception("Stack is empty");
        }
        return data[size - 1];
    }

    public int pop() throws Exception{
        if (size == 0) {
            throw new Exception("Stack is empty");
        }
        int value = data[size - 1];
        size--;
        return value;
    }

    public int size() {
        return size;
    }
}

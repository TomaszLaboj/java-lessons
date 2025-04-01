package org.example;

public class StackImplementation<T> implements MyStack<T> {
    private int CAPACITY = 10;
    private T[] data;
    private int size = 0;
    public StackImplementation() {
        T[] data = (T[]) new Object[CAPACITY];

    }
    public void push(T element) {
        if (size == CAPACITY) {
            T[] dataTransfer = (T[]) new Object[CAPACITY];

            for (int i = 0; i < data.length; i++) {
                dataTransfer[i] = data[i];
            }

            CAPACITY = CAPACITY * 2;

            data = (T[]) new Object[CAPACITY];

            for (int i = 0; i < dataTransfer.length; i++) {
                data[i] = dataTransfer[i];
            }

            data[size] = element;
        } else {
            data[size] = element;
            size++;
        }
    }

    public T top() throws Exception {
        if (size == 0) {
            throw new Exception("Stack is empty");
        }
        return data[size - 1];
    }

    public T pop() throws Exception{
        if (size == 0) {
            throw new Exception("Stack is empty");
        }
        T value = data[size - 1];
        size--;
        return value;
    }

    public int size() {
        return size;
    }
}

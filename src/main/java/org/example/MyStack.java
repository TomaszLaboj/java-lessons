package org.example;

public class MyStack {
    int[] data = new int[5];
    int pointer = -1;

    public void push(int element) {
        this.data[pointer + 1] = element;
        this.pointer++;
    };

    // element is not really removed from the array, only the pointer is decreased so when another item is pushed it will replace the old one
    public int pop() {
        int element = data[pointer];
        this.pointer--;
        return element;
    };

    public int top() {
        return data[pointer];
    };

    public int size() {
        return pointer + 1;
    };
}
//YT video explaining stac, queue and deque
//https://www.youtube.com/watch?v=A3ZUpyrnCbM

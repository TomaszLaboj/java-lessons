package org.example;

public interface MyStack {
    void push(int element);
    int top() throws Exception;
    int pop() throws Exception;
    int size();
}

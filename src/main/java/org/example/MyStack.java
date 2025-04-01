package org.example;

public interface MyStack<T> {
    void push(T element);
    T top() throws Exception;
    T pop() throws Exception;
    int size();
}

package org.example;

public interface MyQueueInterface<T> {
    void enqueue(T element);
    T dequeue() throws Exception;
    T peek();
    int size();
}

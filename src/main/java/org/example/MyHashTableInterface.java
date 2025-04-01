package org.example;

public interface MyHashTableInterface<T> {
    void insert(String string, T element);
    T search(String string);
    void delete(String string);
}

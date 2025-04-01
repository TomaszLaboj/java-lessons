package org.example;

public interface MyList {

    void add(int element);
    void remove(int index) throws Exception;
    int size();
    boolean contains(int element);
}

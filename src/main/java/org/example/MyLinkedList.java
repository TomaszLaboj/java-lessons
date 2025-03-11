package org.example;

public class MyLinkedList implements MyList {
    private Node head;
    private int size = 0;
    private Node tail;

    @Override
    public void add(int element) {
        Node node = new Node(element);
        if(size == 0) {
            this.head = node;
        }
        this.tail.next = node;
        this.tail = node;
        size++;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(int element) {
        return false;
    }

    private static class Node {
        Integer data;
        Node next;

        Node(Integer data) {
            this.data = data;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }

}
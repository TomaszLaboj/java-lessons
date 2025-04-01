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
        } else {
            this.tail.setNext(node);
        }
        this.tail = node;
        size++;
    }

    @Override
    public void remove(int index) throws Exception {

        if (size == 0) {
            throw new Exception("list is empty");
        } else if(index >= size) {
            throw new Exception("out of bounds");
        }
        Node previous;
        Node current = head;

        int i = 0;
        while (i < index) {
            i++;
            previous = current;
            current = current.next;
            if (current == tail) {
                previous.next = null;
                tail = previous;
            } else if (i == index) {
                previous.next = current.next;
            }

        }

        if(current == head) {
            head = head.next;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(int element) {
        Node current = head;
        Node next = current.next;
        for (int i = 0; i <= size; i++) {
            if(current.data == element) {
                return true;
            }
            current = next;
            if (next.next != null) {

            next = next.next;
            }
        }
        return false;
    }

    public void showValues() {
        Node current = head;
        System.out.println("head: " + head.data + " tail: " + tail.data);
        for (int i = 0; i < size; i++) {

            System.out.println("data: " + current.data);
            if (current.next != null) {
                current = current.next;
            }
        }
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


//if (index > size - 1) {
//        throw new Exception("Index out of bounds");
//        }
//Node current = head;
//Node next = current.next;
//
//        for (int i=1; i < index; i++) {
//current = next;
//            if (next.next != null) {
//next = next.next;
//current.next = current.next.next;
//            }
//                    }
//current.next = next.next;
//size--;
package com.tkachenko;

public class LinkedList implements Queue {
    private int size;
    private final Node head = new Node(null);
    private Node tail;

    public LinkedList() {
        tail = head;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(String element) {
        Node node = new Node(element);

        tail.next = node;

        tail = node;

        size++;
    }

    @Override
    public String dequeue() {
        String result = peek();

        if (head == tail) {
            return null;
        }

        if (head.next == tail) {
            tail = head;
        }

        head.next = head.next.next;
        size--;
        return result;
    }

    @Override
    public String peek() {
        return head.next == null ? head.value : head.next.value;
    }

    private static class Node {
        private Node next;
        private final String value;

        public Node(String value) {
            this.next = null;
            this.value = value;
        }
    }
}

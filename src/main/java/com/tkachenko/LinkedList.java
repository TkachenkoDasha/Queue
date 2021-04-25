package com.tkachenko;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Queue {
    private int size = 0;
    private final Node head = new Node(null);
    private Node tail;

    public LinkedList() {
        tail = head;
        head.next = head;
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
        Node node = new Node(element, head);
        tail.next = node;
        tail = node;
        size++;
    }

    @Override
    public String dequeue() {
        String result = peek();

        if (head == tail) {
            throw new NoSuchElementException("peek from an empty list");
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
        if (isEmpty()) {
            throw new NoSuchElementException("Peek from an empty list");
        }
        return head.next.value;
    }

    @Override
    public void clear() {
        for (Node e = head; e != null; ) {
            Node next = e.next;
            e.value = null;
            e.next = null;
            e = next;
        }
        head.next = head;
        tail = head;
        size = 0;
    }

    @Override
    public Iterator<String> iterator() {
         return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<String> {
        Node cursor = head;
        Node prevCursor = null;

        @Override
        public boolean hasNext() {
            return cursor.next != null && cursor.next != head;
        }

        @Override
        public String next() {
            if (hasNext()) {
                String nextValue = cursor.next.value;
                prevCursor = cursor;
                cursor = cursor.next;
                return nextValue;
            }
            throw new NoSuchElementException("There is no next element");
        }

        @Override
        public void remove() {
            if (cursor == head) {
                throw new IllegalStateException("Method next() hasn't been called");
            }
            if (prevCursor == cursor) {
                throw new IllegalStateException("Method remove() has been already called");
            }

            prevCursor.next = cursor.next;
            cursor = prevCursor;
            size--;
        }
    }

    private static class Node {
        Node next;
        String value;

        Node(String value) {
            this.value = value;
        }

        Node(String value, Node next) {
            this(value);
            this.next = next;
        }
    }
}

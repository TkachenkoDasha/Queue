package com.tkachenko;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Queue<E> {
    private int size = 0;
    private final Node<E> head = new Node<>(null);
    private Node<E> tail;
    private int modCount = 0;

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
    public void enqueue(E element) {
        Node<E> node = new Node<>(element, head);
        tail.next = node;
        tail = node;
        size++;
        modCount++;
    }

    @Override
    public E dequeue() {
        E result = peek();

        if (head == tail) {
            throw new NoSuchElementException("Dequeue from an empty queue");
        }

        if (head.next == tail) {
            tail = head;
        }

        head.next = head.next.next;
        size--;
        modCount++;

        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Peek from an empty queue");
        }
        return head.next.value;
    }

    @Override
    public void clear() {
        for (Node<E> e = head; e != null; ) {
            Node<E> next = e.next;
            e.value = null;
            e.next = null;
            e = next;
        }
        head.next = head;
        tail = head;
        size = 0;
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<E> {
        Node<E> cursor = head;
        Node<E> prevCursor = null;
        private int expectedModCount;

        public IteratorImpl() {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();

            return cursor.next != null && cursor.next != head;
        }

        @Override
        public E next() {
            checkForComodification();

            if (hasNext()) {
                E nextValue = cursor.next.value;
                prevCursor = cursor;
                cursor = cursor.next;
                return nextValue;
            }
            throw new NoSuchElementException("There is no next element");
        }

        @Override
        public void remove() {
            checkForComodification();

            if (cursor == head) {
                throw new IllegalStateException("Method next() hasn't been called");
            }
            if (prevCursor == cursor) {
                throw new IllegalStateException("Method remove() has been already called");
            }

            prevCursor.next = cursor.next;
            cursor.value = null;
            cursor.next = null;
            cursor = prevCursor;
            size--;

            expectedModCount++;
            modCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private static class Node<E1> {
        Node<E1> next;
        E1 value;

        Node(E1 value) {
            this.value = value;
        }

        Node(E1 value, Node<E1> next) {
            this(value);
            this.next = next;
        }
    }
}

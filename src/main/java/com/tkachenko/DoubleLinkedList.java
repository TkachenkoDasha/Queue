package com.tkachenko;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements Stack<E>, Queue<E> {
    private int size;
    private final Node<E> head = new Node<>();
    private int modCount = 0;

    private static class Node<E1> {
        E1 value;
        Node<E1> next = this;
        Node<E1> prev = this;

        public Node() {
        }

        public Node(E1 value) {
            this.value = value;
        }
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
    public void clear() {
        for (Node<E> e = head; e != null; ) {
            Node<E> next = e.next;
            e.value = null;
            e.next = null;
            e = next;
        }

        head.next = head.prev = head;
        size = 0;
        modCount++;
    }

    @Override
    public void push(E element) {
        Node<E> node = new Node<>(element);
        Node<E> tail = head.prev;
        tail.next = node;
        head.prev = node;
        node.next = head;
        node.prev = tail;
        size++;
        modCount++;
    }

    @Override
    public E pop() {
        E result = peekLast();
        Node<E> newTail = head.prev.prev;
        newTail.next = head;
        head.prev = newTail;
        size--;
        modCount++;
        return result;
    }

    @Override
    public E peekLast() {
        checkEmpty();
        return head.prev.value;
    }

    @Override
    public void add(E element) {
        push(element);
    }

    @Override
    public void enqueue(E element) {
        push(element);
    }

    @Override
    public E dequeue() {
        E result = peek();
        head.next = head.next.next;
        size--;
        modCount++;
        return result;
    }

    @Override
    public E peek() {
        checkEmpty();
        return head.next.value;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator<E> {
        private Node<E> lastReturned = head;
        private Node<E> next = lastReturned.next;
        private int nextIndex = 0;
        private boolean isNextCalled = false;
        private boolean isPreviousCalled = false;
        private int expectedModCount;

        public MyListIterator() {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (hasNext()) {
                E nextValue = lastReturned.next.value;
                next = next.next;
                lastReturned = lastReturned.next;
                nextIndex++;
                isNextCalled = true;
                isPreviousCalled = false;
                return nextValue;
            }
            throw new NoSuchElementException("There is no next element");
        }

        @Override
        public boolean hasPrevious() {
            checkForComodification();
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            if (hasPrevious()) {
                E prevValue = lastReturned.value;
                next = lastReturned;
                lastReturned = lastReturned.prev;
                nextIndex--;
                isPreviousCalled = true;
                isNextCalled = false;
                return prevValue;
            }
            throw new NoSuchElementException("There is no previous element");
        }


        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (!isNextCalled && !isPreviousCalled) {
                throw new IllegalStateException("Method next() or previous() hasn't been called");
            }

            if (isNextCalled && isPreviousCalled) {
                throw new AssertionError();
            }

            if (isNextCalled) {
                if (next.next == head) {
                    head.prev = next.prev;
                }
                lastReturned.prev.next = next;
                next.prev = lastReturned.prev;
                lastReturned = lastReturned.prev;
                nextIndex--;
            }
            if (isPreviousCalled) {
                lastReturned.next = next.next;
                next.next.prev = lastReturned;
                next = next.next;
            }
            size--;
            isNextCalled = false;
            isPreviousCalled = false;

            modCount++;
            expectedModCount++;
        }

        @Override
        public void set(E e) {
            checkForComodification();
            if (!isNextCalled && !isPreviousCalled) {
                throw new IllegalStateException("Method next() or previous() hasn't been called");
            }

            if (isNextCalled && isPreviousCalled) {
                throw new AssertionError();
            }

            if (isNextCalled) {
                lastReturned.value = e;
            } else {
                next.value = e;
            }
        }

        @Override
        public void add(E e) {
            checkForComodification();
            Node<E> newNode = new Node<>(e);
            lastReturned.next = newNode;
            next.prev = newNode;
            newNode.next = next;
            newNode.prev = lastReturned;
            lastReturned = newNode;
            nextIndex++;
            size++;
            isNextCalled = false;
            isPreviousCalled = false;

            expectedModCount++;
            modCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
    }
}

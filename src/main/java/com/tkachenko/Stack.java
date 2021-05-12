package com.tkachenko;

import java.util.ListIterator;

public interface Stack<E> extends Collection<E> {
    // LIFO
    void push(E element);
    E pop();
    E peekLast();
    ListIterator<E> listIterator();

    default void add(E element) {
        push(element);
    }
}

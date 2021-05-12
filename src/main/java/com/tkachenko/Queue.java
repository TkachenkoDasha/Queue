package com.tkachenko;

public interface Queue<E> extends Collection<E> {
    // FIFO
    void enqueue(E element);
    E dequeue();
    E peek();

    default void add(E element) {
        enqueue(element);
    }
}
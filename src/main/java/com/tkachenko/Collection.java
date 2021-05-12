package com.tkachenko;

import java.util.Iterator;

public interface Collection<E> extends Iterable<E> {
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
    void clear();
    void add(E element);
}

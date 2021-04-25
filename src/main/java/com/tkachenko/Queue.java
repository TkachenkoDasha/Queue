package com.tkachenko;

public interface Queue extends Iterable<String> {
    int size();
    boolean isEmpty();
    void enqueue(String element);
    String dequeue();
    String peek();
    void clear();
}
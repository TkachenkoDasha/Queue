package com.tkachenko;

public interface Queue {
    int size();
    boolean isEmpty();
    void enqueue(String element);
    String dequeue();
    String peek();
}
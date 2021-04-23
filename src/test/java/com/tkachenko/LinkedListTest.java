package com.tkachenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test that newly created queue has size = 0")
    void size() {
        Queue queue = new LinkedList();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Test after enqueue one object queue has size = 1")
    void size1() {
        Queue queue = new LinkedList();
        queue.enqueue("");
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("After enqueue/dequeue the queue has size = 0")
    void size2() {
        Queue queue = new LinkedList();
        queue.enqueue("");
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("isEmpty for empty queue")
    void isEmpty() {
        Queue queue = new LinkedList();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("isEmpty after enqueue returns false")
    void isEmpty1() {
        Queue queue = new LinkedList();
        queue.enqueue("");
        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Dequeue returns the object that was enqueued")
    void enqueue() {
        Queue queue = new LinkedList();
        String element = "#1";
        queue.enqueue(element);
        String result = queue.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Dequeue two elements from the queue of two elements")
    void enqueue1() {
        Queue queue = new LinkedList();
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        String result1 = queue.dequeue();
        String result2 = queue.dequeue();
        assertEquals(element, result1);
        assertEquals(element1, result2);
    }

    @Test
    @DisplayName("After dequeue one element returns value of this element")
    void dequeue() {
        Queue queue = new LinkedList();
        String element = "#1";
        queue.enqueue(element);
        String result = queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue one element from the queue of two elements size == 1")
    void dequeue1() {
        Queue queue = new LinkedList();
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        String result = queue.dequeue();
        assertEquals(1, queue.size());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue on empty queue returns null")
    void dequeue2() {
        Queue queue = new LinkedList();
        String result = queue.dequeue();
        assertTrue(queue.isEmpty());
        assertNull(result);
    }

    @Test
    @DisplayName("After enqueue one element peek returns this element ")
    void peek() {
        Queue queue = new LinkedList();
        String element = "#1";
        queue.enqueue(element);
        assertEquals(element, queue.peek());
    }

    @Test
    @DisplayName("After enqueue two elements peek returns first element")
    void peek1() {
        Queue queue = new LinkedList();
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        assertEquals(element, queue.peek());
    }

    @Test
    @DisplayName("After peek from empty queue returns null")
    void peek2() {
        Queue queue = new LinkedList();
        assertNull(queue.peek());
    }
}
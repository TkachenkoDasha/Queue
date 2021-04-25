package com.tkachenko;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    Queue queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedList();
    }

    @AfterEach
    void tearDown() {
        queue = null;
    }

    @Test
    @DisplayName("Test that newly created queue has size = 0")
    void size() {
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Test after enqueue one object queue has size = 1")
    void size1() {
        queue.enqueue("");
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("After enqueue/dequeue the queue has size = 0")
    void size2() {
        queue.enqueue("");
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("isEmpty for empty queue")
    void isEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("isEmpty after enqueue returns false")
    void isEmpty1() {
        queue.enqueue("");
        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Dequeue returns the object that was enqueued")
    void enqueue() {
        String element = "#1";
        queue.enqueue(element);
        String result = queue.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Dequeue two elements from the queue of two elements")
    void enqueue1() {
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
        String element = "#1";
        queue.enqueue(element);
        String result = queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue one element from the queue of two elements size == 1")
    void dequeue1() {
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        String result = queue.dequeue();
        assertEquals(1, queue.size());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue on empty queue throws NoSuchElementException")
    void dequeue2() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    @DisplayName("After enqueue one element peek returns this element ")
    void peek() {
        String element = "#1";
        queue.enqueue(element);
        assertEquals(element, queue.peek());
    }

    @Test
    @DisplayName("After enqueue two elements peek returns first element")
    void peek1() {
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        assertEquals(element, queue.peek());
    }

    @Test
    @DisplayName("After peek from empty queue throws NoSuchElementException")
    void peek2() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    void clear() {
        queue.enqueue("#1");
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testForeach() {
        int i = 0;
        for (var el : queue) {
            i++;
        }
        assertEquals(0, i);
    }

    @Test
    void testForeach1() {
        int i = 0;
        queue.enqueue("#1");
        queue.enqueue("#2");
        for (var el : queue) {
            i++;
        }
        assertEquals(2, i);
    }

    @Test
    void iteratorRemove() {
        queue.enqueue("#1");
        queue.enqueue("#2");
        queue.enqueue("#3");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    void iterator() {
        assertNotNull(queue.iterator());
    }
}
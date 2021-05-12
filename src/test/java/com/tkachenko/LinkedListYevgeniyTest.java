package com.tkachenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListYevgeniyTest {
    Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
        queue = null;
    }

    // Evgeny Levchenko's tests
    @Test
    @DisplayName("After enqueue three elements counter in while for iterator is equals 3. YL")
    void testForWhileYL() {
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        int capacity = 0;
        Iterator<String> iterator = queue.iterator();
        while(iterator.hasNext()) {
            iterator.next();
            capacity++;
        }
        assertEquals(capacity, 3);
    }

    @Test
    @DisplayName("Before enqueue, after enqueue and dequeue size is 0, 1, 0 respectively. YL")
    void dequeueYL() {
        assertEquals(0, queue.size());
        queue.enqueue("1");
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Peek with capacity 1 returns value of enqueued element and has size == 1. YL")
    void peekYL() {
        queue.enqueue("a");
        assertEquals("a", queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Method peek() throws NoSuchElementException if queue is empty. YL")
    void peek1YL() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    @DisplayName("Method next() throws NoSuchElementException if queue is empty. YL")
    void next2YL() {
        Iterator<String> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }


    @Test
    @Disabled
    @DisplayName("Method hasNext() return true after next() if queue has two elements. YL")
    void hasNextYL() {
        queue.enqueue("nothing");
        queue.enqueue("anything");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Method hasNext() returns true if queue has one element. YL")
    void iterator1YL() {
        queue.enqueue("1");
        Iterator<String> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
    }


    @Test
    @DisplayName("Method hasNext() returns true if queue has one integer element. YL")
    void iterator2YL() {
        Queue<Integer> queue = new LinkedList<>();
        queue.enqueue(1);
        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Method hasNext() returns true if queue has one string element. YL")
    void hasNext1YL() {
        queue.enqueue("nothing");
        Iterator<String> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Test hasNext() and peek() using integers. YL")
    void hasNext2YL() {
        Queue<Integer> queue = new LinkedList<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, queue.peek());
        iterator.next();
        iterator.remove();
        assertEquals(2, queue.peek());
    }

    @Test
    @DisplayName("Test peek() returns first element using Character")
    void hasNext3YL() {
        Queue<Character> queue = new LinkedList<>();
        queue.enqueue('@');
        queue.enqueue('$');
        queue.enqueue('&');
        assertEquals('@', queue.peek());
    }

    @Test
    @DisplayName("Method hasNext() returns false if queue is empty. YL")
    void next1YL() {
        Iterator<String> iterator = queue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Method peek() doesn't delete the element. YL")
    public void testRemove2() {
        queue.enqueue("nothing");
        queue.enqueue("anything");
        queue.enqueue("elephant");
        assertEquals("nothing", queue.peek());
        assertEquals(3, queue.size());
    }

    @Test
    @Disabled
    @DisplayName("YL")
    void remove() {
        Iterator<String> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            queue.enqueue("nothing");
            queue.enqueue("anything");
            iterator.remove();
            iterator.remove();
            assertNull(null, (String) queue.peek());
        });
    }

    @Test
    @Disabled
    @DisplayName("YL")
    void remove1() {
        Iterator<String> itr = queue.iterator();
        queue.enqueue("sth");

        queue.enqueue("anything");
        queue.enqueue("b");
        itr.remove();
        itr.remove();

        assertEquals("b", queue.peek());
    }
}

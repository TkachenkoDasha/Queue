package com.tkachenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleLinkedListIlyaTest {

    DoubleLinkedList<String> stack;

    @BeforeEach
    void setUp() {
        stack = new DoubleLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    // Ilya Krasnenkov's tests
    @Test
    @DisplayName("peekLast returns last added element. IlK")
    void peekLastYlK() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        assertEquals("#3", stack.peekLast());
    }

    @Test
    @DisplayName("Push adds element correctly. IlK")
    void pushYlK() {
        stack.push("#1");
        stack.push("#2");
        assertEquals("#1", stack.peek());
    }

    @Test
    @DisplayName("hasNext returns true if there are some elements. IlK")
    void hasNextYlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        assertTrue(listIterator.hasNext());
    }

    @Test
    @DisplayName("Has next returns false with empty list. IlK")
    void hasNext1YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        ListIterator<Object> listIterator = stack.listIterator();
        assertFalse(listIterator.hasNext());
    }

    @Test
    @DisplayName("HasPrevious works correctly. IlK")
    void hasPreviousYlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        assertTrue(listIterator.hasPrevious());
    }

    @Test
    @DisplayName("Check if previous() returns proper object. IlK")
    void previousYlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        assertTrue(listIterator.hasPrevious());
        assertEquals("#1", listIterator.previous());
    }

    @Test
    @DisplayName("Check previous x2. IlK")
    void previous1YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        assertTrue(listIterator.hasPrevious());
        assertEquals("#2", listIterator.previous());
        assertTrue(listIterator.hasPrevious());
        assertEquals("#1", listIterator.previous());
    }

    @Test
    @DisplayName("Next after previous. IlK")
    void previous2YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        assertTrue(listIterator.hasPrevious());
        assertEquals("#2", listIterator.previous());
        assertTrue(listIterator.hasNext());
        assertEquals("#2", listIterator.next());
    }

    @Test
    @DisplayName("Next after previous x2. IlK")
    void previous3YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        assertEquals("#1", listIterator.next());
        assertEquals("#2", listIterator.next());
        assertEquals("#2", listIterator.previous());
        assertEquals("#1", listIterator.previous());
        assertEquals("#1", listIterator.next());
    }

    @Test
    @DisplayName("Dequeue returns the object that was enqueued. IlK")
    void enqueueYlK() {
        Queue<Object> stack = new DoubleLinkedList<>();
        String element = "#1";
        stack.enqueue(element);
        Object result = stack.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Dequeue returns the object that was enqueued. IlK")
    void enqueue1YlK() {
        Queue<Object> stack = new DoubleLinkedList<>();
        String element = "#1";
        stack.enqueue(element);
        Object result = stack.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Remove works properly. IlK")
    void remove122YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.remove();
        assertEquals("#2", listIterator.next());

    }

    @Test
    @DisplayName("Check set(String obj) to list. IlK")
    void setYlK() {
        DoubleLinkedList<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.set("newString");
        assertEquals("newString", stack.peek());
    }

    @Test
    @DisplayName("set(String obj) in the middle of list of strings. IlK")
    void set2YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.set("new#2");
        listIterator = stack.listIterator();
        listIterator.next();
        assertEquals("new#2", listIterator.next());
    }

    @Test
    @DisplayName("set(String obj) after previous(). IlK")
    void set3YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set("new#2");
        listIterator = stack.listIterator();
        listIterator.next();
        assertEquals("new#2", listIterator.next());
    }

    @Test
    @DisplayName("add() last item to list. IlK")
    void addYlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.add("#2");
        assertEquals("#2", listIterator.previous());
    }

    @Test
    @DisplayName("add() in the middle of the list. IlK")
    void add1YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.add("#4");
        listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        assertEquals("#4", listIterator.next());
    }

    @Test
    @DisplayName("Previous index for the beginning of the list. IlK")
    void previousIndexYlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    @DisplayName("Previous index for the end of the list. IlK")
    void previousIndex1YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        assertEquals(2, listIterator.previousIndex());
    }

    @Test
    @DisplayName("Next index for the beginning of the list. IlK")
    void nextIndexIlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        ListIterator<Object> listIterator = stack.listIterator();
        assertEquals(0, listIterator.nextIndex());
    }

    @Test
    @DisplayName("Next index for the end of the list. IlK")
    void nextIndex1YlK() {
        Stack<Object> stack = new DoubleLinkedList<>();
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<Object> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        assertEquals(3, listIterator.nextIndex());
    }

    @Test
    @DisplayName("Check if ConcurrentModException is thrown for last but one element. IlK")
    void concurrentModification1YlK() {
        Queue<Object> stack = new DoubleLinkedList<>();
        stack.enqueue("#1");
        stack.enqueue("#2");
        stack.enqueue("#3");
        Iterator<Object> iterator = stack.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        stack.dequeue();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}

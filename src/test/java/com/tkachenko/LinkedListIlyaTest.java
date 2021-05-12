package com.tkachenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListIlyaTest {
    Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
        queue = null;
    }

    //Ilya Krasnenkov's tests
    @Test
    @DisplayName("dequeue throws exception for empty queue. IlK")
    void dequeue1IlK() {
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Dequeue removes a head from the queue. IlK")
    void dequeue2IlK() {
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        String result = queue.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Peek returns a head. IlK")
    void peekIlK() {
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        String result = queue.peek();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Peek doesn't remove a head from the queue. IlK")
    void peek1IlK() {
        String element = "#1";
        String element2 = "#2";
        queue.enqueue(element);
        queue.enqueue(element2);
        queue.peek();
        assertEquals(2, queue.size());
    }


    @Test
    @DisplayName("hasNext() for empty queue returns false. IlK")
    void iteratorIlK() {
        Iterator<String> iterator = queue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Iterator removes head. IlK")
    void iterator1IlK() {
        queue.enqueue("elem");
        queue.enqueue("elem1");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.remove();
        assertEquals("elem1", queue.peek());
        iterator.next();
        iterator.remove();
        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, queue::peek);
    }

    @Test
    @DisplayName("Iterator's multiple hasNext doesn't move iterator. IlK")
    void iterator3IlK() {
        queue.enqueue("elem");
        queue.enqueue("elem1");
        Iterator<String> iterator = queue.iterator();
        iterator.hasNext();
        iterator.hasNext();
        assertEquals("elem", iterator.next());
    }

    @Test
    @DisplayName("Next returns proper element. IlK")
    void iterator4IlK() {
        queue.enqueue("elem");
        queue.enqueue("elem1");
        Iterator<String> iterator = queue.iterator();
        assertEquals("elem",iterator.next());
    }
    @Test
    @DisplayName("Iterator's next() returns exception, if there're no elements. IlK")
    void iterator5IlK() {
        Iterator<String> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class,iterator::next);
    }

    @Test
    @DisplayName("Check if iterator goes through each item, in the correct order. IlK")
    void iterator6IlK() {
        String elem1 = "elem1";
        String elem2 = "elem2";
        String[] mas = {elem1, elem2};
        int index = 0;
        queue.enqueue(elem1);
        queue.enqueue(elem2);
        for (String s : queue) {
            assertEquals(mas[index], s);
            index++;
        }
    }
    @Test

    @DisplayName("Check 2 iterators works correctly. IlK")
    void iterator7IlK() {
        Queue<Integer> queue = new LinkedList<>();
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(2);
        Iterator<Integer> iterator = queue.iterator();
        Iterator<Integer> iterator1 = queue.iterator();
        assertEquals(5,iterator.next());
        assertEquals(5,iterator1.next());
    }

    @Test
    @DisplayName("Remove throws exception if next() hasn't been called. IlK")
    void iterator8Ilk(){
        queue.enqueue("elem");
        queue.enqueue("elem2");
        Iterator<String> iterator = queue.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("Remove throws exception if it has already been called after the last call to the next method. IlK")
    void iterator9IlK(){
        queue.enqueue("elem");
        queue.enqueue("elem1");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }
    @Test
    @DisplayName("After clear size == 0. IlK")
    void clearIlK() {
        queue.enqueue("elem");
        queue.enqueue("elem1");
        queue.clear();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("After clear there are no elements. IlK")
    void clear1IlK() {
        queue.enqueue("elem");
        queue.clear();
        assertThrows(NoSuchElementException.class, queue::peek);
    }

    @Test
    @DisplayName("Check if elements add correctly after clear. IlK")
    void clear2IlK() {
        String elem = "elem";
        queue.enqueue(elem);
        queue.enqueue("elem2");
        queue.clear();
        queue.enqueue(elem);
        assertEquals(elem, queue.peek());
    }

    @Test
    @DisplayName("Test if generics work proper with custom classes & iterator. IlK")
    void genericIlK(){
        Queue<Object> queue = new LinkedList<>();
        CustomClass customClass = new CustomClass("someName1", 1);
        CustomClass customClass1 = new CustomClass("someName2", 2);
        CustomClass[] customClasses = {customClass, customClass1};
        int count = 0;
        queue.enqueue(customClass);
        queue.enqueue(customClass1);
        for (Object o : queue) {
            assertEquals(customClasses[count], o);
            count++;
        }
    }

    @Test
    @DisplayName("Test if generics work proper with custom classes. IlK")
    void generic1IlK(){
        Queue<Object> queue = new LinkedList<>();
        CustomClass customClass = new CustomClass("someName1", 1);
        CustomClass customClass1 = new CustomClass("someName2", 2);
        queue.enqueue(customClass);
        queue.enqueue(customClass1);
        assertEquals(customClass, queue.peek());
    }
}

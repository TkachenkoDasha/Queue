package com.tkachenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest<E> {
    Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedList<>();
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
    @DisplayName("Dequeue returns the object that was enqueued. DT")
    void enqueue() {
        String element = "#1";
        queue.enqueue(element);
        String result = queue.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Dequeue two elements from the queue of two elements. DT")
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
    @DisplayName("After dequeue one element returns value of this element. DT")
    void dequeue() {
        String element = "#1";
        queue.enqueue(element);
        String result = queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue one element from the queue of two elements size == 1. DT")
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
    @DisplayName("After dequeue on empty queue throws NoSuchElementException. DT")
    void dequeue2() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    @DisplayName("After enqueue one element peek returns this element. DT")
    void peek() {
        String element = "#1";
        queue.enqueue(element);
        assertEquals(element, queue.peek());
    }

    @Test
    @DisplayName("After enqueue two elements peek returns first element. DT")
    void peek1() {
        String element = "#1";
        String element1 = "#2";
        queue.enqueue(element);
        queue.enqueue(element1);
        assertEquals(element, queue.peek());
    }

    @Test
    @DisplayName("After peek from empty queue throws NoSuchElementException. DT")
    void peek2() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    @DisplayName("After enqueue the element and clear() the queue is empty. DT")
    void clear() {
        queue.enqueue("#1");
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("If queue has one element hasNext() returns true. DT")
    void iteratorHasNext() {
        queue.enqueue("#1");
        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("After enqueue and call hasNext() several times returns true. DT")
    void iteratorHasNext1() {
        queue.enqueue("#1");
        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("After enqueue one element and next(), hasNext() returns false. DT")
    void iteratorHasNext2() {
        queue.enqueue("#1");
        Iterator<String> iterator = queue.iterator();
        iterator.next();

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("If queue is empty hasNext() returns false. DT")
    void iteratorHasNext3() {
        Iterator<String> iterator = queue.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("If queue has one element next() returns this element. DT")
    void iteratorNext() {
        String element = "#1";
        queue.enqueue(element);
        Iterator<String> iterator = queue.iterator();
        String result = iterator.next();

        assertEquals(element, result);
    }

    @Test
    @DisplayName("If queue is empty next() throws NoSuchElementException. DT")
    void iteratorNext1() {
        Iterator<String> iterator = queue.iterator();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("While queue has element after next() and remove() the queue is empty. DT")
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
    @DisplayName("After remove one element from queue the queue is empty. DT")
    void iteratorRemove1() {
        queue.enqueue("#1");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("If next() hasn't been called and remove() called get IllegalStateException. DT")
    void iteratorRemove2() {
        queue.enqueue("#1");
        queue.enqueue("#2");
        queue.enqueue("#3");
        Iterator<String> iterator = queue.iterator();

        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("If next() called and remove() called second time get IllegalStateException. DT")
    void iteratorRemove3() {
        queue.enqueue("#1");
        queue.enqueue("#2");
        queue.enqueue("#3");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("If next() called and remove() called on empty queue get NoSuchElementException and IllegalStateException. DT")
    void iteratorRemove4() {
        Iterator<String> iterator = queue.iterator();

        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("Iterator is not null. DT")
    void iterator() {
        assertNotNull(queue.iterator());
    }

    @Test
    @DisplayName("For empty queue counter in foreach equals 0. DT")
    void testForeach() {
        int i = 0;
        for (var el : queue) {
            i++;
        }
        assertEquals(0, i);
    }

    @Test
    @DisplayName("In queue with two elements counter in foreach equals 2. DT")
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
    @DisplayName("In queue with four elements two iterators working together return last item independently of each other. DT")
    void twoIterator() {
        queue.enqueue("#1");
        queue.enqueue("#2");
        queue.enqueue("#3");
        queue.enqueue("#4");
        Iterator<String> iterator = queue.iterator();
        Iterator<String> iterator1 = queue.iterator();

        String lastElement = null;
        String lastElement1 = null;

        while (iterator.hasNext()) {
            lastElement = iterator.next();
            lastElement1 = iterator1.next();
        }

        assertEquals("#4", lastElement);
        assertEquals("#4", lastElement1);
    }

    @Test
    @DisplayName("After call method add() size == 1 and peek() returns this element. DT")
    void testAdd() {
        queue.add("#1");
        assertEquals("#1", queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("If dequeue calls on penultimate element without iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent() {
        queue.add("#1");
        queue.add("#2");
        queue.add("#3");
        queue.add("#4");
        queue.add("#5");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : queue) {
                if (s.equals("#4")) {
                    queue.dequeue();
                }
            }
        });
    }

    @Test
    @DisplayName("If dequeue calls on last element without iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent1() {
        queue.add("#1");
        queue.add("#2");
        queue.add("#3");
        queue.add("#4");
        queue.add("#5");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : queue) {
                if (s.equals("#5")) {
                    queue.dequeue();
                }
            }
        });
    }

    @Test
    @DisplayName("If remove was called another iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent2() {
        queue.add("#1");
        queue.add("#2");
        queue.add("#3");
        queue.add("#4");
        queue.add("#5");
        Iterator<String> iterator = queue.iterator();
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : queue) {
                if (s.equals("#4")) {
                    iterator.next();
                    iterator.remove();
                }
            }
        });
    }

    @Test
    @DisplayName("If add was called another iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent3() {
        queue.add("#1");
        queue.add("#2");
        queue.add("#3");
        queue.add("#4");
        queue.add("#5");
        Iterator<String> iterator = queue.iterator();
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : queue) {
                if (s.equals("#4")) {
                    iterator.next();
                    iterator.remove();
                }
            }
        });
    }
}
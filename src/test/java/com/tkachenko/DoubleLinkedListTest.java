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

class DoubleLinkedListTest {

    DoubleLinkedList<String> stack;

    @BeforeEach
    void setUp() {
        stack = new DoubleLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    @DisplayName("Test that newly created queue has size = 0")
    void size() {
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("Test after push one object to stack has size = 1")
    void size1() {
        stack.push("#1");
        assertEquals(1, stack.size());
    }

    @Test
    @DisplayName("After push/pop the stack has size = 0")
    void size2() {
        stack.push("");
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("isEmpty for empty stack")
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("isEmpty after push returns false")
    void isEmpty1() {
        stack.push("#1");
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Dequeue returns the object that was enqueued. DT")
    void enqueue() {
        String element = "#1";
        stack.enqueue(element);
        String result = stack.dequeue();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Dequeue two elements from the stack of two elements. DT")
    void enqueue1() {
        String element = "#1";
        String element1 = "#2";
        stack.enqueue(element);
        stack.enqueue(element1);
        String result1 = stack.dequeue();
        String result2 = stack.dequeue();
        assertEquals(element, result1);
        assertEquals(element1, result2);
    }

    @Test
    @DisplayName("After dequeue one element returns value of this element. DT")
    void dequeue() {
        String element = "#1";
        stack.enqueue(element);
        String result = stack.dequeue();
        assertTrue(stack.isEmpty());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue one element from the stack of two elements size == 1. DT")
    void dequeue1() {
        String element = "#1";
        String element1 = "#2";
        stack.enqueue(element);
        stack.enqueue(element1);
        String result = stack.dequeue();
        assertEquals(1, stack.size());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After dequeue on empty stack throws NoSuchElementException. DT")
    void dequeue2() {
        assertThrows(NoSuchElementException.class, () -> stack.dequeue());
    }

    @Test
    @DisplayName("After enqueue one element peek returns this element. DT")
    void peek() {
        String element = "#1";
        stack.enqueue(element);
        assertEquals(element, stack.peek());
    }

    @Test
    @DisplayName("After enqueue two elements peek returns first element. DT")
    void peek1() {
        String element = "#1";
        String element1 = "#2";
        stack.enqueue(element);
        stack.enqueue(element1);
        assertEquals(element, stack.peek());
    }

    @Test
    @DisplayName("After peek from empty stack throws NoSuchElementException. DT")
    void peek2() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    @DisplayName("Pop returns the object that was added. DT")
    void add() {
        String element = "#1";
        stack.add(element);
        String result = stack.pop();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Pop two elements from the stack of two elements. DT")
    void add1() {
        String element = "#1";
        String element1 = "#2";
        stack.add(element);
        stack.add(element1);
        String result = stack.pop();
        String result1 = stack.pop();
        assertEquals(element, result1);
        assertEquals(element1, result);
    }

    @Test
    @DisplayName("Pop returns the object that was pushed. DT")
    void push() {
        String element = "#1";
        stack.push(element);
        String result = stack.pop();
        assertEquals(element, result);
    }

    @Test
    @DisplayName("Pop two elements from the stack of two elements. DT")
    void push1() {
        String element = "#1";
        String element1 = "#2";
        stack.push(element);
        stack.push(element1);
        String result = stack.pop();
        String result1 = stack.pop();
        assertEquals(element1, result);
        assertEquals(element, result1);
    }

    @Test
    @DisplayName("After pop one element returns value of this element. DT")
    void pop() {
        String element = "#1";
        stack.push(element);
        String result = stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(element, result);
    }

    @Test
    @DisplayName("After pop one element from the stack of two elements size == 1. DT")
    void pop1() {
        String element = "#1";
        String element1 = "#2";
        stack.push(element);
        stack.push(element1);
        String result = stack.pop();
        assertEquals(1, stack.size());
        assertEquals(element1, result);
    }

    @Test
    @DisplayName("After pop on empty stack throws NoSuchElementException. DT")
    void pop2() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("After push one element peek returns this element. DT")
    void peekLast() {
        String element = "#1";
        stack.push(element);
        assertEquals(element, stack.peekLast());
    }

    @Test
    @DisplayName("After push two elements peekLast returns last element. DT")
    void peekLast1() {
        String element = "#1";
        String element1 = "#2";
        stack.push(element);
        stack.push(element1);
        assertEquals(element1, stack.peekLast());
    }

    @Test
    @DisplayName("After peek from empty stack throws NoSuchElementException. DT")
    void peekLast2() {
        assertThrows(NoSuchElementException.class, () -> stack.peekLast());
    }

    @Test
    @DisplayName("After push the element and clear() the stack is empty. DT")
    void clear() {
        stack.push("#1");
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("If stack has one element hasNext() returns true. DT")
    void lisIteratorHasNext() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();

        assertTrue(listIterator.hasNext());
    }

    @Test
    @DisplayName("If stack has one element hasPrevious() returns false. DT")
    void listIteratorHasPrevious() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();

        assertFalse(listIterator.hasPrevious());
    }

    @Test
    @DisplayName("After push and call hasNext() several times returns true. DT")
    void listIteratorHasNext1() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();

        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasNext());
    }

    @Test
    @DisplayName("After push and call hasPrevious() several times returns false. DT")
    void listIteratorHasPrevious1() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();

        assertFalse(listIterator.hasPrevious());
        assertFalse(listIterator.hasPrevious());
        assertFalse(listIterator.hasPrevious());
    }

    @Test
    @DisplayName("After push one element and next(), hasNext() returns false. DT")
    void listIteratorHasNext2() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();

        assertFalse(listIterator.hasNext());
    }

    @Test
    @DisplayName("After push two element and next() and previous(), hasPrevious() returns false. DT")
    void listIteratorHasPrevious2() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    @DisplayName("If stack is empty hasNext() returns false. DT")
    void listIteratorHasNext3() {
        ListIterator<String> listIterator = stack.listIterator();

        assertFalse(listIterator.hasNext());
    }

    @Test
    @DisplayName("If stack is empty hasPrevious() returns false. DT")
    void listIteratorHasPrevious3() {
        ListIterator<String> listIterator = stack.listIterator();

        assertFalse(listIterator.hasPrevious());
    }

    @Test
    @DisplayName("After push two element and next()x2, hasPrevious() returns true. DT")
    void listIteratorHasPrevious4() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    @DisplayName("If stack has one element next() returns this element. DT")
    void listIteratorNext() {
        String element = "#1";
        stack.push(element);
        ListIterator<String> listIterator = stack.listIterator();
        String result = listIterator.next();

        assertEquals(element, result);
    }

    @Test
    @DisplayName("If stack is empty next() throws NoSuchElementException. DT")
    void listIteratorNext1() {
        ListIterator<String> listIterator = stack.listIterator();

        assertThrows(NoSuchElementException.class, listIterator::next);
    }

    @Test
    @DisplayName("If stack has two element previous() returns proper element. DT")
    void listIteratorPrevious() {
        String element = "#1";
        String element1 = "#2";
        stack.push(element);
        stack.push(element1);
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        String result = listIterator.previous();

        assertEquals(element1, result);
    }

    @Test
    @DisplayName("If stack is empty previous() throws NoSuchElementException. DT")
    void listIteratorPrevious1() {
        ListIterator<String> listIterator = stack.listIterator();

        assertThrows(NoSuchElementException.class, listIterator::next);
    }

    @Test
    @DisplayName("If stack has one element previous() returns proper element. DT")
    void listIteratorPrevious2() {
        String element = "#1";
        stack.push(element);
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        String result = listIterator.previous();

        assertEquals(element, result);
    }

    @Test
    @DisplayName("If stack has one element previous() returns proper element. DT")
    void listIteratorNextPrevious() {
        String element = "#1";
        stack.push(element);
        ListIterator<String> listIterator = stack.listIterator();
        String next1 = listIterator.next();
        String previous1 = listIterator.previous();
        String next2 = listIterator.next();
        String previous2 = listIterator.previous();
        assertEquals(next1, previous1);
        assertEquals(next2, previous2);
    }

    @Test
    @DisplayName("nextIndex returns the same value if next() hasn't been called. DT")
    void listIteratorNextIndex() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();

        assertEquals(listIterator.nextIndex(), listIterator.nextIndex());
    }

    @Test
    @DisplayName("nextIndex returns size of stack if cursor on the last element. DT")
    void listIteratorNextIndex1() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }

        assertEquals(3, listIterator.nextIndex());
    }

    @Test
    @DisplayName("nextIndex returns the size == 0 if stack is empty. DT")
    void listIteratorNextIndex2() {
        ListIterator<String> listIterator = stack.listIterator();

        assertEquals(stack.size(), listIterator.nextIndex());
    }

    @Test
    @DisplayName("previousIndex returns proper index if cursor not at the beginning. DT")
    void listIteratorPreviousIndex() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }

        assertEquals(2, listIterator.previousIndex());
    }

    @Test
    @DisplayName("previousIndex returns -1 if cursor at the beginning. DT")
    void listIteratorPreviousIndex1() {
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }

        assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    @DisplayName("add() adds element before cursor. DT")
    void listIteratorAdd() {
        stack.push("#1");
        stack.push("#3");
        stack.push("#4");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();

        int prevIndexBeforeAdd = listIterator.previousIndex();
        int nextIndexBeforeAdd = listIterator.nextIndex();

        listIterator.add("#2");

        assertEquals(prevIndexBeforeAdd + 1, listIterator.previousIndex());
        assertEquals(nextIndexBeforeAdd + 1, listIterator.nextIndex());
        assertEquals("#4", listIterator.next());

        listIterator.previous();
        assertEquals("#2", listIterator.previous());
    }

    @Test
    @DisplayName("add() adds element into empty stack. DT")
    void listIteratorAdd1() {
        ListIterator<String> listIterator = stack.listIterator();

        listIterator.add("#1");

        assertEquals(1, stack.size());
        assertEquals("#1", listIterator.previous());
    }

    @Test
    @DisplayName("set() sets element to element that returned by next() and doesn't change size. DT")
    void listIteratorSet() {
        stack.push("#2");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();

        listIterator.set("#1");
        assertEquals("#1", listIterator.previous());
        assertEquals(3, stack.size());
    }

    @Test
    @DisplayName("set() sets to element that returned by previous(). DT")
    void listIteratorSet1() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }
        listIterator.previous();
        listIterator.set("#3");

        assertEquals("#3", listIterator.next());
    }

    @Test
    @DisplayName("set() throws IllegalStateException if stack is empty. DT")
    void listIteratorSet2() {
        ListIterator<String> listIterator = stack.listIterator();

        assertThrows(IllegalStateException.class, () -> listIterator.set("1"));
    }

    @Test
    @DisplayName("set() throws IllegalStateException if after next(), remove() has been called. DT")
    void listIteratorSet3() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.remove();

        assertThrows(IllegalStateException.class, () -> listIterator.set("1"));
    }

    @Test
    @DisplayName("set() throws IllegalStateException if after previous(), remove() has been called. DT")
    void listIteratorSet4() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();

        assertThrows(IllegalStateException.class, () -> listIterator.set("1"));
    }

    @Test
    @DisplayName("set() throws IllegalStateException if after next(), add() has been called. DT")
    void listIteratorSet5() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.add("#3");

        assertThrows(IllegalStateException.class, () -> listIterator.set("1"));
    }

    @Test
    @DisplayName("set() throws IllegalStateException if after previous(), add() has been called. DT")
    void listIteratorSet6() {
        stack.push("#1");
        stack.push("#2");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.add("#3");

        assertThrows(IllegalStateException.class, () -> listIterator.set("1"));
    }

    @Test
    @DisplayName("set() throws IllegalStateException if next() or previous() hasn't been called. DT")
    void listIteratorSet7() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        assertThrows(IllegalStateException.class, () -> listIterator.set("1"));

    }

    @Test
    @DisplayName("After removing all elements the stack is empty. DT")
    void iteratorRemove() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.remove();
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("After removing all elements the stack is empty. DT")
    void iteratorRemove1() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }

        while (listIterator.hasPrevious()) {
            listIterator.previous();
            listIterator.remove();
        }

        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("After remove one element from stack the stack is empty using next(). DT")
    void iteratorRemove2() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.remove();
        }

        assertTrue(stack.isEmpty());
        assertEquals(0, listIterator.nextIndex());
    }

    @Test
    @DisplayName("After remove one element from stack the stack is empty using previous(). DT")
    void iteratorRemove3() {
        stack.push("#1");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("If next() or previous() hasn't been called and remove() is called get IllegalStateException. DT")
    void iteratorRemove4() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();

        assertThrows(IllegalStateException.class, listIterator::remove);
    }

    @Test
    @DisplayName("If next() called and remove() called second time get IllegalStateException. DT")
    void iteratorRemove5() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.remove();

        assertThrows(IllegalStateException.class, listIterator::remove);
    }


    @Test
    @DisplayName("If previous() called and remove() called second time get IllegalStateException. DT")
    void iteratorRemove6() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        ListIterator<String> listIterator = stack.listIterator();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();

        assertThrows(IllegalStateException.class, listIterator::remove);
    }

    @Test
    @DisplayName("ListIterator is not null. DT")
    void listIterator() {
        assertNotNull(stack.listIterator());
    }

    @Test
    @DisplayName("Iterator is not null. DT")
    void iterator() {
        assertNotNull(stack.iterator());
    }

    @Test
    @DisplayName("For empty stack counter in foreach equals 0. DT")
    void testForeach() {
        int i = 0;
        for (var el : stack) {
            i++;
        }
        assertEquals(0, i);
    }

    @Test
    @DisplayName("In stack with two elements counter in foreach equals 2. DT")
    void testForeach1() {
        int i = 0;
        stack.push("#1");
        stack.push("#2");
        for (var el : stack) {
            i++;
        }
        assertEquals(2, i);
    }

    @Test
    @DisplayName("In stack with four elements two listIterators working together return last item independently of each other. DT")
    void twoListIterator() {
        stack.push("#1");
        stack.push("#2");
        stack.push("#3");
        stack.push("#4");
        ListIterator<String> listIterator = stack.listIterator();
        ListIterator<String> listIterator1 = stack.listIterator();

        String lastElement = null;
        String lastElement1 = null;

        while (listIterator.hasNext()) {
            lastElement = listIterator.next();
            lastElement1 = listIterator1.next();
        }

        assertEquals("#4", lastElement);
        assertEquals("#4", lastElement1);
    }

    @Test
    @DisplayName("If dequeue calls on penultimate element without iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent() {
        stack.add("#1");
        stack.add("#2");
        stack.add("#3");
        stack.add("#4");
        stack.add("#5");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : stack) {
                if (s.equals("#4")) {
                    stack.dequeue();
                }
            }
        });
    }

    @Test
    @DisplayName("If dequeue calls on last element without iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent1() {
        stack.add("#1");
        stack.add("#2");
        stack.add("#3");
        stack.add("#4");
        stack.add("#5");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : stack) {
                if (s.equals("#5")) {
                    stack.dequeue();
                }
            }
        });
    }

    @Test
    @DisplayName("If remove was called another iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent2() {
        stack.add("#1");
        stack.add("#2");
        stack.add("#3");
        stack.add("#4");
        stack.add("#5");
        ListIterator<String> listIterator = stack.listIterator();
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : stack) {
                if (s.equals("#4")) {
                    listIterator.next();
                    listIterator.remove();
                }
            }
        });
    }

    @Test
    @DisplayName("If add was called another iterator throws ConcurrentModificationException. DT")
    void listIteratorConcurrent3() {
        stack.add("#1");
        stack.add("#2");
        stack.add("#3");
        stack.add("#4");
        stack.add("#5");
        ListIterator<String> listIterator = stack.listIterator();
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : stack) {
                if (s.equals("#4")) {
                    listIterator.add("6");
                }
            }
        });
    }

    // Evgeny Levchenko's tests
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
    @DisplayName("Test peek() returns first element using Character. YL.")
    void hasNext3YL() {
        Queue<Character> queue = new LinkedList<>();
        queue.enqueue('@');
        queue.enqueue('$');
        queue.enqueue('&');
        assertEquals('@', queue.peek());
    }
}
package com.tkachenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListIgorTest {

    DoubleLinkedList<String> queue;

    @BeforeEach
    void setUp() {
        queue = new DoubleLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        queue.clear();
    }

    void fillStack(int count) {
        for (int i = 0; i < count; i++) {
            queue.push("#" + i);
        }
    }

    //********* Queue ***************
    //-------  size() ---------------
    @Test
    @DisplayName("Queue.size(): New => size() == 0. IgK")
    void size() {
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Queue.size(): New, +1 => size() == 1. IgK")
    void size1() {
        fillStack(1);
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Queue.size(): New, +1, -1 => size() == 0. IgK")
    void size2() {
        fillStack(1);
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Queue.size(): New, +5, -1 => size() == 4. IgK")
    void size3() {
        fillStack(5);
        queue.dequeue();
        assertEquals(4, queue.size());
    }

    //-------  size() ---------------
    @Test
    @DisplayName("Stack.size(): New => size() == 0. IgK")
    void size4() {
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Stack.size(): New, +1 => size() == 1. IgK")
    void size5() {
        fillStack(1);
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Stack.size(): New, +1, -1 => size() == 0. IgK")
    void size6() {
        fillStack(1);
        queue.pop();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Stack.size(): New, +5, -1 => size() == 4. IgK")
    void size7() {
        fillStack(5);
        queue.pop();
        assertEquals(4, queue.size());
    }

    //-------  isEmpty() ---------------
    @Test
    @DisplayName("Queue.isEmpty(): New => empty() == true. IgK")
    void isEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Queue.isEmpty(): New, +1 => empty() == false. IgK")
    void isEmpty2() {
        fillStack(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Queue.isEmpty(): New, +2 -2 => empty() == true. IgK")
    void isEmpty3() {
        fillStack(2);
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Stack.isEmpty(): New, +2 -2 => empty() == true. IgK")
    void isEmpty4() {
        fillStack(2);
        queue.pop();
        queue.pop();
        assertTrue(queue.isEmpty());
    }

    //-------  peek() ---------------
    @Test
    @DisplayName("Queue.peek(): New => peek() == NoSuchElementException. IgK")
    void peek() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.peek();
        });
    }

    @Test
    @DisplayName("Queue.peek(): New, +1 => peek() == #0. IgK")
    void peek2() {
        fillStack(1);
        assertEquals("#0", queue.peek());
    }

    @Test
    @Disabled
    @DisplayName("Queue.peek(): New, +3 => peek() == #0, #0. IgK")
    void peek3() {
        fillStack(3);

        assertEquals("#2", queue.peek());
        assertEquals("#2", queue.peek());
    }

    //-------  dequeue() ---------------
    @Test
    @DisplayName("Queue.deque(): New => dequeue() == NoSuchElementException. IgK")
    void dequeue() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    @DisplayName("Queue.deque(): New, +1 => dequeue() == #0. IgK")
    void dequeue2() {
        fillStack(1);
        assertEquals(1, queue.size());
        assertEquals("#0", queue.dequeue());
    }

    @Test
    @DisplayName("Queue.deque(): New, +3 => dequeue() == #0, #1. IgK")
    void dequeue3() {
        fillStack(3);
        assertEquals("#0", queue.dequeue());
        assertEquals("#1", queue.dequeue());
    }

    //-------  clear() ---------------
    @Test
    @DisplayName("Queue.clear(): New, +3, clear() => size() == 0. IgK")
    void clear() {
        fillStack(3);
        queue.clear();
        assertTrue(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Queue.clear(): New, +3, clear(), clear() => size() == 0. IgK")
    void clear2() {
        fillStack(3);
        queue.clear();
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    //********* Iterator ***************
    //-------  hasNext() ---------------
    @Test
    @DisplayName("Iterator.hasNext() : New => hasNext()=false. IgK")
    void iteratorHasNext() {
        assertFalse(queue.iterator().hasNext());
    }

    @Test
    @DisplayName("Iterator.hasNext() : New, +3 => hasNext() = true. IgK")
    void iteratorHasNext2() {
        fillStack(3);
        assertTrue(queue.iterator().hasNext());
    }

    @Test
    @DisplayName("Iterator.hasNext() : New, +3, clear() => hasNext() = false. IgK")
    void iteratorHasNext3() {
        fillStack(3);
        queue.clear();
        assertFalse(queue.iterator().hasNext());
    }

    //-------  next() ---------------
    @Test
    @DisplayName("Iterator.next() : New => next()= NoSuchElementException. IgK")
    void iteratorNext() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.iterator().next();
        });
    }

    @Test
    @DisplayName("Iterator.next() : New, +3 => next() = #0. IgK")
    void iteratorNext2() {
        fillStack(3);
        assertEquals("#0", queue.iterator().next());
    }

    @Test
    @DisplayName("Iterator.next() : New, +3 => next(),next(),next() = #0,#1,#2. IgK")
    void iteratorNext3() {
        fillStack(3);
        Iterator iter = queue.iterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.next() : New, +2, next(),next()=> next() = NoSuchElementException. IgK")
    void iteratorNext4() {
        fillStack(2);
        Iterator iter = queue.iterator();
        iter.next();
        iter.next();
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    //-------  forEach() ---------------
    @Test
    @DisplayName("Iterator.forEach() : New, +5 => forEach() == #0, #1, #2, #3, #4. IgK")
    void forEach() {
        int i = 0;
        fillStack(i);
        for (String elem : queue) {
            assertEquals("#" + i, elem);
            i++;
        }
    }

    //-------  remove() ---------------
    @Test
    @DisplayName("Iterator.remove() : New => remove()= IllegalStateException. IgK")
    void iteratorRemove() {
        assertThrows(IllegalStateException.class, () -> {
            queue.iterator().remove();
        });
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3 => remove() = IllegalStateException. IgK")
    void iteratorRemove2() {
        fillStack(3);
        assertThrows(IllegalStateException.class, () -> {
            queue.iterator().remove();
        });
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3, next(),next(),remove() => #0,#2. IgK")
    void iteratorRemove3() {
        fillStack(3);
        Iterator iter = queue.iterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        iter.remove();

        iter = queue.iterator();
        assertEquals("#0", iter.next());
        assertEquals("#2", iter.next());

//
//        iter = queue.iterator();
//        assertEquals("#2", iter.next());
//        assertEquals("#0", iter.next());
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3, next(),remove(),remove() => remove() = IllegalStateException. IgK")
    void iteratorRemove4() {
        fillStack(3);
        Iterator iter = queue.iterator();
        iter.next();
        iter.remove();
        assertThrows(IllegalStateException.class, () -> {
            iter.remove();
        });
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3, next(),remove(),next(),remove(),next(),remove() => isEmpty()=true. IgK")
    void iteratorRemove5() {
        fillStack(3);
        Iterator iter = queue.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        iter.hasNext();
        assertTrue(queue.isEmpty());
    }

    // ----------- version control -------------------
    @Test
    @DisplayName("Iterator.versionControl() : New, +3, it1(), it2(), it1.next(),it2.next(),itr1.remove() no Exception. IgK")
    void versionControl() {
        fillStack(3);
        Iterator iter1 = queue.iterator();
        Iterator iter2 = queue.iterator();
        iter1.next();
        iter2.next();
        iter1.remove();
    }

    @Test
    @DisplayName("Iterator.versionControl() : New, +3, iter(), next(),next(),deque(),next() => #0,#1,#4 NoSuchElementException.class. IgK")
    void versionControl2() {
        fillStack(5);
        Iterator iter = queue.iterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#0", queue.dequeue());
        assertThrows(ConcurrentModificationException.class, () -> {
            iter.next();
        });
    }

    @Test
    @DisplayName("Iterator.versionControl() : New, +5 , iter1(), iter2(), iter1.next(),iter1.remove()," +
            "iter2.next() => ConcurrentModificationException. IgK")
    void versionControl3() {
        fillStack(5);
        Iterator iter1 = queue.iterator();
        Iterator iter2 = queue.iterator();
        iter1.next();
        iter1.next();
        iter1.remove();

        assertThrows(ConcurrentModificationException.class, () -> {
            iter2.next();
        });
    }


    //-------  isEmpty() ---------------
    @Test
    @DisplayName("Stack.pop(): New => dequeue() == NoSuchElementException. IgK")
    void pop() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.pop();
        });
    }

    @Test
    @DisplayName("Stack.pop(): New, +1 => dequeue() == #0. IgK")
    void pop2() {
        fillStack(1);
        assertEquals(1, queue.size());
        assertEquals("#0", queue.pop());
    }

    @Test
    @DisplayName("Stack.pop(): New, +3 => dequeue() == #2, #1. IgK")
    void pop3() {
        fillStack(3);
        assertEquals("#2", queue.pop());
        assertEquals("#1", queue.pop());
    }

    //-------  peekLast() ---------------
    @Test
    @DisplayName("Stack.peekLast(): New => peekLast() == NoSuchElementException. IgK")
    void peekLast() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.peekLast();
        });
    }

    @Test
    @DisplayName("Stack.peekLast(): New, +1 => peekLast() == #0. IgK")
    void peekLast2() {
        fillStack(1);
        assertEquals("#0", queue.peekLast());
    }

    @Test
    @Disabled
    @DisplayName("Stack.peekLast(): New, +3 => peekLast() == #2, #2. IgK")
    void peekLast3() {
        fillStack(3);
        assertEquals("#0", queue.peekLast());
        assertEquals("#0", queue.peekLast());
    }

    //********* Iterator ***************
    //-------  hasNext() ---------------
    @Test
    @DisplayName("ListIterator.hasNext() : New => hasNext()=false. IgK")
    void listIteratorHasNext() {
        assertFalse(queue.listIterator().hasNext());
    }

    @Test
    @DisplayName("ListIterator.hasNext() : New, +3 => hasNext() = true. IgK")
    void listIteratorHasNext2() {
        fillStack(3);
        assertTrue(queue.listIterator().hasNext());
    }

    @Test
    @DisplayName("ListIterator.hasNext() : New, +3, clear() => hasNext() = false. IgK")
    void listIteratorHasNext3() {
        fillStack(3);
        queue.clear();
        assertFalse(queue.listIterator().hasNext());
    }

    //-------  next() ---------------
    @Test
    @DisplayName("ListIterator.next() : New => next()= NoSuchElementException. IgK")
    void listIteratorNext() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.listIterator().next();
        });
    }

    @Test
    @DisplayName("ListIterator.next() : New, +3 => next() = #2. IgK")
    void listIteratorNext2() {
        fillStack(3);
        assertEquals("#0", queue.listIterator().next());
    }

    @Test
    @DisplayName("ListIterator.next() : New, +3 => next(),next(),next() = #2,#1,#0. IgK")
    void listIteratorNext3() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("ListIterator.next() : New, +2, next(),next()=> next() = NoSuchElementException. IgK")
    void listIteratorNext4() {
        fillStack(2);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    //-------  hasPrevious() ---------------
    @Test
    @DisplayName("ListIterator.hasPrevious() : New => hasPrevious()=false. IgK")
    void iteratorHasPrevious() {
        assertFalse(queue.listIterator().hasPrevious());
    }

    @Test
    @DisplayName("ListIterator.hasPrevious() : New, +3 => hasPrevious() = false. IgK")
    void iteratorHasPrevious2() {
        fillStack(3);
        assertFalse(queue.listIterator().hasPrevious());
    }

    @Test
    @DisplayName("ListIterator.hasPrevious() : New, +5, next() => hasPrevious() = false. IgK")
    void iteratorHasPrevious3() {
        fillStack(5);
        ListIterator iter = queue.listIterator();
        iter.next();
        assertFalse(queue.listIterator().hasPrevious());
    }

    @Test
    @DisplayName("ListIterator.hasPrevious() : New, +3, next(),next() => hasPrevious() = true. IgK")
    void iteratorHasPrevious4() {
        fillStack(5);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        assertFalse(queue.listIterator().hasPrevious());
    }

    //-------  previous() ---------------
    @Test
    @DisplayName("ListIterator.previous() : New => next()= NoSuchElementException. IgK")
    void previous() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.listIterator().previous();
        });
    }

    @Test
    @DisplayName("ListIterator.previous() : New, +3 => next() = #0. IgK")
    void previous2() {
        fillStack(3);
        assertThrows(NoSuchElementException.class, () -> {
            queue.listIterator().previous();
        });
    }

    @Test
    @DisplayName("ListIterator.previous() : New, +3 => next(),next(),previus(),previus(),previus() = NoSuchElementException. IgK")
    void previous3() {
        fillStack(2);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        iter.previous();
        iter.previous();
        assertThrows(NoSuchElementException.class, () -> {
            queue.listIterator().previous();
        });
    }

    @Test
    @DisplayName("ListIterator.previous() : New, +3 => next(),next(),next()," +
            "previus(),previus() = #1,#2. IgK")
    void previous4() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        iter.next();
        assertEquals("#2", iter.previous());
        assertEquals("#1", iter.previous());
    }

//    //-------  forEach() ---------------
//    @Test
//    @DisplayName("ListIterator.forEach() : New, +5 => forEach() == #0,#1,#2,#3,#4 IgK")
//    void forEach() {
//        fillStack(5);
//        int i=0;
//        for (String elem : queue){
//            assertEquals("#"+i,elem);
//            i++;
//        }
//    }

    //-------  nextIndex() ---------------
    @Test
    @DisplayName("ListIterator.nextIndex() : New, +3, nextIndex(),next()...nextIndex() => nextIndex() = 0,1,2,3,3. IgK")
    void nextIndex() {
        fillStack(5);
        ListIterator iter = queue.listIterator();
        assertEquals(0, iter.nextIndex());
        iter.next();
        assertEquals(1, iter.nextIndex());
        iter.next();
        assertEquals(2, iter.nextIndex());
        iter.next();
        assertEquals(3, iter.nextIndex());
        assertEquals(3, iter.nextIndex());
    }

    //-------  previousIndex() ---------------
    @Test
    @DisplayName("ListIterator.previousIndex() : New, +3, previousIndex(),next()...previousIndex() => " +
            "previousIndex() = -1,0,1,2,2. IgK")
    void previousIndex() {
        fillStack(5);
        ListIterator iter = queue.listIterator();
        assertEquals(-1, iter.previousIndex());
        iter.next();
        assertEquals(0, iter.previousIndex());
        iter.next();
        assertEquals(1, iter.previousIndex());
        iter.next();
        assertEquals(2, iter.previousIndex());
        assertEquals(2, iter.previousIndex());
    }

    //-------  remove() ---------------
    @Test
    @DisplayName("Iterator.remove() : New => remove()= IllegalStateException. IgK")
    void listIteratorRemove() {
        assertThrows(IllegalStateException.class, () -> {
            queue.listIterator().remove();
        });
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3 => remove() = IllegalStateException. IgK")
    void listIteratorRemove2() {
        fillStack(3);
        assertThrows(IllegalStateException.class, () -> {
            queue.listIterator().remove();
        });
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3, next(),next(),remove() => #0,#2. IgK")
    void listIteratorRemove3() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        iter.remove();
        assertEquals("#2", iter.next());

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.remove() : New, +3, next(),remove(),remove() => remove() = IllegalStateException. IgK")
    void listIteratorRemove4() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.remove();
        assertThrows(IllegalStateException.class, () -> {
            iter.remove();
        });
    }

    @Test
    @Disabled
    @DisplayName("Iterator.remove() : New => +3, next(),remove(),next(),remove(),next(),remove() => isEmpty()=true. IgK")
    void listIteratorRemove5() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        assertFalse(queue.isEmpty());
    }

    //-------------- public void set(T t) ----------------------
    @Test
    @DisplayName("Iterator.set(): New => set()= IllegalStateException. IgK")
    void set() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        assertThrows(IllegalStateException.class, () -> {
            iter.set("test");
        });
    }

    @Test
    @DisplayName("Iterator.set(): New, +3 => remove() = IllegalStateException. IgK")
    void set2() {
        fillStack(3);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        iter.set("#123");

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#123", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.set(): New, +5, next(),next(),next(),next(),set()," +
            "previous(),set(),previous(),set() => #0,#1,#345,#234,#2. IgK")
    void set3() {
        fillStack(5);
        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.set("#123");
        iter.previous();
        iter.set("#234");
        iter.previous();
        iter.set("#345");

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#345", iter.next());
        assertEquals("#234", iter.next());
        assertEquals("#4", iter.next());
    }

    //-------------- public void add(T t) ----------------------
    @Test
    @DisplayName("Iterator.add(): New, #0,#1,#2 => next()=#0,#1,#2 IgK")
    void add() {
        ListIterator iter = queue.listIterator();
        iter.add("#0");
        iter.add("#1");
        iter.add("#2");

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.add(): New +3,#+1,#+2 => next()=#+1,#+2,#0,#1,#2. IgK")
    void add2() {
        fillStack(3);

        ListIterator iter = queue.listIterator();
        iter.add("#+1");
        iter.add("#+2");

        iter = queue.listIterator();
        assertEquals("#+1", iter.next());
        assertEquals("#+2", iter.next());
        assertEquals("#0", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.add(): New,+3,next(),#3,#4 => next() = #0,#+1,#+2,#1,#2. IgK")
    void add3() {
        fillStack(3);

        ListIterator iter = queue.listIterator();
        iter.next();
        iter.add("#+1");
        iter.add("#+2");

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#+1", iter.next());
        assertEquals("#+2", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.add(): New,+3,next(),next(),prev(),#+1,#+2 => next() = #0,#+1,#+2,#1,#2. IgK")
    void add4() {
        fillStack(3);

        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        iter.previous();
        iter.add("#+1");
        iter.add("#+2");

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#+1", iter.next());
        assertEquals("#+2", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#2", iter.next());
    }

    @Test
    @DisplayName("Iterator.add(): New,+3,next(),next(),prev(),#+1,next(),prev(),#+2,#+3 => next() = " +
            "#0,#+1,#1,#+2,#+3,#2. IgK")
    void add5() {
        fillStack(3);

        ListIterator iter = queue.listIterator();
        iter.next();
        iter.next();
        iter.previous();
        iter.add("#+1");
        iter.next();
        iter.add("#+2");
        iter.add("#+3");

        iter = queue.listIterator();
        assertEquals("#0", iter.next());
        assertEquals("#+1", iter.next());
        assertEquals("#1", iter.next());
        assertEquals("#+2", iter.next());
        assertEquals("#+3", iter.next());
        assertEquals("#2", iter.next());
    }

}

package com.tkachenko;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    double[] arr2 = {0.1, 0.2, 0.3, 0.3, 0.3, 0.6, 0.7, 0.8, 0.9, 1};

    @Test
    @DisplayName("Check if lbs returns proper index. IlK")
    void leftBinarySearch() {
        assertEquals(2, BinarySearch.leftBinarySearch(arr2, 0.3));
    }

    @Test
    @DisplayName("Lbs works properly with most left element. IlK")
    void leftBinarySearch1() {
        double[] arr2 = {0.3, 0.3, 0.3, 0.3, 0.3, 0.6, 0.7, 0.8, 0.9, 1};
        assertEquals(0, BinarySearch.leftBinarySearch(arr2, 0.3));
    }

    @Test
    @DisplayName("Rbs works properly. IlK")
    void rightBinarySearch() {
        assertEquals(4, BinarySearch.rightBinarySearch(arr2, 0.3));
    }

    @Test
    @DisplayName("Rbs works properly with most right element. IlK")
    void rightBinarySearch1() {
        double[] arr2 = {0.3, 0.3, 0.3, 0.3, 0.3, 0.6, 0.7, 0.9, 0.9, 0.9};
        assertEquals(arr2.length - 1, BinarySearch.rightBinarySearch(arr2, 0.9));
    }

    @Test
    @DisplayName("Check if lbs returns ix > arr[i] if there are no such elements. TD")
    void leftBinarySearchFail() {
        double[] arr2 = {0.1, 0.2, 0.3, 0.5, 0.6, 0.6, 0.7, 0.8, 0.9, 1};
        assertEquals(-4, BinarySearch.leftBinarySearch(arr2, 0.4));
    }

    @Test
    @DisplayName("Rbs returns ix < arr[i] if there are no such elements. TD")
    void rightBinarySearchFail() {
        double[] arr2 = {0.1, 0.2, 0.3, 0.5, 0.6, 0.6, 0.7, 0.8, 0.9, 1};
        assertEquals(-3, BinarySearch.rightBinarySearch(arr2, 0.4));
    }

    @Test
    @DisplayName("Cbs returns proper count of elements. IlK")
    void countBinarySearch() {
        assertEquals(3, BinarySearch.countBinarySearch(arr2, 0.3));
    }

    @Test
    @DisplayName("Cbs returns 0 if no elements found. IlK")
    void countBinarySearchFail() {
        double[] arr2 = {0.3, 0.3, 0.3, 0.3, 0.3, 0.6, 0.7, 0.9, 0.9, 0.9};
        assertEquals(0, BinarySearch.countBinarySearch(arr2, 0.4));
    }

    @Test
    @DisplayName("Cbs returns 1 if there is only element. IlK")
    void countBinarySearchFail1() {
        double[] arr2 = {0.3, 0.3, 0.3, 0.3, 0.3, 0.6, 0.7, 0.9, 0.9, 0.9};
        assertEquals(1, BinarySearch.countBinarySearch(arr2, 0.6));
    }

    @Test
    @DisplayName("Test time for array 1k elements. IlK")
    void testTime() {
        int n = 1000;
        int nKey = 500;
        double key = 0.5;
        double[] arr = new Random().doubles().limit(n).toArray();
        Arrays.fill(arr, 0, nKey, key);
        Arrays.sort(arr);
        long start = System.nanoTime();
        assertEquals(500, BinarySearch.countBinarySearch(arr, key));
        long finish = System.nanoTime() - start;
        System.out.println(finish / 1e6);
    }

    @Test
    @DisplayName("Test time for array 100m elements. IlK")
    void testTime1() {
        int n = 100_000_000;
        int nKey = 500_000;
        double key = 0.5;
        double[] arr = new Random().doubles().limit(n).toArray();
        Arrays.fill(arr, 256, nKey, key);
        Arrays.sort(arr);
        long start = System.nanoTime();
        assertEquals(499744, BinarySearch.countBinarySearch(arr, key));
        long finish = System.nanoTime() - start;
        System.out.println(finish / 1e6);
    }
}

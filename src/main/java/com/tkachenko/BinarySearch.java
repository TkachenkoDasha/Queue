package com.tkachenko;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    public static void main(String[] args) {
        final int n = 1000;
        final int nKey = 500;
        final double key = 0.5;
        double[] arr = new Random().doubles().limit(n).toArray();
        Arrays.fill(arr, 0, nKey, key);
        Arrays.sort(arr);
        int count = countBinarySearch(arr, key);
        assert count == nKey;
        System.out.println(leftBinarySearch(arr, key));
        System.out.println(rightBinarySearch(arr, key));
        System.out.println(count);
    }

    /* return index ix such that arr[ix-1] < key, for any i >= ix arr[i] >= key
     *
     * @param arr
     * @param key
     * @return index ix such that arr[ix-1] < key, for any i >= ix arr[i] >= key
     */
    public static int leftBinarySearch(double[] arr, double key) {
        int ix = -1;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            double midVal = arr[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                ix = mid;
                high = mid - 1;
            }
        }

        return ix == -1 ? -(low + 1) : ix;
    }

    /* return index ix such that arr[ix+1] > key, for any i <= ix arr[i] <= key
     *
     * @param arr
     * @param key
     * @return index ix such that arr[ix+1] > key, for any i <= ix arr[i] <= key
     */
    public static int rightBinarySearch(double[] arr, double key) {
        int ix = -1;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            double midVal = arr[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                ix = mid;
                low = mid + 1;
            }
        }

        return ix == -1 ? -(high + 1) : ix;
    }

    public static int countBinarySearch(double[] arr, double key) {
        if (leftBinarySearch(arr, key) < 0 && rightBinarySearch(arr, key) < 0) {
            return 0;
        }

        return (rightBinarySearch(arr, key) - leftBinarySearch(arr, key)) + 1;
    }
}

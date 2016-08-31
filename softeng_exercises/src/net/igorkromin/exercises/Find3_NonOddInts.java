package net.igorkromin.exercises;

import net.igorkromin.Exercise;
import net.igorkromin.supporting.QuickSort;

import java.util.Arrays;

/**
 * You have an array of integers, such that each integer is present an odd number of times, except 3 of them. Find the three numbers.
 *
 * Example -
 *
 *      int    # present
 *      8      5
 *      4      2  <-
 *      6      2  <-
 *      2      3
 *      1      1
 *      5      2  <-
 *      9      3
 *      7      1
 *
 * Output should contain these ints: 4, 6, 5
 */
public class Find3_NonOddInts extends Exercise {

    static int[] arrInt = {8, 4, 6, 2, 2, 8, 4, 2, 8, 6, 8, 8, 1, 9, 5, 9, 7, 5, 9};
    //static int[] arrInt = {4, 4, 6, 2, 2, 8, 4, 2, 8, 6, 4, 8, 1, 5, 5, 9, 7, 5, 5};

    // --- Naive looping solution
    // Run time complexity is O(n^2)
    // Space complexity is O(1)

    public void solveNaive() {
        int v, c, idx = 0;
        int[] vals = new int[3];

        for (int i = 0; i < arrInt.length; i++) {
            v = arrInt[i];
            c = 0;

            for (int j = 0; j < arrInt.length; j++) {
                int a = arrInt[j];
                if (v == a) {
                    c++;
                }
            }

            if (c % 2 == 0) {
                if (idx == 0) {
                    vals[0] = v;
                    idx++;

                    out("Found non-odd occurrence for int = " + v);
                }
                else {
                    if (!inArray(v, vals, idx)) {
                        vals[idx] = v;
                        out("Found non-odd occurrence for int = " + v);
                        idx++;
                    }
                }
            }
        }
    }

    protected static boolean inArray(int v, int[] arr, int maxIdx) {
        for (int i = 0; i < maxIdx; i++) {
            if (arr[i] == v) {
                return true;
            }
        }
        return false;
    }

    // --- Bucket based solution
    // Run time complexity is O(n^2)
    // Space complexity is O(n)

    public void solveWithBuckets() {
        int[] values = new int[arrInt.length];
        int[] counts = new int[arrInt.length];

        // set all values to -1
        for (int i = 0; i < arrInt.length; i++) {
            values[i] = -1;
        }

        for (int i = 0; i < arrInt.length; i++) {
            int val = arrInt[i];
            int idx = findValIdx(values, val);
            if (idx == -1) {
                idx = addVal(values, val);
            }
            counts[idx]++;
        }

        for (int i = 0; i < arrInt.length; i++) {
            if (values[i] == -1) {
                break;
            }
            if (counts[i] % 2 == 0) {
                out("Found non-odd occurrence for int = " + values[i]);
            }
        }
    }

    private int findValIdx(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (val == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    private int addVal(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                arr[i] = val;
                return i;
            }
        }
        return -1;
    }

    // --- Quick Sort based solution
    // Run time complexity is O(n log 2)
    // Space complexity is O(1)

    public void solveWithSort() {
        // clone the array so other solutions are not affected (just in case), this is not part of the solution
        int[] newArr = Arrays.copyOf(arrInt, arrInt.length);

        QuickSort.quickSort(newArr);

        int v = newArr[0];
        int c = 1;

        for (int i = 1; i < newArr.length; i++) {
            int a = newArr[i];
            if (v == a) {
                c++;
            }
            else {
                if (c % 2 == 0) {
                    out("Found non-odd occurrence for int = " + v);
                }
                v = a;
                c = 1;
            }
        }
    }

    // -- Main method

    public static final void main(String[] args) {
        run(new Find3_NonOddInts());
    }

}
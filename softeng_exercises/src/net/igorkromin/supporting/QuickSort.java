package net.igorkromin.supporting;

/**
 * Quick Sort implementation
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int loIdx, int hiIdx) {
        if (loIdx < hiIdx) {
            int partition = partition(arr, loIdx, hiIdx);
            quickSort(arr, loIdx, partition - 1);
            quickSort(arr, partition + 1, hiIdx);
        }
    }

    private static int partition(int[] arr, int loIdx, int hiIdx) {
        int pivot = arr[hiIdx];
        int swapIdx = loIdx;

        for (int i = loIdx; i < hiIdx; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, swapIdx);
                swapIdx++;
            }
        }
        swap(arr, swapIdx, hiIdx);

        return swapIdx;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int a = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = a;
    }

}

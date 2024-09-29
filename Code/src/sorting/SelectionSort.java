package sorting;

import utility.general.SortUtils;

public class SelectionSort {

    /**
     * Sorts an array using the selection sort algorithm.
     * This method repeatedly finds the minimum element from the unsorted part of the array
     * and moves it to the beginning. In every iteration of the selection sort, the minimum
     * element from the unsorted subarray is picked and moved to the sorted subarray.
     *
     * @param arr The array to be sorted.
     */
    public static void sort(int[] arr) {
        int length = arr.length;
        // Iterate over the array
        for (int i = 0; i < length - 1; i++) {
            // Assume the first element of the unsorted part as the minimum
            int minIndex = i;
            // Check the rest of the array to find the true minimum
            for (int j = i + 1; j < length; j++) {
                // If we find an element smaller than the current minimum, update minIndex
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element of the unsorted part
            // Only perform the swap if the minimum is not already in place
            if (minIndex != i) {
                SortUtils.swap(arr, minIndex, i);
            }
        }
    }
}

package sorting;

import utility.general.SortUtils;

public class BubbleSort {

    /**
     * Sorts an array in ascending order using the bubble sort algorithm.
     * This method iterates over the array, comparing and swapping adjacent elements into the
     * correct order.
     * The algorithm stops early if no swaps are needed during a new pass, indicating the array
     * is sorted.
     *
     * @param array The array to be sorted.
     */
    public static void sort(int[] array) {
        boolean swapped; // Flag to track if a swap has occurred during the pass
        int length = array.length; // Initially set the entire array for sorting
        do {
            swapped = false; // Reset swap flag at the start of each pass
            for (int i = 0; i < length - 1; i++) { // Only iterate up to the unsorted part of the array
                if (array[i] > array[i + 1]) { // Compare adjacent elements
                    // Swap elements
                    SortUtils.swap(array, i, i + 1);
                    swapped = true; // Indicate a swap occurred
                }
            }
            length--; // Reduce the sorting range since the last element is now sorted
        } while (swapped); // If no swaps occurred, the array is sorted
    }

}

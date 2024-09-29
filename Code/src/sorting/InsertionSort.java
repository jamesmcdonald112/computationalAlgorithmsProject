package sorting;

public class InsertionSort {

    /**
     * Sorts an array in ascending order using the insertion sort algorithm.
     * The algorithm iterates over the array, and for each iteration, it removes one element from the array,
     * finds the location it belongs to in the already sorted part of the array, and inserts it there,
     * repeating this process until the whole array is sorted.
     *
     * @param array The array of integers to be sorted.
     */
    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i]; // The current element to be positioned
            int j = i - 1; // The last index of the sorted section

            // Shift elements of the sorted section that are greater than the current element
            // to one position ahead of their current position
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }

            // Place the current element at its correct position in the sorted section
            array[j + 1] = current;
        }
    }
}

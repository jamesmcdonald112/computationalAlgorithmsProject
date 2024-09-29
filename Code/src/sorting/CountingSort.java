package sorting;

public class CountingSort {

    /**
     * Sorts an array in ascending order using the counting sort algorithm.
     * This method first finds the maximum value to determine the size of the count array.
     * It then counts each element, computes cumulative counts, and uses these counts
     * to place elements directly into their final positions in the original array.
     *
     * @param arr The array of integers to be sorted.
     */
    public static void sort(int[] arr) {
        int max = findMax(arr); // Find the maximum value in the array
        int[] count = new int[max + 1]; // Initialise the count array
        // Count each element's frequency
        for (int num : arr) {
            count[num]++;
        }
        // Accumulate the counts
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // Place elements into the sorted order
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        // Copy sorted elements back to the original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    /**
     * Finds the maximum value in an array.
     *
     * @param arr The array to search for the maximum value.
     * @return The maximum value found in the array.
     */
    private static int findMax(int[] arr) {
        int max = arr[0]; // Assume the first element is the maximum
        for (int num : arr) {
            if (num > max) {
                max = num; // Update max if current element is greater
            }
        }
        return max; // Return the maximum value found
    }

}

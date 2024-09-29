package sorting;

public class MergeSort {

    /**
     * Checks if the array length is more than one to decide whether sorting is needed and then
     * calls the recursive Merge Sort method.
     *
     * @param array The array to be sorted.
     */
    public static void sort(int[] array) {
        if (array.length > 1) {
            performMergeSort(array, 0, array.length - 1);
        }
    }

    /**
     * Performs the recursive part of the merge sort algorithm. It divides the array into smaller subarrays until
     * each subarray consists of a single element, then merges those subarrays in sorted order.
     *
     * @param array The array to be sorted.
     * @param start The starting index of the portion of the array to be sorted.
     * @param end   The ending index of the portion of the array to be sorted.
     */
    private static void performMergeSort(int[] array, int start, int end) {
        // Recursively divide the array if the current segment has more than one element
        if (start < end) {
            int middle = (start + end) / 2; // Calculate the middle point of the current segment

            // Recursively sort the left half of the array
            performMergeSort(array, start, middle);
            // Recursively sort the right half of the array
            performMergeSort(array, middle + 1, end);

            // Merge the sorted halves
            mergeSubArrays(array, start, middle, end);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray. The first subarray is from start to middle,
     * and the second subarray is from middle+1 to end.
     *
     * @param array  The array containing the subarrays to merge.
     * @param start  The starting index of the first subarray.
     * @param middle The ending index of the first subarray and one less than the start of the second subarray.
     * @param end    The ending index of the second subarray.
     */
    private static void mergeSubArrays(int[] array, int start, int middle, int end) {
        // Initialise the sizes of temporary arrays to hold the subarrays
        int sizeLeft = middle - start + 1;
        int sizeRight = end - middle;

        // Create temporary arrays
        int[] leftArray = new int[sizeLeft];
        int[] rightArray = new int[sizeRight];

        // Copy the elements into the temporary arrays
        System.arraycopy(array, start, leftArray, 0, sizeLeft);
        System.arraycopy(array, middle + 1, rightArray, 0, sizeRight);

        // Merge the temporary arrays back into the original array
        int i = 0, j = 0; // Indices for left and right subarrays
        int k = start; // Index of merged subarray
        while (i < sizeLeft && j < sizeRight) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i++];
            } else {
                array[k] = rightArray[j++];
            }
            k++;
        }

        // Copy the remaining elements of leftArray, if any
        while (i < sizeLeft) {
            array[k++] = leftArray[i++];
        }

        // Copy the remaining elements of rightArray, if any
        while (j < sizeRight) {
            array[k++] = rightArray[j++];
        }
    }
}

package utility.general;

public class SortUtils {

    /**
     * Swaps two elements in an array.
     *
     * @param array The array where the elements need to be swapped.
     * @param i     The index of the first elements to be swapped.
     * @param j     The index of the second element to be swapped.
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i]; // Hold the value at i
        array[i] = array[j]; // Replace the value at index i with the value at index j
        array[j] = temp;     // Replace the value at index j with the held value of index i
    }

    /**
     * Takes in a array and returns a clone
     *
     * @param src The array to be copied
     * @return a copy of the original array
     */
    public static int[] copyArr(int[] src){
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}

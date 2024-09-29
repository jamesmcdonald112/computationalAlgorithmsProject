package utility.general;

public class ArrayGenerator {

    /**
     * Generates an array filled with random integers.
     *
     * @param size       The size of the array to generate.
     * @return An array filled with random integers.
     */
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            // Math.random() is a double
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
}

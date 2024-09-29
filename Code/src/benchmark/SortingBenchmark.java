package benchmark;

import java.util.function.Consumer;

public class SortingBenchmark {

    private static int repeats = 10; // Default value

    /**
     * Benchmarks a sorting algorithm by measuring its execution time on a given array.
     * The sorting algorithm is executed multiple times to calculate the average execution time,
     * providing a more accurate benchmark.
     *
     * @param sortMethod The sorting method to benchmark. It must implement the {@link Consumer} interface,
     *                   accepting an array of integers.
     * @param array      The array to sort. The method clones this array before sorting to avoid modifying
     *                   the original array.
     * @return The average execution time in milliseconds for the sorting algorithm.
     */
    public static double benchmarkSort(Consumer<int[]> sortMethod, int[] array) {
        long totalDuration = 0;

        for (int i = 0; i < repeats; i++) {
            // Clone the array so the original is not sorted.
            int[] arrayToSort = array.clone();
            long startTime = System.nanoTime();
            // Using the Consumer functional interface, which defines the accept method.
            // Accept is called to execute the sorting algorithm on the arrayToSort.
            sortMethod.accept(arrayToSort);
            long endTime = System.nanoTime();

            totalDuration += (endTime - startTime);
        }

        // Converted from nanoseconds to milliseconds and average time taken is returned.
        return totalDuration / (repeats * 1_000_000.0);
    }
}

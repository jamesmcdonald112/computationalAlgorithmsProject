package benchmark;

import config.ConfigurationManager;
import sorting.*;
import utility.console.ConsoleDisplay;
import utility.general.ArrayGenerator;
import utility.general.SortUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class BenchmarkRunner {

    // Instance variable that stores the results of the benchmark results, to output them in the
    //formatted table.
    Map<String, Map<Integer, Double>> benchmarkResults = new HashMap<>();

    /**
     * The main method loads configuration settings, starts the benchmark process for various array
     * sizes, and writes the benchmark results to a specified file.
     */
    public static void main(String[] args) {
        ConfigurationManager.loadConfigurations();

        // Stored as a string array as Properties only stores values as strings
        String[] sizeConfig = ConfigurationManager.getProperty("sizes").split(",");

        // Initialise an array 'sizes' with parsed integer values from the 'sizeConfig' string
        // array.
        int[] sizes = new int[sizeConfig.length];
        for (int i = 0; i < sizeConfig.length; i++) {
            sizes[i] = Integer.parseInt(sizeConfig[i]);
        }

        BenchmarkRunner runner = new BenchmarkRunner();
        runner.runBenchmarks(sizes);
    }

    /**
     * Iterates over sizes array, generates a random array for each, and runs performBenchmarking
     * method on each.
     *
     * @param sizes An array containing the sizes for each array.
     */
    private void runBenchmarks(int[] sizes) {
         /*Iterate over each size specified in 'sizes'. For each size, generate an array filled with random
         integers. Each generated array is then used to benchmark
         all sorting algorithms. This ensures that sorting performance is tested across arrays of
         varying sizes and contents, maintaining the same initial array for all sorting
         algorithms to ensure fairness in benchmarking results.*/
        for (int size : sizes) {
            int[] originalArray = ArrayGenerator.generateRandomArray(size);
            try {
                performBenchmarking(size, originalArray);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Build the output string for the file.
        StringBuilder resultsForFile = new StringBuilder(generateResultsTable(benchmarkResults, sizes));

        // Write the results to a file.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("benchmark_results.txt"))) {
            writer.write(resultsForFile.toString());
        } catch (IOException e) {
            ConsoleDisplay.displayErrorMessage("Error writing to benchmark results file: " + e.getMessage());
        }

        // Display the benchmark table on the console.
        ConsoleDisplay.displayBenchmarkTable(benchmarkResults, sizes);
    }

    /**
     * Performs benchmarking for all sorting algorithms on a given array size, writes the results to a file,
     * and displays them on the console.
     *
     * @param size          The size of the array being benchmarked.
     * @param originalArray The original array to sort.
     * @throws IOException If an I/O error occurs.
     */
    private void performBenchmarking(int size, int[] originalArray) throws IOException {
        // Map of algorithm names to their corresponding sort methods. The explanation from this
        // video helped me to understand the Consumer functional interface and its uses
        // https://www.youtube.com/watch?v=o384gHvf22Y&ab_channel=TrendingCode

        // String will represent the algorithm name and Consumer<int[]> will represent the sort
        // method
        Map<String, Consumer<int[]>> algorithms = new HashMap<>();

        // Sets the sorting algorithm name as the key and the sorting method as the value.
        algorithms.put("BubbleSort", BubbleSort::sort);
        algorithms.put("InsertionSort", InsertionSort::sort);
        algorithms.put("MergeSort", MergeSort::sort);
        algorithms.put("SelectionSort", SelectionSort::sort);
        algorithms.put("CountingSort", CountingSort::sort);

        // For each key-value pair (entry) in algorithms
        for (Map.Entry<String, Consumer<int[]>> entry : algorithms.entrySet()) {
            // Stores the average time taken to sort a clone of the same data so the test is fair.
            // entry.value represents the sort method and will be run for each algorithm.
            double averageDuration =
                    SortingBenchmark.benchmarkSort(entry.getValue(), SortUtils.copyArr(originalArray));

            // Retrieve the mapping of array sizes and sorting durations for the current algorithm.
            Map<Integer, Double> sizeAndDuration = benchmarkResults.get(entry.getKey());

            // Check if there is no existing map for this algorithm.
            if (sizeAndDuration == null) {
                // If no map exists, create a new HashMap to store size-duration pairs.
                sizeAndDuration = new HashMap<>();
                // Add the new map to benchmarkResults with the algorithm name as the key.
                benchmarkResults.put(entry.getKey(), sizeAndDuration);
            }

            // Add the benchmark result for the current size: map the array size to the average sorting duration.
            sizeAndDuration.put(size, averageDuration);

        }
    }

    /**
     * Generates a formatted benchmark results table that is returned as a string.
     *
     * @param benchmarkResults The benchmarks results used for the table
     * @param sizes            The array sizes used to populate the table.
     * @return The results in a tabel format as a string.
     */
    private String generateResultsTable(Map<String, Map<Integer, Double>> benchmarkResults, int[] sizes) {
        StringBuilder tableBuilder = new StringBuilder();

        // The "Size" column takes up 15 characters space
        tableBuilder.append(String.format("%-15s", "Size"));
        for (int size : sizes) {
            tableBuilder.append(String.format("%-10d", size));
        }
        tableBuilder.append("\n");

        // Entry is the sorting algorithm and its corresponding map results.
        for (Map.Entry<String, Map<Integer, Double>> entry : benchmarkResults.entrySet()) {
            // Appends the name of the sorting algorithm
            tableBuilder.append(String.format("%-15s", entry.getKey()));
            Map<Integer, Double> results = entry.getValue();
            // For each size in the sizes array, the loop gets the average duration from results
            for (int size : sizes) {
                // If a result for a size doesn't exist, it defaults to 0.0.
                tableBuilder.append(String.format("%-10.3f", results.getOrDefault(size, 0.0)));
            }
            tableBuilder.append("\n");
        }
        return tableBuilder.toString();
    }


}

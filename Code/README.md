# Computational Thinking with Algorithms

This project benchmarks various sorting algorithms using arrays of different sizes. The results are displayed in a console table and saved to a file for analysis.

## Features
- Sorting Algorithms: These include bubble sort, insertion sort, merge sort, selection sort, and counting sort.
- Benchmarking: The performance of each algorithm is measured in milliseconds and compared across different array sizes.
- Random Array Generation: Arrays with random integers are generated to ensure diverse test cases.
- Configuration: Array sizes can be configured via a properties file.

## How to Run
1.	Clone the repository:
```bash
git clone https://github.com/jamesmcdonald112/computationalAlgorithmsProject.git
cd computationalAlgorithmsProject
```

2. Compile the code:
```bash
javac src/**/*.java
```

3. Run the benchmarks:
```bash
cd src
java benchmark/BenchmarkRunner
```

4.	View Results: The results will be displayed in the console and saved to benchmark_results.txt in the src folder.

## Configuration
Modify the array sizes for benchmarking in the config.properties file. Example:
```bash
sizes=100,250,500,750,1000
```

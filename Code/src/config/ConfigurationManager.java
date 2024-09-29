package config;

import benchmark.BenchmarkRunner;
import utility.console.ConsoleDisplay;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    // Holds configuration setting.
    private static Properties properties = new Properties();

    /**
     * Loads configuration properties from a specified file.
     */
    public static void loadConfigurations() {
        try {
            /*
            This information for this section was found though the site linked below. My
            understanding is that:
            - Properties creates an instance of properties used to save my config info in
            key-value pairs.
            - load() is a method of the Properties class that will read the file and loads the
            content to the properties object.
            - BenchmarkRunner.class is the reference to the BenchmarkRunner class object.
            - getClassLoader() retries the loader that loaded BenchmarkRunner.class. This class
            loader is used to load resources in the same class path.
            - getResourceAsStream() returns an InputStream from which the file's content is read
            - "config.properties" is the name of the file it is searching for.

            https://www.baeldung.com/reading-file-in-java
             */

            // Load the properties file from the classpath
            properties.load(BenchmarkRunner.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            ConsoleDisplay.displayErrorMessage("Failed to load configurations: " + e.getMessage());
            System.exit(1);
        } catch (NullPointerException e) {
            ConsoleDisplay.displayErrorMessage("Configuration file not found.");
            System.exit(1);
        }
    }


    /**
     * Retrieves a configuration value by key.
     *
     * @param key The configuration key.
     * @return The configuration value.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

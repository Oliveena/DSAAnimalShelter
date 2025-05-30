package app;

import java.util.logging.Logger;
import patterns.creational.factories.LogFactory;
import ui.CLI.ShelterApp;

/**
 * The entry point of the Shelter Application.
 * <p>
 * This class initializes logging and launches the CLI-based shelter application.
 * It handles any unexpected exceptions that may occur during startup.
 * </p>
 */
public class Main {

    /**
     * Logger instance used to log messages for the Main class.
     * Initialized using a factory method from {@link LogFactory}.
     */
    private static final Logger logger = LogFactory.getLogger(Main.class);

    /**
     * Main method that starts the Shelter Application.
     * <p>
     * This method initializes the {@link ShelterApp} class and starts the application.
     * Any exceptions thrown during the startup are caught and logged.
     * </p>
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        logger.info("Starting Shelter Application...");

        try {
            ShelterApp app = new ShelterApp();
            app.start();
        } catch (Exception e) {
            logger.severe("Error starting ShelterApp: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

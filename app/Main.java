package app;

import java.util.logging.Logger;
import patterns.creational.factories.LogFactory;
import ui.CLI.ShelterApp;

public class Main {

    private static final Logger logger = LogFactory.getLogger(Main.class);

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

package ui;

/**
 * The main entry point for the Shelter application.
 * <p>
 * This class contains the {@link Main(String[])} method which initializes and starts
 * the {@link ShelterApp}. It serves as the starting point for the application's execution.
 */
public class Main {

    /**
     * The main method that launches the Shelter application.
     * <p>
     * This method initializes the {@link ShelterApp} class and starts the shelter
     * app by invoking the {@link ShelterApp#start()} method.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        ShelterApp app = new ShelterApp();  // Create an instance of ShelterApp
        app.start();  // Start the ShelterApp
    }
}

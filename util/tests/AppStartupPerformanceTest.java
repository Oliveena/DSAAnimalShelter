package util.tests;

import org.junit.jupiter.api.Test;
import ui.CLI.ShelterApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Performance test class to evaluate the startup time of the {@link ShelterApp}.
 * <p>
 * Ensures that the application initializes within an acceptable time frame.
 */
public class AppStartupPerformanceTest {

    /**
     * Tests the time it takes for the {@link ShelterApp} to start silently (non-interactively).
     * <p>
     * The test asserts that the startup process completes in less than 2000 milliseconds.
     * This helps ensure the app maintains performance expectations.
     */
    @Test
    public void testAppStartupTime() {
        long startTime = System.currentTimeMillis();

        ShelterApp app = ShelterApp.getInstance();
        app.startSilently(); // Simulated non-interactive startup

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("App startup took " + duration + " ms");
        assertTrue(duration < 2000, "App startup took too long!");
    }
}

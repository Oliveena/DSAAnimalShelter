package util.tests;

import org.junit.jupiter.api.Test;
import ui.CLI.ShelterApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppStartupPerformanceTest {

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

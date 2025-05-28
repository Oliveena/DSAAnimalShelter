package patterns.creational.factories;

import java.util.logging.Logger;

/**
 * A centralized factory for creating class-specific loggers.
 */
public class LogFactory {
    /**
     * Returns a logger for the specified class.
     *
     * @param clazz the class for which the logger is created
     * @return a Logger instance for the given class
     */
    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}

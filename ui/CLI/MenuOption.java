package ui.CLI;

import java.util.function.Consumer;

/**
 * Represents a selectable menu option in the command-line interface (CLI).
 * <p>
 * Each {@code MenuOption} contains a description and an associated action
 * that is executed when the option is selected.
 */
public class MenuOption {
    private final String description;
    private final Runnable action;

    /**
     * Constructs a new {@code MenuOption} with a description and a runnable action.
     *
     * @param description the text description shown in the menu
     * @param action      the code to execute when this menu option is selected
     */
    public MenuOption(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    /**
     * Returns the description of the menu option.
     *
     * @return the description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Executes the action associated with this menu option.
     */
    public void execute() {
        action.run();
    }
}

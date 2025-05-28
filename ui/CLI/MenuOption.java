package ui.CLI;

import java.util.function.Consumer;

public class MenuOption {
    private final String description;
    private final Runnable action;

    public MenuOption(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
        action.run();
    }
}

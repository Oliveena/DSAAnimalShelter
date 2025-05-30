package ui.CLI.menus;

import controllers.VolunteerController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Defines the command-line menu options available for volunteers in the system.
 * <p>
 * Provides access to volunteer-related actions such as registration and task assignment.
 * Each option is mapped to a {@link MenuOption} which delegates functionality to the
 * corresponding controller methods.
 */
public class VolunteerMenu {

    private final VolunteerController volunteerController;

    /**
     * Constructs a new {@code VolunteerMenu} with the given controller.
     *
     * @param volunteerController the controller that handles volunteer operations
     */
    public VolunteerMenu(VolunteerController volunteerController) {
        this.volunteerController = volunteerController;
    }

    /**
     * Returns a map of CLI menu options available for volunteers.
     *
     * @return a map of menu keys to {@link MenuOption} instances
     */
    public Map<String, MenuOption> getMenu() {
        Map<String, MenuOption> volunteerMenu = new LinkedHashMap<>();
        volunteerMenu.put("1", new MenuOption("Register Volunteer", volunteerController::registerVolunteer));
        volunteerMenu.put("2", new MenuOption("Add Task to Volunteer", volunteerController::addTask));
        // Future enhancement: add "view tasks", "remove tasks", etc.
        volunteerMenu.put("0", new MenuOption("Return to Main Menu", () -> {}));
        return volunteerMenu;
    }
}

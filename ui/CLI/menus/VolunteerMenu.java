package ui.CLI.menus;

import controllers.VolunteerController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

public class VolunteerMenu {
    private final VolunteerController volunteerController;

    public VolunteerMenu(VolunteerController volunteerController) {
        this.volunteerController = volunteerController;
    }

    public Map<String, MenuOption> getMenu() {
        Map<String, MenuOption> volunteerMenu = new LinkedHashMap<>();
        volunteerMenu.put("1", new MenuOption("Register Volunteer", volunteerController::registerVolunteer));
        volunteerMenu.put("2", new MenuOption("Add Task to Volunteer", volunteerController::addTask));
        // Optional: Add more options like view tasks, remove tasks, etc.
        volunteerMenu.put("0", new MenuOption("Return to Main Menu", () -> {}));
        return volunteerMenu;
    }
}

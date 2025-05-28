package ui.CLI.menus;

import controllers.AdminController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdminMenu {
    private final AdminController adminController;

    public AdminMenu(AdminController adminController) {
        this.adminController = adminController;
    }

    public Map<String, MenuOption> getMenu() {
        Map<String, MenuOption> menu = new LinkedHashMap<>();
        menu.put("1", new MenuOption("Add Animal", adminController::addAnimal));
        menu.put("2", new MenuOption("List Animals", adminController::listAnimals));
        menu.put("3", new MenuOption("Find Animal by Name", adminController::findByName));
        menu.put("4", new MenuOption("Find Animal by ID", adminController::findById));
        menu.put("5", new MenuOption("Find Animals by Species", adminController::findBySpecies));
        menu.put("6", new MenuOption("Sort Animals", adminController::sortAnimals));
        menu.put("7", new MenuOption("Remove Animal", adminController::removeAnimal));
        menu.put("8", new MenuOption("Adopt Animal", adminController::adoptAnimal));
        menu.put("9", new MenuOption("Clear Adoption Queue", adminController::clearQueue));
        menu.put("10", new MenuOption("Peek Next in Queue", adminController::peekNextAnimal));
        menu.put("0", new MenuOption("Return to Main Menu", () -> {}));
        return menu;
    }
}

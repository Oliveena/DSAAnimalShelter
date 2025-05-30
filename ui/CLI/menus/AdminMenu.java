package ui.CLI.menus;

import controllers.AdminController;
import controllers.AdoptionController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the command-line interface menu for administrative tasks in the animal shelter system.
 * <p>
 * Provides options for managing animals, searching, sorting, removing, and initiating adoptions.
 * Includes access to a dedicated Adoption submenu.
 */
public class AdminMenu {

    private final AdminController adminController;
    private final AdoptionSubMenu adoptionSubMenu;

    /**
     * Constructs an AdminMenu with the given controllers.
     *
     * @param adminController    the controller responsible for admin-related actions
     * @param adoptionController the controller responsible for adoption-related actions
     */
    public AdminMenu(AdminController adminController, AdoptionController adoptionController) {
        this.adminController = adminController;
        this.adoptionSubMenu = new AdoptionSubMenu(adoptionController);
    }

    /**
     * Returns the menu options available in the admin menu.
     * <p>
     * Each menu option is mapped by a string key to a {@link MenuOption} which
     * encapsulates the option's description and its action.
     *
     * @return a map of option keys to their corresponding menu options
     */
    public Map<String, MenuOption> getMenu() {
        Map<String, MenuOption> menu = new LinkedHashMap<>();
        menu.put("1", new MenuOption("Add Animal", adminController::addAnimal));
        menu.put("2", new MenuOption("List Animals", adminController::listAnimals));
        menu.put("3", new MenuOption("Find Animal by Name", adminController::findByName));
        menu.put("4", new MenuOption("Find Animal by ID", adminController::findById));
        menu.put("5", new MenuOption("Find Animals by Species", adminController::findBySpecies));
        menu.put("6", new MenuOption("Sort Animals", adminController::sortAnimals));
        menu.put("7", new MenuOption("Remove Animal", adminController::removeAnimal));
        menu.put("8", new MenuOption("Adopt Animal", adoptionSubMenu::show));
        menu.put("9", new MenuOption("Clear Adoption Queue", adminController::clearQueue));
        menu.put("10", new MenuOption("Peek Next in Queue", adminController::peekNextAnimal));
        menu.put("0", new MenuOption("Return to Main Menu", () -> {}));
        return menu;
    }
}

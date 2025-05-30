package ui.CLI.menus;

import controllers.AdoptionController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a command-line submenu for adoption-related operations in the animal shelter system.
 * <p>
 * Provides options for adopting animals based on FIFO (first-in-first-out),
 * preference-based criteria, and viewing current animals in the shelter.
 * This submenu is typically accessible from the Admin menu.
 */
public class AdoptionSubMenu {

    private final AdoptionController adoptionController;

    /**
     * Constructs a new {@code AdoptionSubMenu} with the given controller.
     *
     * @param adoptionController the controller responsible for handling adoption operations
     */
    public AdoptionSubMenu(AdoptionController adoptionController) {
        this.adoptionController = adoptionController;
    }

    /**
     * Displays the adoption submenu and handles user input for various adoption options.
     * <p>
     * The submenu runs in a loop until the user chooses to return to the main admin menu.
     */
    public void show() {
        Scanner scanner = new Scanner(System.in);
        Map<String, MenuOption> menu = new LinkedHashMap<>();

        menu.put("1", new MenuOption("Adopt Animal of the Month (FIFO)", adoptionController::adoptAnimalOfTheMonth));
        menu.put("2", new MenuOption("Preference-Based Adoption", adoptionController::preferenceBasedAdoption));
        menu.put("3", new MenuOption("List Animals in Shelter", adoptionController::listAnimals));
        menu.put("0", new MenuOption("Return to Admin Menu", () -> {}));

        while (true) {
            System.out.println("\n--- Adoption Menu ---");
            menu.forEach((key, option) -> System.out.println(key + ". " + option.getDescription()));

            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            MenuOption selected = menu.get(choice);
            if (selected != null) {
                if ("0".equals(choice)) break;
                selected.execute();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

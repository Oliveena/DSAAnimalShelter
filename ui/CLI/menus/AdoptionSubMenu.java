package ui.CLI.menus;

import controllers.AdoptionController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AdoptionSubMenu {
    private final AdoptionController adoptionController;

    public AdoptionSubMenu(AdoptionController adoptionController) {
        this.adoptionController = adoptionController;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        Map<String, MenuOption> menu = new LinkedHashMap<>();

        menu.put("1", new MenuOption("Adopt Animal of the Month (FIFO)", () -> adoptionController.adoptAnimalOfTheMonth()));
        menu.put("2", new MenuOption("Preference-Based Adoption", () -> adoptionController.preferenceBasedAdoption()));
        menu.put("3", new MenuOption("List Animals in Shelter", () -> adoptionController.listAnimals()));
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
            }
        }
    }
}

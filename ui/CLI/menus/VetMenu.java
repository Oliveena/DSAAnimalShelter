package ui.CLI.menus;

import controllers.VetController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Defines the veterinarian-specific command-line menu options within the shelter system.
 * <p>
 * Provides access to veterinary operations such as updating and viewing medical records.
 * Each menu option is linked to a corresponding action in the {@link VetController}.
 */
public class VetMenu {

    private final VetController vetController;

    /**
     * Constructs a {@code VetMenu} using the given {@link VetController}.
     *
     * @param vetController the controller handling veterinarian-related logic
     */
    public VetMenu(VetController vetController) {
        this.vetController = vetController;
    }

    /**
     * Returns a map of menu options for veterinarian operations.
     * <p>
     * Each key corresponds to a CLI input and maps to a {@link MenuOption}.
     *
     * @return a menu map for veterinarian options
     */
    public Map<String, MenuOption> getMenu() {
        Map<String, MenuOption> vetMenu = new LinkedHashMap<>();
        vetMenu.put("1", new MenuOption("Update Medical Record", vetController::updateMedicalRecord));
        vetMenu.put("2", new MenuOption("View Medical Record", vetController::viewMedicalRecord));
        vetMenu.put("0", new MenuOption("Return to Main Menu", () -> {}));
        return vetMenu;
    }
}

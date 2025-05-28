package ui.CLI.menus;

import controllers.VetController;
import ui.CLI.MenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

public class VetMenu {
    private final VetController vetController;

    public VetMenu(VetController vetController) {
        this.vetController = vetController;
    }

    public Map<String, MenuOption> getMenu() {
        Map<String, MenuOption> vetMenu = new LinkedHashMap<>();
        vetMenu.put("1", new MenuOption("Update Medical Record", vetController::updateMedicalRecord));
        vetMenu.put("2", new MenuOption("View Medical Record", vetController::viewMedicalRecord));
        vetMenu.put("0", new MenuOption("Return to Main Menu", () -> {}));
        return vetMenu;
    }
}

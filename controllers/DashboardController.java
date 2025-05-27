package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.Animal;
import ui.ShelterApp;
import java.util.List;

public class DashboardController {

    private final ShelterApp shelterApp = new ShelterApp();

    @FXML
    private void handleAddAnimal() {
        showInfo("Add Animal", "Add Animal form.");
    }

    @FXML
    private void handleListAnimals() {
        List<Animal> animals = shelterApp.getRegistry().getAllAnimals();
        if (animals.isEmpty()) {
            showInfo("List Animals", "No animals currently in the shelter.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Animal animal : animals) {
            sb.append(animal.getDetails()).append("\n");
        }

        showInfo("Animals in Shelter", sb.toString());
    }

    @FXML
    private void handleAdoptAnimal() {
        shelterApp.adoptAnimal();  // Uses your existing logic
    }

    @FXML
    private void handleSearchAnimal() {
        showInfo("Search Animal", "This would open a search dialog.");
    }

    @FXML
    private void handleRemoveAnimal() {
        showInfo("Remove Animal", "This would open a remove-by-ID dialog.");
    }

    @FXML
    private void handleSortAnimals() {
        showInfo("Sort Animals", "This would allow sort options (name, age).");
    }

    @FXML
    private void handleVolunteerManagement() {
        showInfo("Volunteers", "Volunteer Page");
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void showInfo(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

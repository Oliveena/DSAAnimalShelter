package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import services.AdoptionService;
import services.AnimalService;
import services.VolunteerService;
import ui.CLI.ShelterApp;

import java.io.IOException;
import java.util.List;


public class DashboardController {


    private final ShelterApp shelterApp = ShelterApp.getInstance();
    private final AnimalController animalController = shelterApp.getAnimalController();
    private final AdoptionController adoptionController = shelterApp.getAdoptionController();
    private final VolunteerController volunteerController = shelterApp.getVolunteerController();


    @FXML
    private void handleAddAnimal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add_animal.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Add Animal");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListAnimals() {
        animalController.listAnimals();
    }

    @FXML
    private void handleAdoptAnimal() {
        adoptionController.adoptAnimal();
    }

    @FXML
    private void handleSearchAnimal() {
        ChoiceDialog<String> typeDialog = new ChoiceDialog<>("Name", List.of("Name", "ID", "Species"));
        typeDialog.setTitle("Search Animal");
        typeDialog.setHeaderText("Choose search type:");
        typeDialog.setContentText("Search by:");

        typeDialog.showAndWait().ifPresent(searchType -> {
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setTitle("Search Animal");
            inputDialog.setHeaderText("Enter " + searchType + ":");
            inputDialog.setContentText(searchType + ":");

            inputDialog.showAndWait().ifPresent(input -> {
                switch (searchType) {
                    case "Name" -> animalController.findAnimalByName();
                    case "ID" -> animalController.findAnimalById();
                    case "Species" -> animalController.findAnimalsBySpecies();
                    default -> showInfo("Invalid", "Unsupported search type.");
                }
            });
        });
    }


    @FXML
    private void handleRemoveAnimal() {
       animalController.removeAnimal();
    }

    @FXML
    private void handleSortAnimals() {
        animalController.sortAnimals();
    }

    @FXML
    private void handleRegisterVolunteer() {
        volunteerController.registerVolunteer();
    }

    @FXML
    private void handleAddTask() {
        volunteerController.addTask();
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

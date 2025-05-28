package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import models.Animal;
import services.AdoptionService;
import services.AnimalService;
import services.VolunteerService;
import ui.ShelterApp;

import java.io.IOException;
import java.util.List;


public class DashboardController {

    private final ShelterApp shelterApp = new ShelterApp(); // shared controller logic

    private final AnimalService animalService = shelterApp.getAnimalService();
    private final AdoptionService adoptionService = shelterApp.getAdoptionService();
    private final VolunteerService volunteerService = shelterApp.getVolunteerService();

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
        animalService.listAnimals();
    }

    @FXML
    private void handleAdoptAnimal() {
        adoptionService.adoptAnimal();
    }

    @FXML
    private void handleSearchAnimal() {
       animalService.searchAnimal();
    }

    @FXML
    private void handleRemoveAnimal() {
       animalService.removeAnimal();
    }

    @FXML
    private void handleSortAnimals() {
        animalService.sortAnimals();
    }

    @FXML
    private void handleRegisterVolunteer() {
        volunteerService.registerVolunteer();
    }

    @FXML
    private void handleAddTask() {
        volunteerService.addTask();
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

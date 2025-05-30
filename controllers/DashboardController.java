package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import models.animals.Animal;
import ui.CLI.ShelterApp;

import java.util.List;

/**
 * Controller for the main dashboard view in the JavaFX interface.
 * Provides GUI access to major features of the animal shelter application,
 * including animal management, adoption, and volunteer coordination.
 */
public class DashboardController {

    private final ShelterApp shelterApp = ShelterApp.getInstance();
    private final AnimalController animalController = shelterApp.getAnimalController();
    private final AdoptionController adoptionController = shelterApp.getAdoptionController();
    private final VolunteerController volunteerController = shelterApp.getVolunteerController();

    /**
     * Opens a new window for adding an animal using a separate FXML view.
     */
    @FXML
    private void handleAddAnimal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/javaFX/views/add_animal.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Add Animal");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the list of animals currently in the shelter.
     */
    @FXML
    private void handleListAnimals() {
        animalController.listAnimals();
    }

    /**
     * Allows the user to choose an adoption method from a dialog and performs the corresponding action.
     */
    @FXML
    private void handleAdoptAnimal() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Adopt Animal of the Month (FIFO)",
                List.of("Adopt Animal of the Month (FIFO)", "Preference-Based Adoption", "Manual Adoption"));

        dialog.setTitle("Adopt Animal");
        dialog.setHeaderText("Choose adoption method:");
        dialog.setContentText("Select option:");

        dialog.showAndWait().ifPresent(choice -> {
            switch (choice) {
                case "Adopt Animal of the Month (FIFO)" -> adoptionController.adoptAnimalOfTheMonth();
                case "Preference-Based Adoption" -> adoptionController.preferenceBasedAdoption();
                // TODO: implement manual adoption through GUI
                // case "Manual Adoption" -> adoptionController.adoptAnimal();
                default -> showInfo("Invalid Selection", "Please select a valid adoption option.");
            }
        });
    }

    /**
     * Prompts the user to search for animals by name, ID, or species.
     * Opens input dialogs and delegates the search to the AnimalController.
     */
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

    /**
     * Removes an animal from the shelter registry.
     */
    @FXML
    private void handleRemoveAnimal() {
        animalController.removeAnimal();
    }

    /**
     * Allows the user to sort animals based on criteria like name or age.
     */
    @FXML
    private void handleSortAnimals() {
        animalController.sortAnimals();
    }

    /**
     * Registers a new volunteer using the VolunteerController.
     */
    @FXML
    private void handleRegisterVolunteer() {
        volunteerController.registerVolunteer();
    }

    /**
     * Allows the user to add a new volunteer task.
     */
    @FXML
    private void handleAddTask() {
        volunteerController.addTask();
    }

    /**
     * Exits the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * Displays a simple information dialog with the given title and content.
     *
     * @param title   The title of the alert dialog.
     * @param content The content/message to be displayed.
     */
    private void showInfo(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
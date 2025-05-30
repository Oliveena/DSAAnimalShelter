package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import services.AnimalService;
import ui.CLI.ShelterApp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controller for the Add Animal form in the Shelter Application UI.
 * <p>
 * This controller handles form logic for adding animals of various types
 * (Dog, Cat, Bird, Lizard) with dynamic input fields shown based on the selected type.
 * It validates input, submits data asynchronously via {@link AnimalService},
 * and provides feedback to the user.
 * </p>
 */
public class AddAnimalController {

    // Common animal info
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private ChoiceBox<String> typeChoiceBox;

    // Dog-specific fields
    @FXML private VBox dogFields;
    @FXML private TextField breedField;
    @FXML private CheckBox trainedCheckBox;

    // Cat-specific fields
    @FXML private VBox catFields;
    @FXML private TextField furLengthField;
    @FXML private CheckBox indoorCheckBox;

    // Bird-specific fields
    @FXML private VBox birdFields;
    @FXML private CheckBox canFlyCheckBox;

    // Lizard-specific fields
    @FXML private VBox lizardFields;
    @FXML private CheckBox venomousCheckBox;

    /**
     * Service to manage animal-related operations.
     */
    private final AnimalService animalService = ShelterApp.getInstance().getAnimalService();

    /**
     * Single-threaded executor for background task execution.
     */
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Initializes the controller.
     * <p>
     * Sets up the visibility logic for animal type-specific form sections
     * based on the selection in the {@code typeChoiceBox}.
     * </p>
     */
    @FXML
    public void initialize() {
        dogFields.setVisible(false);
        catFields.setVisible(false);
        birdFields.setVisible(false);
        lizardFields.setVisible(false);

        typeChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            dogFields.setVisible(false);
            catFields.setVisible(false);
            birdFields.setVisible(false);
            lizardFields.setVisible(false);

            if ("Dog".equalsIgnoreCase(newVal)) dogFields.setVisible(true);
            else if ("Cat".equalsIgnoreCase(newVal)) catFields.setVisible(true);
            else if ("Bird".equalsIgnoreCase(newVal)) birdFields.setVisible(true);
            else if ("Lizard".equalsIgnoreCase(newVal)) lizardFields.setVisible(true);
        });
    }

    /**
     * Handles the action of adding a new animal based on form inputs.
     * <p>
     * Validates input, gathers additional fields based on animal type,
     * and submits data to the backend asynchronously.
     * </p>
     */
    @FXML
    private void handleAdd() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String type = typeChoiceBox.getValue();

        if (name.isEmpty() || ageText.isEmpty() || type == null) {
            showAlert("Missing Fields", "Please complete all required fields.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age < 0 || age > 30) {
                showAlert("Invalid Age", "Please enter an age between 0 and 30.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Age", "Age must be a number.");
            return;
        }

        Map<String, String> extras = switch (type.toLowerCase()) {
            case "dog" -> Map.of(
                    "breed", breedField.getText().trim(),
                    "trained", String.valueOf(trainedCheckBox.isSelected())
            );
            case "cat" -> Map.of(
                    "furLength", furLengthField.getText().trim(),
                    "indoor", String.valueOf(indoorCheckBox.isSelected())
            );
            case "bird" -> Map.of("canFly", String.valueOf(canFlyCheckBox.isSelected()));
            case "lizard" -> Map.of("venomous", String.valueOf(venomousCheckBox.isSelected()));
            default -> Map.of();
        };

        executor.submit(() -> {
            try {
                animalService.addAnimalFromUI(name, age, type, extras);

                javafx.application.Platform.runLater(() -> {
                    showAlert("Success", "Animal added successfully!");
                    clearForm();
                });
            } catch (Exception e) {
                javafx.application.Platform.runLater(() ->
                        showAlert("Error", "Failed to add animal: " + e.getMessage()));
            }
        });
    }

    /**
     * Clears the form fields and resets the UI.
     */
    private void clearForm() {
        nameField.clear();
        ageField.clear();
        breedField.clear();
        trainedCheckBox.setSelected(false);
        furLengthField.clear();
        indoorCheckBox.setSelected(false);
        canFlyCheckBox.setSelected(false);
        venomousCheckBox.setSelected(false);
        typeChoiceBox.getSelectionModel().clearSelection();

        dogFields.setVisible(false);
        catFields.setVisible(false);
        birdFields.setVisible(false);
        lizardFields.setVisible(false);
    }

    /**
     * Displays an informational alert dialog to the user.
     *
     * @param title The title of the alert.
     * @param msg   The message content of the alert.
     */
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Shuts down the background executor service.
     * <p>
     * Should be called when the controller is being disposed of.
     * </p>
     */
    public void shutdown() {
        executor.shutdown();
    }
}


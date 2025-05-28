package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import services.AnimalService;
import ui.CLI.ShelterApp;

import java.util.HashMap;
import java.util.Map;

public class AddAnimalController {

    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private ChoiceBox<String> typeChoiceBox;

    @FXML private VBox dogFields;
    @FXML private TextField breedField;
    @FXML private CheckBox trainedCheckBox;

    @FXML private VBox catFields;
    @FXML private TextField furLengthField;
    @FXML private CheckBox indoorCheckBox;

    @FXML private VBox birdFields;
    @FXML private CheckBox canFlyCheckBox;

    @FXML private VBox lizardFields;
    @FXML private CheckBox venomousCheckBox;

    private final AnimalService animalService = ShelterApp.getInstance().getAnimalService();

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

        Map<String, String> extras = new HashMap<>();

        switch (type.toLowerCase()) {
            case "dog" -> {
                extras.put("breed", breedField.getText().trim());
                extras.put("trained", String.valueOf(trainedCheckBox.isSelected()));
            }
            case "cat" -> {
                extras.put("furLength", furLengthField.getText().trim());
                extras.put("indoor", String.valueOf(indoorCheckBox.isSelected()));
            }
            case "bird" -> {
                extras.put("canFly", String.valueOf(canFlyCheckBox.isSelected()));
            }
            case "lizard" -> {
                extras.put("venomous", String.valueOf(venomousCheckBox.isSelected()));
            }
        }

        try {
            animalService.addAnimalFromUI(name, age, type, extras);
            showAlert("Success", "Animal added successfully!");
            clearForm();
        } catch (Exception e) {
            showAlert("Error", "Failed to add animal: " + e.getMessage());
        }
    }

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

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

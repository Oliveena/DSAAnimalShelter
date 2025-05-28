package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Animal;
import services.AnimalService;
import ui.ShelterApp;

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

    private final AnimalService animalService = new ShelterApp().getAnimalService();

    @FXML
    public void initialize() {
        dogFields.setVisible(false);
        catFields.setVisible(false);

        typeChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("Dog".equalsIgnoreCase(newVal)) {
                dogFields.setVisible(true);
                catFields.setVisible(false);
            } else if ("Cat".equalsIgnoreCase(newVal)) {
                dogFields.setVisible(false);
                catFields.setVisible(true);
            } else {
                dogFields.setVisible(false);
                catFields.setVisible(false);
            }
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

        if ("Dog".equalsIgnoreCase(type)) {
            extras.put("breed", breedField.getText().trim());
            extras.put("trained", String.valueOf(trainedCheckBox.isSelected()));
        } else if ("Cat".equalsIgnoreCase(type)) {
            extras.put("furLength", furLengthField.getText().trim());
            extras.put("indoor", String.valueOf(indoorCheckBox.isSelected()));
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
        typeChoiceBox.getSelectionModel().clearSelection();
        dogFields.setVisible(false);
        catFields.setVisible(false);
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

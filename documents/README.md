# 🐾 Animal Shelter Management System

A Java-based application for managing an animal shelter — tracking animals, facilitating adoptions, maintaining medical records, and organizing the adoption queue.

This system allows shelter staff to register animals (e.g., dogs, cats, birds, lizards), manage adoptions using various behavioral strategies, and maintain vaccination and return records — all with an extendable, object-oriented design.

---

## Authors

Anastassia Tarassova
Dang Huynh Minh 

for 

Data Structures and Algorithms course

---

## ✨ Features

- **Add Animal** — Register a new animal with details like name, species, age, and breed-specific attributes.
- **List Animals** — Display all animals currently in the shelter.
- **Search by Name** — Find animals by partial or full name (case-insensitive).
- **Adopt Animal** — Adopt the next animal in the FIFO queue or using a matching strategy.
- **Remove by ID** — Remove an animal from the registry using its unique ID.
- **Sort Animals** — Sort animals alphabetically or by age.
- **Manage Adoption Queue** — Preview, clear, and manage the FIFO-based adoption queue.
- **Track Medical Records** — Attach and view vaccination and medical records.
- **Return to Shelter** — Record animals returned with reasoning.

---

## 🛠️ Technologies Used

- **Java 17+**
- **IntelliJ IDEA** (recommended)
- **Maven** for build automation
- **JUnit** (planned for future test coverage)
- **JavaFX**

---

## 📦 Project Structure



--- 

### Technologies Used

Java: The core language used for the project.

IntelliJ IDEA: IDE used for development.

We attempted to use: 
JUnit: Used for unit testing (for testing methods such as adding animals, searching, etc.).
Maven: Used for dependency management.


---

### Setup Instructions

Ensure you have the following installed:

Java 8+ (JDK)

IntelliJ IDEA (or any other Java IDE)

---

### Clone the Repository
Clone this repository to your local machine:

```git clone https://github.com/Oliveena/DSAAnimalShelter.git```
```cd animal-shelter-management```
Import into IntelliJ
Open IntelliJ IDEA.

Select File > Open... and navigate to the project directory.

IntelliJ will automatically recognize the project structure and offer to import Maven dependencies.

---

### Build and Run the Project
If you're using Maven, you can build the project via the terminal:


```mvn clean install```

To run the project, simply use the Run feature in your IDE or run:


```mvn exec:java```

---

Running Tests
You can run the JUnit tests to ensure everything is working properly. If you're using Maven, you can execute:

mvn test
Alternatively, you can run the tests directly from your IDE using the built-in support for JUnit.

---

### Usage
Once the application is running, it will display a menu with various options, including:

***Add Animal***: Adds a new animal to the shelter.

***List Animals***: Displays all animals in the shelter.

***Adopt Animal***: Adopts the first animal in the adoption queue (FIFO).

***Search Animal***: Searches for animals by name.

***Remove Animal***: Removes an animal by its ID.

***Preview Next Animal***: Peeks at the next animal to be adopted.

***Clear Adoption Queue***: Clears the adoption queue.

***Sort Animals***: Sorts animals by name or age.

***Exit***: Exits the application.

---

### Folder Structure
```
DSAAnimalShelter
├─ .idea
│  ├─ git_toolbox_prj.xml
│  ├─ libraries
│  │  ├─ javafx_sdk_21.xml
│  │  ├─ lib.xml
│  │  └─ lib1.xml
│  ├─ misc.xml
│  ├─ modules.xml
│  ├─ shelf
│  │  ├─ Changes
│  │  │  ├─ AnimalRegistry.class
│  │  │  └─ shelved.patch
│  │  ├─ Changes.xml
│  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m__[Changes]
│  │  │  ├─ AnimalRegistryTest.class
│  │  │  ├─ ShelterApp.class
│  │  │  ├─ ShelterMenu.class
│  │  │  └─ shelved.patch
│  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m__[Changes]1
│  │  │  ├─ AnimalRegistryTest.class
│  │  │  ├─ ShelterApp.class
│  │  │  ├─ ShelterMenu.class
│  │  │  └─ shelved.patch
│  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m___Changes_.xml
│  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m___Changes_1.xml
│  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_39_a_m__[Changes]
│  │  │  └─ shelved.patch
│  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_39_a_m___Changes_.xml
│  │  ├─ Uncommitted_changes_before_Update_at_2025-05-29_9_18_a_m__[Changes]
│  │  │  ├─ AddAnimalController.class
│  │  │  ├─ AdminController.class
│  │  │  ├─ AdoptionController.class
│  │  │  ├─ AnimalController.class
│  │  │  ├─ DashboardController.class
│  │  │  ├─ ShelterQueue.class
│  │  │  └─ shelved.patch
│  │  └─ Uncommitted_changes_before_Update_at_2025-05-29_9_18_a_m___Changes_.xml
│  ├─ uiDesigner.xml
│  ├─ vcs.xml
│  └─ workspace.xml
├─ app
│  └─ Main.java
├─ Assignment1 - Copy.iml
├─ controllers
│  ├─ AddAnimalController.java
│  ├─ AdminController.java
│  ├─ AdoptionController.java
│  ├─ AnimalController.java
│  ├─ DashboardController.java
│  ├─ MedicalRecordController.java
│  ├─ VetController.java
│  └─ VolunteerController.java
├─ documents
│  ├─ concept-design.txt
│  ├─ diagrams
│  │  ├─ addAnimal.drawio.png
│  │  ├─ adoptAnimal.drawio.png
│  │  ├─ removeAnimal.drawio.png
│  │  ├─ searchAnimal.drawio.png
│  │  └─ sortAnimals.drawio.png
│  ├─ Pseudocodes.txt
│  └─ README.md
├─ models
│  ├─ Adoptable.java
│  ├─ Adopter.java
│  ├─ AdopterPreferences.java
│  ├─ AnimalRegistry.java
│  ├─ animals
│  │  ├─ Animal.java
│  │  ├─ Bird.java
│  │  ├─ Cat.java
│  │  ├─ Dog.java
│  │  ├─ Lizard.java
│  │  └─ Species.java
│  ├─ employees
│  │  ├─ Employee.java
│  │  ├─ Vet.java
│  │  └─ Volunteer.java
│  ├─ forms
│  │  ├─ AdoptionForm.java
│  │  ├─ AdoptionFormInstance.java
│  │  ├─ ReturnToShelterForm.java
│  │  └─ ReturnToShelterFormInstance.java
│  ├─ MedicalRecord.java
│  ├─ ShelterQueue.java
│  ├─ Task.java
│  └─ Treatable.java
├─ out
│  └─ production
│     └─ Assignment1 - Copy
│        ├─ .idea
│        │  ├─ git_toolbox_prj.xml
│        │  ├─ libraries
│        │  │  ├─ javafx_sdk_21.xml
│        │  │  ├─ lib.xml
│        │  │  └─ lib1.xml
│        │  ├─ misc.xml
│        │  ├─ modules.xml
│        │  ├─ shelf
│        │  │  ├─ Changes
│        │  │  │  └─ shelved.patch
│        │  │  ├─ Changes.xml
│        │  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m__[Changes]
│        │  │  │  └─ shelved.patch
│        │  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m__[Changes]1
│        │  │  │  └─ shelved.patch
│        │  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m___Changes_.xml
│        │  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_38_a_m___Changes_1.xml
│        │  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_39_a_m__[Changes]
│        │  │  │  └─ shelved.patch
│        │  │  ├─ Uncommitted_changes_before_Checkout_at_2025-05-28_9_39_a_m___Changes_.xml
│        │  │  ├─ Uncommitted_changes_before_Update_at_2025-05-29_9_18_a_m__[Changes]
│        │  │  │  └─ shelved.patch
│        │  │  └─ Uncommitted_changes_before_Update_at_2025-05-29_9_18_a_m___Changes_.xml
│        │  ├─ uiDesigner.xml
│        │  ├─ vcs.xml
│        │  └─ workspace.xml
│        ├─ app
│        │  └─ Main.class
│        ├─ Assignment1 - Copy.iml
│        ├─ controllers
│        │  ├─ AddAnimalController.class
│        │  ├─ AdminController.class
│        │  ├─ AdoptionController.class
│        │  ├─ AnimalController.class
│        │  ├─ DashboardController.class
│        │  ├─ MedicalRecordController.class
│        │  ├─ VetController.class
│        │  └─ VolunteerController.class
│        ├─ documents
│        │  ├─ concept-design.txt
│        │  ├─ diagrams
│        │  │  ├─ addAnimal.drawio.png
│        │  │  ├─ adoptAnimal.drawio.png
│        │  │  ├─ removeAnimal.drawio.png
│        │  │  ├─ searchAnimal.drawio.png
│        │  │  └─ sortAnimals.drawio.png
│        │  ├─ Pseudocodes.txt
│        │  └─ README.md
│        ├─ models
│        │  ├─ Adoptable.class
│        │  ├─ Adopter.class
│        │  ├─ AdopterPreferences.class
│        │  ├─ AnimalRegistry.class
│        │  ├─ animals
│        │  │  ├─ Animal.class
│        │  │  ├─ Bird.class
│        │  │  ├─ Cat.class
│        │  │  ├─ Dog.class
│        │  │  ├─ Lizard.class
│        │  │  └─ Species.class
│        │  ├─ employees
│        │  │  ├─ Employee.class
│        │  │  ├─ Vet.class
│        │  │  └─ Volunteer.class
│        │  ├─ forms
│        │  │  ├─ AdoptionForm.class
│        │  │  ├─ AdoptionFormInstance.class
│        │  │  ├─ ReturnToShelterForm.class
│        │  │  └─ ReturnToShelterFormInstance.class
│        │  ├─ MedicalRecord.class
│        │  ├─ ShelterQueue.class
│        │  ├─ Task.class
│        │  └─ Treatable.class
│        ├─ patterns
│        │  ├─ behavioral
│        │  │  ├─ observer
│        │  │  │  ├─ VolunteerManager.class
│        │  │  │  ├─ VolunteerObserver.class
│        │  │  │  └─ VolunteerSubject.class
│        │  │  └─ strategies
│        │  │     ├─ AnimalMatchingStrategy.class
│        │  │     └─ PreferenceMatchingStrategy.class
│        │  ├─ creational
│        │  │  ├─ builders
│        │  │  │  ├─ AnimalBuilder.class
│        │  │  │  ├─ BirdBuilder.class
│        │  │  │  ├─ CatBuilder.class
│        │  │  │  ├─ DogBuilder.class
│        │  │  │  ├─ LizardBuilder.class
│        │  │  │  └─ MedicalRecordBuilder.class
│        │  │  └─ factories
│        │  │     ├─ AdopterFactory.class
│        │  │     ├─ FormFactory.class
│        │  │     └─ LogFactory.class
│        │  └─ structural
│        │     ├─ decorators
│        │     │  ├─ AnimalDecorator.class
│        │     │  └─ VaccinationDecorator.class
│        │     └─ templates
│        │        ├─ AnimalProcessingTemplate.class
│        │        ├─ AnimalReturnProcessor.class
│        │        ├─ FifoAdoptionProcessor.class
│        │        └─ PreferenceBasedAdoptionProcessor.class
│        ├─ README.md
│        ├─ repository
│        │  └─ AnimalRepository.class
│        ├─ services
│        │  ├─ AdoptionService$FifoMatchingStrategy.class
│        │  ├─ AdoptionService.class
│        │  ├─ AnimalService.class
│        │  ├─ MedicalRecordService.class
│        │  ├─ ShelterService.class
│        │  └─ VolunteerService.class
│        ├─ ui
│        │  ├─ CLI
│        │  │  ├─ MenuOption.class
│        │  │  ├─ menus
│        │  │  │  ├─ AdminMenu.class
│        │  │  │  ├─ AdoptionSubMenu.class
│        │  │  │  ├─ VetMenu.class
│        │  │  │  └─ VolunteerMenu.class
│        │  │  └─ ShelterApp.class
│        │  ├─ FXMain.class
│        │  └─ javaFX
│        │     └─ views
│        │        ├─ add_animal.fxml
│        │        └─ dashboard.fxml
│        └─ util
│           └─ tests
│              ├─ AnimalControllerTest.class
│              ├─ AnimalRegistryTest.class
│              ├─ AppPerformanceTest.class
│              └─ AppStartupPerformanceTest.class
├─ patterns
│  ├─ behavioral
│  │  ├─ observer
│  │  │  ├─ VolunteerManager.java
│  │  │  ├─ VolunteerObserver.java
│  │  │  └─ VolunteerSubject.java
│  │  └─ strategies
│  │     ├─ AnimalMatchingStrategy.java
│  │     └─ PreferenceMatchingStrategy.java
│  ├─ creational
│  │  ├─ builders
│  │  │  ├─ AnimalBuilder.java
│  │  │  ├─ BirdBuilder.java
│  │  │  ├─ CatBuilder.java
│  │  │  ├─ DogBuilder.java
│  │  │  ├─ LizardBuilder.java
│  │  │  └─ MedicalRecordBuilder.java
│  │  └─ factories
│  │     ├─ AdopterFactory.java
│  │     ├─ FormFactory.java
│  │     └─ LogFactory.java
│  └─ structural
│     ├─ decorators
│     │  ├─ AnimalDecorator.java
│     │  └─ VaccinationDecorator.java
│     └─ templates
│        ├─ AnimalProcessingTemplate.java
│        ├─ AnimalReturnProcessor.java
│        ├─ FifoAdoptionProcessor.java
│        └─ PreferenceBasedAdoptionProcessor.java
├─ repository
│  └─ AnimalRepository.java
├─ services
│  ├─ AdoptionService.java
│  ├─ AnimalService.java
│  ├─ MedicalRecordService.java
│  ├─ ShelterService.java
│  └─ VolunteerService.java
├─ ui
│  ├─ CLI
│  │  ├─ MenuOption.java
│  │  ├─ menus
│  │  │  ├─ AdminMenu.java
│  │  │  ├─ AdoptionSubMenu.java
│  │  │  ├─ VetMenu.java
│  │  │  └─ VolunteerMenu.java
│  │  └─ ShelterApp.java
│  ├─ FXMain.java
│  └─ javaFX
│     ├─ controllers
│     └─ views
│        ├─ add_animal.fxml
│        └─ dashboard.fxml
└─ util
   └─ tests
      ├─ AnimalControllerTest.java
      ├─ AnimalRegistryTest.java
      ├─ AppPerformanceTest.java
      └─ AppStartupPerformanceTest.java

```
---

### Contributing
Feel free to fork this project and submit pull requests. When contributing, please ensure the following:

Write unit tests: Ensure new features or bug fixes are covered by tests.

Follow Java naming conventions: Use clear, descriptive names for variables and methods.

Document your code: Add comments and documentation where appropriate.

---

### License
This project is a student project.



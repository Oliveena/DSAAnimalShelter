# Animal Shelter Management System


This is an Animal Shelter Management System built in Java, designed to manage animal adoption in a shelter. The system allows users to add animals, view animal details, search by name, adopt animals in a First-In-First-Out (FIFO) order, and manage the adoption queue.

The system features multiple types of animals (like Dogs, Cats, Birds, Lizards), different adoption patterns.behavioral.strategies, and keeps track of vaccination records.

---

### Features

***Add Animal*** : Add a new animal to the shelter with details like type, name, age, and specific attributes (breed, fur length, etc.).

***List Animals*** : List all animals in the shelter.

***Adopt Animal*** : Adopt the next animal in the adoption queue.

***Search Animal*** : Search for an animal by name.

***Remove Animal*** : Remove an animal from the shelter by its ID.

***Sort Animals*** : Sort animals by name or age.

***Manage Adoption Queue*** : Preview the next animal in the adoption queue, clear the queue, and adopt animals in FIFO order.

***Vaccination Records*** : Track vaccination details for animals.

--- 

### Technologies Used

Java: The core language used for the project.

IntelliJ IDEA: IDE used for development.

We attempted to use: 
JUnit: Used for unit testing (for testing methods such as adding animals, searching, etc.).
Maven: Used for dependency management.
Considering this is not the purpose of the assignment, we chose to keep them for a later iteration .

---

### Setup Instructions

Ensure you have the following installed:

Java 8+ (JDK)

IntelliJ IDEA (or any other Java IDE)

---

### Clone the Repository
Clone this repository to your local machine:

```git clone https://github.com/your-username/animal-shelter-management.git```
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
├── data/                   # Contains classes for managing the registry of animals.
├── domain/                  # Contains core domain classes for different animals, animal types, and adoption.
├── patterns.behavioral.strategies/             # Contains different adoption patterns.behavioral.strategies (e.g., FIFO).
├── ui/                     # Contains the user interface and menu interactions.
├── patterns.creational.builders/               # Contains builder classes for creating animals.
├── resources/              # Contains resources like configuration files or images.
├── test/                   # Contains unit tests.
├── pom.xml                 # Maven project configuration.
└── README.md               # Project documentation (this file).
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


```
DSAAnimalShelter
├─ .idea
│  ├─ git_toolbox_prj.xml
│  ├─ libraries
│  │  └─ lib.xml
│  ├─ misc.xml
│  ├─ modules.xml
│  ├─ uiDesigner.xml
│  ├─ vcs.xml
│  └─ workspace.xml
├─ Assignment1 - Copy.iml
├─ Main.java
├─ models
│  ├─ Adoptable.java
│  ├─ Adopter.java
│  ├─ AdoptionForm.java
│  ├─ Animal.java
│  ├─ AnimalRegistry.java
│  ├─ Bird.java
│  ├─ Cat.java
│  ├─ Dog.java
│  ├─ Lizard.java
│  ├─ MedicalRecord.java
│  ├─ ShelterQueue.java
│  ├─ Task.java
│  └─ Volunteer.java
├─ out
│  └─ production
│     └─ Assignment1 - Copy
│        ├─ .idea
│        │  ├─ git_toolbox_prj.xml
│        │  ├─ libraries
│        │  │  └─ lib.xml
│        │  ├─ misc.xml
│        │  ├─ modules.xml
│        │  ├─ uiDesigner.xml
│        │  ├─ vcs.xml
│        │  └─ workspace.xml
│        ├─ Assignment1 - Copy.iml
│        ├─ diagrams
│        │  ├─ addAnimal.drawio.png
│        │  ├─ adoptAnimal.drawio.png
│        │  ├─ removeAnimal.drawio.png
│        │  ├─ searchAnimal.drawio.png
│        │  └─ sortAnimals.drawio.png
│        ├─ models
│        │  ├─ Adoptable.class
│        │  ├─ Adopter.class
│        │  ├─ AdoptionForm.class
│        │  ├─ Animal.class
│        │  ├─ AnimalRegistry.class
│        │  ├─ Bird.class
│        │  ├─ Cat.class
│        │  ├─ Dog.class
│        │  ├─ Lizard.class
│        │  ├─ MedicalRecord.class
│        │  ├─ ShelterQueue.class
│        │  ├─ Task.class
│        │  └─ Volunteer.class
│        ├─ patterns
│        │  ├─ builders
│        │  │  ├─ AnimalBuilder.class
│        │  │  ├─ BirdBuilder.class
│        │  │  ├─ CatBuilder.class
│        │  │  ├─ DogBuilder.class
│        │  │  ├─ LizardBuilder.class
│        │  │  └─ MedicalRecordBuilder.class
│        │  ├─ decorators
│        │  │  ├─ AnimalDecorator.class
│        │  │  └─ VaccinationDecorator.class
│        │  ├─ factories
│        │  │  └─ AdopterFactory.class
│        │  ├─ observer
│        │  │  ├─ VolunteerManager.class
│        │  │  ├─ VolunteerObserver.class
│        │  │  └─ VolunteerSubject.class
│        │  ├─ strategies
│        │  │  ├─ AdoptionStrategy.class
│        │  │  └─ FIFOAdoptionStrategy.class
│        │  └─ templates
│        │     ├─ AnimalProcessingTemplate.class
│        │     ├─ AnimalReturnProcessor.class
│        │     └─ FifoAdoptionProcessor.class
│        ├─ Pseudocodes.txt
│        ├─ README.md
│        ├─ services
│        │  └─ Shelter.class
│        └─ ui
│           ├─ Main.class
│           ├─ ShelterApp.class
│           └─ ShelterMenu.class
├─ patterns
│  ├─ builders
│  │  ├─ AnimalBuilder.java
│  │  ├─ BirdBuilder.java
│  │  ├─ CatBuilder.java
│  │  ├─ DogBuilder.java
│  │  ├─ LizardBuilder.java
│  │  └─ MedicalRecordBuilder.java
│  ├─ decorators
│  │  ├─ AnimalDecorator.java
│  │  └─ VaccinationDecorator.java
│  ├─ factories
│  │  └─ AdopterFactory.java
│  ├─ observer
│  │  ├─ VolunteerManager.java
│  │  ├─ VolunteerObserver.java
│  │  └─ VolunteerSubject.java
│  ├─ strategies
│  │  ├─ AdoptionStrategy.java
│  │  └─ FIFOAdoptionStrategy.java
│  └─ templates
│     ├─ AnimalProcessingTemplate.java
│     ├─ AnimalReturnProcessor.java
│     └─ FifoAdoptionProcessor.java
├─ Pseudocodes.txt
├─ README.md
├─ repository
│  └─ DB_ACCESSES_WOULD_BE_HERE
├─ services
│  └─ Shelter.java
└─ util
   ├─ diagrams
   │  ├─ addAnimal.drawio.png
   │  ├─ adoptAnimal.drawio.png
   │  ├─ removeAnimal.drawio.png
   │  ├─ searchAnimal.drawio.png
   │  └─ sortAnimals.drawio.png
   ├─ tests
   │  └─ AnimalRegistryTest.java
   └─ ui
      ├─ JAVAFX_SHOULD_PROBABLY_GO_HERE
      ├─ ShelterApp.java
      └─ ShelterMenu.java

```
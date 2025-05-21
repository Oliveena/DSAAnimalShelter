# Animal Shelter Management System


This is an Animal Shelter Management System built in Java, designed to manage animal adoption in a shelter. The system allows users to add animals, view animal details, search by name, adopt animals in a First-In-First-Out (FIFO) order, and manage the adoption queue.

The system features multiple types of animals (like Dogs, Cats, Birds, Lizards), different adoption strategies, and keeps track of vaccination records.

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
├── logic/                  # Contains core logic classes for different animals, animal types, and adoption.
├── strategies/             # Contains different adoption strategies (e.g., FIFO).
├── ui/                     # Contains the user interface and menu interactions.
├── builders/               # Contains builder classes for creating animals.
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


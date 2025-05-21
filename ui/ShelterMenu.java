package ui;

/**
 * A utility class that displays the menu options for the Animal Shelter application.
 * <p>
 * This class contains a single static method, {@code displayMenu()}, which prints the available
 * options for the user to interact with the shelter management system. It serves as the user interface
 * for navigating through different functionalities such as adding, listing, adopting, and removing animals.
 */

public class ShelterMenu {

    /**
     * Displays the main menu options for the shelter application.
     * This method presents various options to the user, such as adding an animal,
     * listing animals, adopting an animal, and more.
     */
    public static void displayMenu() {
        System.out.println("\n--- Animal Shelter Menu ---");
        System.out.println("1. Add Animal");
        System.out.println("2. List Animals");
        System.out.println("3. Adopt Animal");
        System.out.println("4. Search Animal by Name");
        System.out.println("5. Remove Animal");
        System.out.println("6. Preview Next Animal");
        System.out.println("7. Clear AdoptionQueue");
        System.out.println("8. Find Animal by ID");
        System.out.println("9. Sort Animals");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }
}
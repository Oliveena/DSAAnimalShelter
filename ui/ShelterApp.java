package ui;

import java.util.*;

import models.*;

import patterns.strategies.AdoptionStrategy;
import patterns.strategies.FIFOAdoptionStrategy;
import services.AdoptionService;
import services.AnimalService;
import services.Shelter;
import services.VolunteerService;


/**
 * The main application for managing the animal shelter, providing functionality
 * for adding, listing, adopting, and removing animals from the shelter.
 * <p>
 * The app interacts with an animal registry and an adoption queue, providing
 * various functionalities such as adding animals, searching, sorting, and adopting animals.
 * It allows interaction via a command-line interface.
 */
public class ShelterApp {  // now acting as Controller

    private AnimalService animalService;
    private AdoptionService adoptionService;
    private VolunteerService volunteerService;
    private Scanner scanner;

    public ShelterApp() {
        this.scanner = new Scanner(System.in);
        AnimalRegistry registry = new AnimalRegistry();
        ShelterQueue queue = new ShelterQueue();
        Shelter shelter = new Shelter();
        AdoptionStrategy strategy = new FIFOAdoptionStrategy();

        animalService = new AnimalService(registry, queue, shelter, scanner);
        adoptionService = new AdoptionService(strategy, queue,registry);
        volunteerService = new VolunteerService(shelter, scanner);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            ShelterMenu.displayMenu();
            String choice = scanner.nextLine();
            switch(choice) {
                case "1": animalService.addAnimal(); break;
                case "2": animalService.listAnimals(); break;
                case "3": adoptionService.adoptAnimal(); break;
                case "4": animalService.searchAnimal(); break;
                case "5": animalService.removeAnimal(); break;
                case "6": adoptionService.peekNextAnimal(); break;
                case "7": adoptionService.clearQueue(); break;
                case "8": animalService.findAnimalById(); break;
                case "9": animalService.sortAnimals(); break;
                case "10": volunteerService.registerVolunteer(); break;
                case "11": volunteerService.addTask(); break;
                case "0": exit = true; break;
                default: System.out.println("Invalid choice."); break;
            }
        }
    }

    public AnimalService getAnimalService() {
        return animalService;
    }

    public AdoptionService getAdoptionService() {
        return adoptionService;
    }

    public VolunteerService getVolunteerService() {
        return volunteerService;
    }



}


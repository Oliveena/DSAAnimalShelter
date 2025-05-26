/**
 *  PreferenceBasedAdoptionProcessor - Smart Adoption Logic
 *
 * This class handles animal adoption using a preference-based matching algorithm.
 * It utilizes the {@link PreferenceMatcher} utility, which applies Dijkstra-like scoring
 * to select the best match for the adopter based on their preferences.
 *
 * ➤ Primary Use Case:
 *     - Realistic, intelligent adoptions that prioritize compatibility
 *     - Matches adopters to animals using traits such as species, breed, and age
 *
 *  Matching Criteria:
 *     - Species match
 *     - Breed match
 *     - Age within preferred range
 *     - Additional logic can be added for temperament, medical status, etc.
 *
 *  Adoption Flow:
 *     1. Validates adopter and preferences
 *     2. Searches for the best-matching animal using {@link PreferenceMatcher}
 *     3. Verifies animal eligibility
 *     4. Registers the adoption and updates registry
 *     5. Logs the outcome
 *
 *  Comparison:
 *     - This is the **main adoption pathway** for most users.
 *     - For spotlight or promotional adoptions, use {@link FifoAdoptionProcessor}.
 *
 * @see FifoAdoptionProcessor for "Animal of the Month" logic
 */


package patterns.templates;

import models.Adopter;
import models.AnimalRegistry;
import models.animals.Animal;
import util.matching.PreferenceMatcher;

import java.util.List;
import java.util.logging.Logger;

// Preference-based adoption strategy — primary logic using scoring/matching
public class PreferenceBasedAdoptionProcessor extends AnimalProcessingTemplate {

    private final Adopter adopter;
    private final AnimalRegistry registry;
    private Animal adoptedAnimal;

    private final Logger logger = Logger.getLogger(PreferenceBasedAdoptionProcessor.class.getName());

    public PreferenceBasedAdoptionProcessor(Adopter adopter, AnimalRegistry registry) {
        this.adopter = adopter;
        this.registry = registry;
    }

    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
        if (adopter.getPreferences() == null) throw new IllegalArgumentException("Adopter preferences must be set.");
    }

    @Override
    protected Animal findAnimal() {
        List<Animal> availableAnimals = registry.getAllAnimals();
        adoptedAnimal = PreferenceMatcher.findBestMatch(adopter, availableAnimals);
        if (adoptedAnimal == null) throw new IllegalStateException("No matching animals found for preferences.");
        return adoptedAnimal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        // Add eligibility checks here if needed
    }

    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
        registry.removeAnimalById(animal.getId());
    }

    @Override
    protected void logOutcome(Animal animal) {
        logger.info(STR."\{adopter.getName()} adopted animal based on preferences: \{animal.getDetails()}");
    }
}


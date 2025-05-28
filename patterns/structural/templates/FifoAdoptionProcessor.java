/**
 * 📦 FifoAdoptionProcessor - "Animal of the Month" Adoption Logic
 *
 * This class handles adoptions based on the FIFO (First-In, First-Out) principle.
 * It is used specifically for highlighting and adopting the **"Animal of the Month"**,
 * which is the animal that has been in the shelter the longest (front of the queue).
 *
 * ➤ Primary Use Case:
 *     - Promotional or spotlighted adoption
 *     - Prioritizes animals who have been waiting the longest
 *     - Does NOT consider adopter preferences
 *
 * 🔁 Adoption Flow:
 *     1. Validates input adopter
 *     2. Retrieves the next animal in the queue
 *     3. Confirms eligibility (if needed)
 *     4. Processes adoption
 *     5. Logs the outcome
 *
 * 🆚 Comparison:
 *     - This is **different** from {@link PreferenceBasedAdoptionProcessor}, which uses
 *       Dijkstra-like logic to find the best animal match based on adopter preferences.
 *
 * @see PreferenceBasedAdoptionProcessor for user-driven matching
 */


package patterns.structural.templates;

import models.AnimalRegistry;
import models.Adopter;
import models.animals.Animal;
import models.ShelterQueue;
import patterns.behavioral.strategies.FIFOAdoptionStrategy;

import java.util.logging.Logger;

// FIFO-based adoption strategy — used only for "Animal of the Month" spotlight
public class FifoAdoptionProcessor extends AnimalProcessingTemplate {

    private final AnimalRegistry registry;
    private final ShelterQueue queue;
    private final Adopter adopter;
    private Animal adoptedAnimal;

    private final Logger logger = Logger.getLogger(FifoAdoptionProcessor.class.getName());

    public FifoAdoptionProcessor(Adopter adopter, AnimalRegistry registry, ShelterQueue queue) {
        this.adopter = adopter;
        this.registry = registry;
        this.queue = queue;
    }

    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
    }

    @Override
    protected Animal findAnimal() {
        adoptedAnimal = new FIFOAdoptionStrategy().adopt(registry, queue);
        if (adoptedAnimal == null) throw new IllegalStateException("No animals available for adoption.");
        return adoptedAnimal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        // Add custom eligibility checks here if needed
    }

    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
    }

    @Override
    protected void logOutcome(Animal animal) {
        logger.info(adopter.getName() + " adopted animal: " + animal.getName());
    }
}

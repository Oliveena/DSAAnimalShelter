package domain;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a queue for animals awaiting adoption in the shelter.
 * <p>
 * The queue follows the First In First Out (FIFO) principle where animals are added
 * to the end of the queue and adopted (removed) from the front.
 * <p>
 * This class provides methods for adding animals, removing them, checking the size,
 * and clearing the queue.
 */
public class ShelterQueue {
    private Queue<Animal> queue = new LinkedList<>();

    /**
     * Adds an animal to the adoption queue.
     * <p>
     * This method uses the {@link Queue#offer(Object)} method to add the animal
     * to the queue. It does not throw an exception if the queue is full.
     *
     * @param animal the animal to add to the queue
     */
    public void enqueue(Animal animal) {
        queue.offer(animal); // Adds the animal to the queue, returns false if full, doesn't throw an exception
    }

    /**
     * Removes and returns the next animal in the queue for adoption (FIFO).
     * <p>
     * This method uses the {@link Queue#poll()} method to remove and return the
     * animal at the front of the queue. If the queue is empty, it returns null.
     *
     * @return the next animal in the queue, or null if the queue is empty
     */
    public Animal dequeue() {
        return queue.poll(); // Returns null if the queue is empty
    }

    /**
     * Peeks at the next animal in the queue without removing it.
     * <p>
     * This method uses the {@link Queue#peek()} method to check the animal at the
     * front of the queue without removing it. If the queue is empty, it returns null.
     *
     * @return the next animal in the queue, or null if the queue is empty
     */
    public Animal peekNext() {
        return queue.peek(); // Returns null if the queue is empty
    }

    /**
     * Checks if the queue is empty.
     * <p>
     * This method uses the {@link Queue#isEmpty()} method to determine if there are
     * any animals in the queue.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the size of the queue.
     * <p>
     * This method uses the {@link Queue#size()} method to get the number of animals
     * currently in the queue.
     *
     * @return the number of animals in the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * Clears the queue.
     * <p>
     * This method uses the {@link Queue#clear()} method to remove all animals from the queue.
     */
    public void clear() {
        queue.clear();
    }

    /**
     * Adds an animal to the queue.
     * <p>
     * This method is an alias for {@link #enqueue(Animal)} and is provided for clarity.
     *
     * @param animal the animal to add to the queue
     */
    public void addAnimal(Animal animal) {
        enqueue(animal); // Corrected to add animal to the queue
    }
}

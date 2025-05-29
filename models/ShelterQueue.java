package models;

import java.util.LinkedList;
import java.util.Queue;
import models.animals.Animal;



/**
 * Represents a queue of animals waiting for adoption at the shelter.
 * <p>
 * This queue follows the FIFO (First-In, First-Out) model, meaning the animal
 * that has been waiting the longest will be adopted first.
 * <p>
 * Useful in promotional strategies like "Animal of the Month" where priority is
 * given to animals who’ve spent more time in the shelter.
 */
public class ShelterQueue {
    private Queue<Animal> queue = new LinkedList<>();

    /**
     * Adds an animal to the end of the queue.
     *
     * @param animal the animal to enqueue
     */
    public void enqueue(Animal animal) {
        queue.offer(animal);
    }

    /**
     * Removes and returns the animal at the front of the queue.
     *
     * @return the next animal in line, or {@code null} if queue is empty
     */
    public Animal dequeue() {
        return queue.poll();
    }

    /**
     * Peeks at the next animal without removing it from the queue.
     *
     * @return the next animal, or {@code null} if the queue is empty
     */
    public Animal peekNext() {
        return queue.peek();
    }

    /**
     * Returns {@code true} if the queue is empty.
     *
     * @return whether the queue is empty
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the number of animals currently in the queue.
     *
     * @return queue size
     */
    public int size() {
        return queue.size();
    }

    /**
     * Clears all animals from the queue.
     */
    public void clear() {
        queue.clear();
    }

    /**
     * Adds an animal to the queue.
     * Alias for {@link #enqueue(Animal)} provided for semantic clarity.
     *
     * @param animal the animal to add
     */
    public void addAnimal(Animal animal) {
        enqueue(animal);
    }
}

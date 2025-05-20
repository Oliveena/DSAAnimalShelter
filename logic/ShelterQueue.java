package logic;

import java.util.LinkedList;
import java.util.Queue;


public class ShelterQueue {
    private Queue<Animal> queue = new LinkedList<>();

    /**
     * Add animal to the adoption queue
     */
    public void enqueue(Animal animal) {
        queue.offer(animal); // Adds the animal to the queue, returns false if full, doesn't throw an exception
    }

    /**
     * Removes and returns the next animal for adoption (FIFO)
     */
    public Animal dequeue() {
        return queue.poll(); // Returns null if the queue is empty
    }

    /**
     * Peek at the next animal without removing it
     */
    public Animal peekNext() {
        return queue.peek(); // Returns null if the queue is empty
    }

    /**
     * Check if the queue is empty
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Get the size of the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * Clear the queue
     */
    public void clear() {
        queue.clear();
    }

    /**
     * Add animal to the queue (uses enqueue)
     */
    public void addAnimal(Animal animal) {
        enqueue(animal); // Corrected to add animal to the queue
    }
}

package logic;

import java.util.LinkedList;
import java.util.Queue;


public class ShelterQueue {
    private Queue<Animal> queue = new LinkedList<>();

    /**
     * Add animal to the adoption queue
     */
    public void enqueue(Animal animal) {
        queue.offer(animal); // same as add(), but returns false instead of throwing if full
    }

    /**
     * Removes and returns the next animal for adoption (FIFO)
     */
    public Animal dequeue() {
        return queue.poll(); // null if empty
    }

    /**
     * Peek at the next animal without removing
     */
    public Animal peekNext() {
        return queue.peek(); // null if empty
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }
}

package cs113.listGame.ListClasses;

public interface IteratorInterface<E> {
    /**
     * Checks to see if a next element exists
     * @return returns true there is a next
     */
    boolean hasNext();

    /**
     * Advances to the next element
     * Asserts if there is no next
     * @return returns a valid element
     */
    E next();

    /**
     * Removes the 'current' element
     */
    void remove();
}

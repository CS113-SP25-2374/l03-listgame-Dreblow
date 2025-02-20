package cs113.listGame.ListClasses;

public interface ListInterface <E> {
    /**
     * Adds an element to the end of the list
     * @param element
     * @return
     */
    boolean add(E element);

    /**
     * Inserts an element into the list at the index given
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * Clears all elements from the list
     */
    void clear();

    /**
     * Returns true if the element is in the list
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * Finds the index of the object if it exists, otherwise returns -1
     * @param object
     * @return
     */
    int indexOf(Object object);

    /**
     * Returns the element from the index
     * @param index
     * @return
     */
    E get(int index);

    /**
     * Returns true if there are no elements
     * @return
     */
    boolean isEmpty();

    /**
     * Removes the element at the specified index
     * @param index
     * @return
     */
    boolean remove(int index);

    /**
     * Removes the given element if it exists
     * @param element
     * @return
     */
    boolean remove(E element);

    /**
     * Number of elements in the list
     * @return
     */
    int size();

    /**
     * Replace the element at the specified index with the element given
     * @param index
     * @param element
     */
    void set(int index, E element);
}

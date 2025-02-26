package cs113.listGame.ListClasses;

public class LinkedListDAD<E> implements ListInterface<E>, IteratorInterface<E>, IterableInterface<E>  {

    private class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        private Node(E element){
            this.element = element;
        }
    }

    private class LinkedListIterator implements IteratorInterface<E> {
        private Node<E> current = head;
        private Node<E> lastReturned = null; 
    
        @Override
        public boolean hasNext() {
            return current != null;
        }
    
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            lastReturned = current;
            current = current.next; // Move to next node
            return lastReturned.element;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("next() must be called before remove()");
            }
            
            unlink(lastReturned); // Remove the node from the list
            lastReturned = null;  // Prevent multiple removes per `next()`
        }
    }


    private Node<E> current;

    Node<E> head;
    Node<E> tail;
    int size;

    public LinkedListDAD() {
        head = tail = null;
        size = 0;
        current = head;
    }

    @Override
    public boolean add(E element) {
        // Create a new node
        Node<E> node = new Node<>(element);
        size++;

        // Check if empty since single node
        if(head == null) {
            head = tail = node;
            return true;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    
        Node<E> node = new Node<>(element);
        size++;
    
        // Insert at head
        if (index == 0) {
            node.next = head;
            if (head != null) {
                head.prev = node;
            }
            head = node;

            // Set tail if list was empty
            if (size == 0) {
                tail = node; 
            }
        // Insert at tail
        } else if (index == size) {
            tail.next = node;
            node.prev = tail;
            tail = node; 
        // Insert in middle
        } else {
            Node<E> curr = getIndex(index);
            node.next = curr;
            node.prev = curr.prev;
            if (curr.prev != null) {
                curr.prev.next = node;
            }
            curr.prev = node;
        }
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        Node<E> curr = head;
    
        while (curr != null) {
            // ChatGPT says java.util.Objects is safer for null
            if (java.util.Objects.equals(curr.element, element)) {
                return true;
            }
            curr = curr.next;
        }

        // Didn't find
        return false;
    }

    @Override
    public int indexOf(Object object) {
        Node<E> curr = head;
        int index = 0;
    
        while (curr != null) {
            if (curr.element.equals(object)) {
                // Return index if element is found
                return index; 
            }
            curr = curr.next;
            index++;
        }
    
        // Return -1 if element is not found
        return -1; 
    }

    @Override
    public E get(int index) {
        return getIndex(index).element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(int index) {
        if (size == 0 || index < 0 || index >= size) throw new IndexOutOfBoundsException();
        unlink(getIndex(index));
        return true;
    }

    @Override
    public boolean remove(E element) {
        Node<E> curr = head;
    
        while (curr != null) {
            if (curr.element.equals(element)) {
                unlink(curr);
                return true;
            }
            curr = curr.next;
        }
    
        // Element not found
        return false; 
    }

    @Override
    public int size() {
        return size;
    }

    public int length() {
        return size;
    }

    @Override
    public void set(int index, E element) {
        Node<E> curr = getIndex(index);
        curr.element = element;
    }

    @Override
    public boolean hasNext() {
        return current != null && current.next != null;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }

        // Move the iterator forward
        current = current.next; 
        return current.element;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public IteratorInterface<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
    
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.element);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
    
        sb.append("]");
        return sb.toString();
    }

    private Node<E> getIndex(int index) {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException();

        Node<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    private void unlink(Node<E> node) {
        Node<E> curr = node;
        Node<E> prev = curr.prev;
        Node<E> next = curr.next;

        size --;

        if(curr == head) {
            head = curr.next;
        }
        if(curr == tail) {
            tail = curr.prev;
        }
        if(prev != null) {
            prev.next = next;
        }
        if(next != null) {
            next.prev = prev;
        }
    }
    
}


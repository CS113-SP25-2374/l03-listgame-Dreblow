package cs113.listGame.ListClasses;

public class ArrayListDAD<E> implements ListInterface<E> {

    private E[] array;

    final static int DEFAULT_SIZE = 10;

    public ArrayListDAD() {
        array = (E[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean add(E element) {
        if (array[array.length - 1] != null) {
            int length = array.length;
            resize();
            array[length] = element;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = element;
                return true;
            }
        }
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        if (array[array.length - 1] != null) {
            resize();
        } else if (index == array.length - 1) {
            array[array.length - 1] = element;
        }

        for (int i = size(); i >= index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(Object object) {
        if (object == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++){
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = null;
        return true;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        return remove(index);
    }

    @Override
    public int size() {
        for(int i = array.length - 1; i >= 0 ; i--) {
            if (array[i] != null){
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    public void set(int index, E element) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = element;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        int size = size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i]);
            if (i < size - 1) stringBuilder.append(", ");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int length() {
        return size();
    }

    private void resize() {
        E[] resizeArray = (E[]) new Object[array.length + (array.length / 2)];
        for (int i = 0; i < array.length; i++) {
            resizeArray[i] = array[i];
        }
        array = resizeArray;
    }
    
}
/**
 * A linked list of character data objects.
 * (Actually, a list of Node objects, each holding a reference to a character
 * data object.
 * However, users of this class are not aware of the Node objects. As far as
 * they are concerned,
 * the class represents a list of CharData objects. Likwise, the API of the
 * class does not
 * mention the existence of the Node objects).
 */
public class List {

    // public static void main(String[] args) {
    //     String str = "committed_";
    //     List list = new List();
    //     for (int i = 0; i < str.length(); i++) {
    //         list.update(str.charAt(i));
    //     }

    //     System.out.println(list.toString());
    // }

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;

    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
        return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /**
     * GIVE Adds a CharData object with the given character to the beginning of this
     * list.
     */
    public void addFirst(char chr) {
        // Your code goes here
        CharData newChr = new CharData(chr);
        Node newNode = new Node(newChr, first);
        first = newNode;
        size++;
    }

    /** GIVE Textual representation of this list. */
    public String toString() {
        // Your code goes here

        if (size == 0) {
            return "()";
        }

        Node current = first;
        StringBuilder str = new StringBuilder("(");

        while (current != null) {
            str.append(current.toString());
            str.append(" ");
            current = current.next;
        }
        str.replace(str.length() - 1, str.length(), ")");

        return str.toString();

    }

    /**
     * Returns the index of the first CharData object in this list
     * that has the same chr value as the given char,
     * or -1 if there is no such object in this list.
     */
    public int indexOf(char chr) {
        // Your code goes here
        Node current = first;
        int index = 0;

        while (current != null) {

            if (current.cp.equals(chr)) {
                return index;
            }

            index++;
            current = current.next;

        }
        return -1;
    }

    /**
     * If the given character exists in one of the CharData objects in this list,
     * increments its counter. Otherwise, adds a new CharData object with the
     * given chr to the beginning of this list.
     */
    public void update(char chr) {
        // Your code goes here
        if (indexOf(chr) == -1) {
            addFirst(chr);
        } else {
            get(indexOf(chr)).count++;
        }
    }

    /**
     * GIVE If the given character exists in one of the CharData objects
     * in this list, removes this CharData object from the list and returns
     * true. Otherwise, returns false.
     */
    public boolean remove(char chr) {
        // Your code goes here
        if (indexOf(chr) == -1)
            return false;

        Node prev = null;
        Node current = first;

        while (current != null && !(current.cp.equals(chr))) {
            prev = current;
            current = current.next;
        }
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = current.next;
        }
        size--;

        return true;

    }

    /**
     * Returns the CharData object at the specified index in this list.
     * If the index is negative or is greater than the size of this list,
     * throws an IndexOutOfBoundsException.
     */
    public CharData get(int index) {
        // Your code goes here
        Node current = first;
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("index is out of bounds");

        int i = 0;
        while (i < index && current != null) {
            current = current.next;
            i++;
        }
        return current.cp;
    }

    /**
     * Returns an array of CharData objects, containing all the CharData objects in
     * this list.
     */
    public CharData[] toArray() {
        CharData[] arr = new CharData[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            arr[i++] = current.cp;
            current = current.next;
        }
        return arr;
    }

    /**
     * Returns an iterator over the elements in this list, starting at the given
     * index.
     */
    public ListIterator listIterator(int index) {
        // If the list is empty, there is nothing to iterate
        if (size == 0)
            return null;
        // Gets the element in position index of this list
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
        return new ListIterator(current);
    }
}
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
* A collection of Square objects that cannot contain two of the same Squares
*
* @author cmoeller3
* @version 1.0
*/
public class SquareSet implements Set<Square> {
    /**
    * An interator for the Square Set class
    * @author cmoeller3
    * @version 1.0
    */
    private class SquareIterator implements Iterator<Square> {
        private int cursor = 0;
        /**
        * @return true if there is a next element
        */
        @Override
        public boolean hasNext() {
            return cursor <= lastIndex;
        }
        /**
        * @return the next Square in the set
        */
        @Override
        public Square next() {
            if (hasNext()) {
                Square s = get(cursor++);
                return s;
            } else {
                throw new NoSuchElementException("No more elements");
            }
        }

        @Override
        public void remove() {
            SquareSet.this.remove(cursor - 1);
        }
    }

    private int lastIndex;
    private Square[] elements;
    /**
    * Creates a new SquareSet of capacity 10
    */
    public SquareSet() {
        this(0);
    }
    /**
    * Creates a new SquareSet of a given capacity
    * @param capacity used to create new SquareSet
    */
    private SquareSet(int capacity) {
        elements = new Square[capacity];
        lastIndex = -1;
    }

    /**
    * Constructor creates a default Square set and adds all
    * non-nulls to collection
    * @param squares to be checked and stored in this set
    */
    public SquareSet(Collection<Square> squares) {
        this(0);
        for (Square s : squares) {
            if (s != null) {
                this.add(s);
            }
        }
    }

    /**
    * @param index of the desired square
    * @return Square at index
    */
    public Square get(int index) {
        if (index < 0 || index > lastIndex) {
            throw new IndexOutOfBoundsException(new Integer(index).toString());
        }
        return elements[index];
    }
    /**
    * Resets set to an empty array of length 10 and resets the lastIndex
    */
    @Override
    public void clear() {
        elements = new Square[10];
        lastIndex = -1;
    }

    /**
    * Removes all squares from this that are contained in c
    * @param c collection of squares to be removed if not null and is an
    * instance of Square Set
    * @return true if all elements succesfully removed
    */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        if (!(c instanceof SquareSet)) {
            return false;
        }
        SquareSet s = (SquareSet) c;
        for (Square square : s) {
            s.isValid(square);
        }
        for (Square square : s) {
            this.remove(square);
        }
        return true;
    }

    /**
    * Removes all squares from this that are not contained in c
    * @param c collection of squares to be retained if not null and is an
    * instance of Square Set
    * @return true if all elements not contained in c are succesfully removed
    */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        if (!(c instanceof SquareSet)) {
            return false;
        }
        SquareSet s = (SquareSet) c;
        for (Square square : this.toArray(new Square[this.size()])) {
            if (!(s.contains(square))) {
                this.remove(square);
            }
        }
        return true;
    }

    /**
    * Checks all elements in c to be valid Squares if so add
    * @param Collection c attempted to add all elements to this set
    * @return true if all elements are added
    */
    @Override
    public boolean addAll(Collection<? extends Square> c) {
        for (Square s : c) {
            this.isValid(s);
        }
        if (this.equals(c)) {
            return false;
        }
        for (Square s : c) {
            this.add(s);
        }
        return true;
    }

    /**
    * Checks to see if all the elements in a provided collection are contained
    * in this set.
    * @param Collection c to be compared with this
    * @return true if c is a subset of this
    */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        for (Object square : c) {
            if (!(this.contains(square))) {
                return false;
            }
        }
        return true;
    }
    /**
    * Removes a single given Square from the set
    * @param Object o to be removed from the set
    * @return true if square was removed false otherwise
    */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square s = (Square) o;
        if (!(this.contains(s))) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (elements[i].equals(s)) {
                for (int j = i; j < this.size() - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements = Arrays.copyOf(elements, this.size() - 1);
                lastIndex--;
                return true;
            }
        }
        return false;
    }
    /**
    * Removes a single Square at a given index from the set
    * @param index of square to be removed
    * @return true if square was removed false otherwise
    */
    public boolean remove(int index) {
        if (!(index < this.size())) {
            return false;
        }
        for (int i = index; i < this.size() - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements = Arrays.copyOf(elements, this.size() - 1);
        lastIndex--;
        return true;
    }
    /**
    * Adds a square to the set if the square is a valid square and does not
    * already exist in the set
    * @param s element to be added to the Set
    * @return true if s is added false otherwise
    */
    public boolean add(Square s) {
        this.isValid(s);
        if (s == null) {
            throw new NullPointerException();
        }
        if (this.contains(s)) {
            return false;
        } else if (lastIndex == elements.length - 1) {
            int newCapacity = elements.length + 1;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[++lastIndex] = s;
        return true;
    }
    /**
    * @param Square[] a is the array to have the set stored in
    * @return Square[] a with a copy of elements if it large enough
    * else return a new Copy with run-time type Square[]
    */
    @Override
    public <Square> Square[] toArray(Square[] a) {
        if (a.length < this.size()) {
            return (Square[]) Arrays.copyOf(elements,
                this.size(), a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, this.size());
        if (a.length > this.size()) {
            a[this.size()] = null;
        }
        return a;
    }

    /**
    * @return Square[] elements
    */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, this.size());
    }
    /**
    * @retrun a new Square Iterator
    */
    @Override
    public Iterator<Square> iterator() {
        return new SquareIterator();
    }

    /**
    * Checks to see if the object o equals an element in this
    * @param Object o is the object to be compared
    * @return true if o equals an element in this
    */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Square) {
            Square s2 = (Square) o;
            for (Square s : elements) {
                if (s2.equals(s)) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public boolean isEmpty() {
        return lastIndex == -1;
    }

    /**
    * @return the numbers of elements in the set
    */
    @Override
    public int size() {
        return lastIndex + 1;
    }
    /**
    * Checks a given object to not be null and and instance of SquareSet
    * compares all elements of this to o
    * @param o compared to this if not null and is an
    * instance of SquareSet
    * @return true if o is not null and instance of SquareSet and all elements
    * are equals
    */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        Set s = (Set) o;

        return this.size() == s.size()
            && this.containsAll(s) && s.containsAll(this);
    }

    /**
    * does something
    * @return int something
    */
    public int hashCode() {
        int result = 0;
        for (Square s : this.toArray(new Square[this.size()])) {
            result += s.hashCode();
        }
        return result;
    }

    /**
    * Checks to see if the passed Square is a valid Square
    * @param s checked to be valid
    */
    public void isValid(Square s) {
        if ((charToInt(s.getFile()) > 8 || charToInt(s.getFile()) < 1)
            || (Character.getNumericValue(s.getRank()) > 8
            || Character.getNumericValue(s.getRank()) < 1)) {
            throw new InvalidSquareException("" + s.getFile() + s.getRank());
        }
    }

    /**
    * Converts a lowercase char to an int
    *
    * @return int representation of a char
    * @param c converted to int
    */
    public static int charToInt(char c) {
        int charA = (int) ('a' - 1);
        int answer = ((int) (c)) - charA;
        return answer;
    }
}
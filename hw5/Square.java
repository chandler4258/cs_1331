/**
* Represents a square on a chess board with a name.
*
* @author Instructor
* @version 1.0
*/
public class Square {
    private char rank;
    private char file;
    private String name;
    /**
    * Constructs a square with a given file and rank
    * Calls other constructor.
    * @param file used in this call
    * @param rank used in this call
    */
    public Square(char file, char rank) {
        this("" + file + rank);
    }
    /**
    * Constructs a square with a given name.
    *
    * @param name stored in name split into file and rank
    */
    public Square(String name) {
        this.name = name;
        if (name != null && name.length() == 2) {
            file = name.charAt(0);
            rank = name.charAt(1);
            if (file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8') {
                this.name = name;

            } else {
                throw new InvalidSquareException(name);
            }
        } else {
            throw new InvalidSquareException(name);
        }
    }
    /**
    * @return the file of the Square ie 'c'
    */
    public char getFile() {
        return file;
    }
    /**
    * @return the rank of the Square ie '4'
    */
    public char getRank() {
        return rank;
    }
    /**
    * @return the name of the Square ie "c4"
    */
    public String toString() {
        return name;
    }
    /**
    * Converts a passed integer into a file char "abcdefgh"
    *
    * @return true if object is the same object or has the same rank and file
    * false otherwise
    * @param o compared to this object
    */
    public boolean equals(Object o) {
        if (o instanceof Square) {
            Square that = (Square) o;
            return that.rank == rank && that.file == file;
        } else {
            return false;
        }
    }
    /**
    * place holder hash Code
    * @return hashCode of a Square
    */
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) file;
        result = 31 * result + (int) rank;
        return result;
    }
}
/**
* Represents a square on a chess board with a name.
*
* @author cmoeller3
* @version 1.0
*/
public class Square {
    private char file;
    private char rank;
    private int x; //int representation of file
    private int y; //int representation of rank
    private String name;

    /**
    * Constructs a square with a given file and rank
    *
    * @param fileIn stored in file
    * @param rankIn stored in rank
    */
    public Square(char fileIn, char rankIn) throws InvalidSquareException {
        if ((charToInt(fileIn) > 8 || charToInt(fileIn) < 1)
            || (Character.getNumericValue(rankIn) > 8
            || Character.getNumericValue(rankIn) < 1)) {
            InvalidSquareException e = new InvalidSquareException(""
                + fileIn + rankIn);

            throw e;
        } else {
            file = fileIn;
            rank = rankIn;
            x = charToInt(fileIn);
            y = Character.getNumericValue(rankIn);
            name = "" + file + rank;
        }
    }
    /**
    * Constructs a square with a given name.
    * Calls other constructor.
    *
    * @param nameIn stored in name and used to call other constructor
    */
    public Square(String nameIn) throws InvalidSquareException {
        this(nameIn.charAt(0), nameIn.charAt(1));
        name = nameIn;
    }
    /**
    * @return the name of the Square ie "c4"
    */
    public String toString() {
        return name;
    }
    /**
    * @return the rank of the Square ie '4'
    */
    public char getRank() {
        return rank;
    }
    /**
    * @return the file of the Square ie 'c'
    */
    public char getFile() {
        return file;
    }
    /**
    * @return the rank of the Square in int form ie 4
    */
    public int getRankInt() {
        return y;
    }
    /**
    * @return the file of the Square in int form ie 3
    */
    public int getFileInt() {
        return x;
    }
    /**
    * Checks to see if given rank and file ints are in bounds of a chess board
    *
    * @return true if it is within bounds false otherwise
    *
    * @param rank checked for inBounds
    * @param file checked for inBounds
    */
    public static boolean isInBounds(int rank, int file) {
        if ((rank < 9 && rank > 0) && (file < 9 && file > 0)) {
            return true;
        }
        return false;
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
    /**
    * Converts an int to its char form
    *
    * @return char representation of an int
    * @param i converted to rank char
    */
    public static char intToCharRank(int i) {
        int char1 = (int) ('1' - 1);
        return (char) (i + char1);
    }
    /**
    * Converts a passed integer into a file char "abcdefgh"
    *
    * @return file char of the passed in int
    * @param i converted to file char
    */
    public static char intToCharFile(int i) {
        int charA = (int) ('a' - 1);
        return (char) (i + charA);
    }

    /**
    * Converts a passed integer into a file char "abcdefgh"
    *
    * @return true if object is the same object or has the same rank and file
    * false otherwise
    * @param testObject compared to this object
    */
    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (testObject == null) {
            return false;
        }

        if (!(testObject instanceof Square)) {
            return false;

        }
        Square testSquare = (Square) testObject;
        return (this.file == testSquare.file
                    && this.rank == testSquare.rank) ? true : false;
    }

    /**
    * place holder hash Code
    * @return 0
    */
    public int hashCode() {
        return 0;
    }
}
/**
* Exception thrown when a Square is created that does not exist on
* a chess board.
*
* @author Instructor
* @version 1.0
*/
public class InvalidSquareException extends RuntimeException {

    /**
    * Constructor for InvalidSquareException
    * @param square used in super call
    */
    public InvalidSquareException(String square) {
        super(square);
    }
}
/**
* Exception thrown when a Square is created that does not exist on
* a chess board.
* InvalidSquareException is a checked exception because it is thrown when
* invalid user input is inputed.
*
* @author cmoeller3
* @version 1.0
*/
public class InvalidSquareException extends RuntimeException {

    private String message = "";

    /**
    * Constructor for InvalidSquareException
    *
    * @param messageIn stored in message
    */
    public InvalidSquareException(String messageIn) {
        message = messageIn;
    }

    /**
    * Returns the String message of te Exception
    * @return String message
    */
    public String getMessage() {
        return message;
    }
}
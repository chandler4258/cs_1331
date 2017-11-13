/**
* abstract class for the pieces of chess
*
* @author Instructor
* @version 1.0
*/
public abstract class Piece {
    private Color color;
    /**
    * Constructor assigns c to color
    *
    * @param c stored in color
    */
    public Piece(Color c) {
        color = c;
    }
    /**
    * @return color of this piece
    */
    public Color getColor() {
        return color;
    }

    /**
    * Checks to see if a file and rank is in the bounds of a chess board
    * @param file used in check
    * @param rank used in check
    * @return true if in bounds false otherwise
    */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }
    /**
    * Abstract method to get algebraic Name
    * @return string algebraic name
    */
    public abstract String algebraicName();
    /**
    * Abstract method to get fen Name
    * @return string fen name
    */
    public abstract String fenName();
    /**
    * Abstract: finds all squares this piece could move to from starting square
    * @return Square[] containing all valid moves this piece could make
    * @param square is the starting square
    */
    public abstract Square[] movesFrom(Square square);

    /**
    * @return String of color and type of piece
    */
    public String toString() {
        return color.toString() + " " + this.getClass();
    }
}
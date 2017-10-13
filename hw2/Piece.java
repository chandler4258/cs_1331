/**
* abstract class for the pieces of chess
*
* @author cmoeller3
* @version 1.0
*/

public abstract class Piece {
    private Color color;

    /**
    * Constructor assigns colorIn to color
    *
    * @param colorIn stored in color
    */
    public Piece(Color colorIn) {
        color = colorIn;
    }

    /**
    * @return color of this piece
    */
    public Color getColor() {
        return color;
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

}
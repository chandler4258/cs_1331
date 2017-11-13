/**
* Pawn piece is a subclass of Piece
*
* @author Instructor
* @version 1.0
*/
public class Pawn extends Piece {
    /**
    * Calls super constructor with param c
    *
    * @param c used in super call
    */
    public Pawn(Color c) {
        super(c);
    }
    /**
    * @return string algebraic name ""
    */
    public String algebraicName() {
        return "";
    }
    /**
    * returns upper or lower case based on Color
    * @return string fen name "P" or "p"
    */
    public String fenName() {
        return getColor() == Color.WHITE ? "P" : "p";
    }
    /**
    * finds all squares a pawn could move to from starting square
    * @return Square[] containing all valid moves a pawn could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        char rank = square.getRank();
        char file = square.getFile();
        if (getColor() == Color.WHITE) {
            if (rank == '8') {
                return new Square[0];
            } else if (rank == '2') {
                return new Square[]{new Square(file, '4'),
                    new Square(file, '3')};
            } else {
                return new Square[]{new Square(file, (char) (rank + 1))};
            }
        } else {
            if (rank == '1') {
                return new Square[0];
            } else if (rank == '7') {
                return new Square[]{new Square(file, '5'),
                    new Square(file, '6')};
            } else {
                return new Square[]{new Square(file, (char) (rank - 1))};
            }
        }
    }
}
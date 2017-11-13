/**
* King piece is a subclass of Piece
*
* @author Instructor
* @version 1.0
*/
public class King extends Piece {
    /**
    * Calls super constructor with param c
    *
    * @param c used in super call
    */
    public King(Color c) {
        super(c);
    }
    /**
    * @return string algebraic name "K"
    */
    public String algebraicName() {
        return "K";
    }
    /**
    * returns upper or lower case based on Color
    * @return string fen name "K" or "k"
    */
    public String fenName() {
        return getColor() == Color.WHITE ? "K" : "k";
    }
    /**
    * finds all squares a king could move to from starting square
    * @return Square[] containing all valid moves a king could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (isInBoard((char) (file + c), (char) (rank + r))) {
                    sq[counter++] = new Square((char) (file + c),
                        (char) (rank + r));
                }
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
/**
* Rook piece is a subclass of Piece
*
* @author Instructor
* @version 1.0
*/
public class Rook extends Piece {
    /**
    * Calls super constructor with param c
    *
    * @param c used in super call
    */
    public Rook(Color c) {
        super(c);
    }
    /**
    * @return string algebraic name "R"
    */
    public String algebraicName() {
        return "R";
    }
    /**
    * returns upper or lower case based on Color
    * @return string fen name "R" or "r"
    */
    public String fenName() {
        return getColor() == Color.WHITE ? "R" : "r";
    }
    /**
    * finds all squares a rook could move to from starting square
    * @return Square[] containing all valid moves a rook could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], rank)) {
                sq[counter++] = new Square(files[0], rank);
            }
            if (isInBoard(files[1], rank)) {
                sq[counter++] = new Square(files[1], rank);
            }
            if (isInBoard(file, ranks[0])) {
                sq[counter++] = new Square(file, ranks[0]);
            }
            if (isInBoard(file, ranks[1])) {
                sq[counter++] = new Square(file, ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
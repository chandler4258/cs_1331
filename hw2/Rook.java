/**
* Rook piece is a subclass of Piece
*
* @author cmoeller3
* @version 1.0
*/

public class Rook extends Piece {

    /**
    * Calls super constructor with param colorIn
    *
    * @param colorIn used in super call
    */
    public Rook(Color colorIn) {
        super(colorIn);
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
        if (getColor() == Color.WHITE) {
            return algebraicName();
        } else {
            return algebraicName().toLowerCase();
        }
    }
    /**
    * finds all squares a rook could move to from starting square
    * @return Square[] containing all valid moves a rook could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] fromSquares = new Square[14];
        int fileInt = square.getFileInt();
        int rankInt = square.getRankInt();
        int count = 0;
        String sb = "";

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt, fileInt + i)) {
                sb = "" + Square.intToCharFile(fileInt + i)
                    + Square.intToCharRank(rankInt);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt + i, fileInt)) {
                sb = "" + Square.intToCharFile(fileInt)
                    + Square.intToCharRank(rankInt + i);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt, fileInt - i)) {
                sb = "" + Square.intToCharFile(fileInt - i)
                    + Square.intToCharRank(rankInt);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt - i, fileInt)) {
                sb = "" + Square.intToCharFile(fileInt)
                    + Square.intToCharRank(rankInt - i);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }

        Square[] fromSquaresFinal = new Square[count];
        int counter = 0;
        for (Square s : fromSquares) {
            if (s != null) {
                fromSquaresFinal[counter] = s;
                counter++;
            }
        }
        return fromSquaresFinal;
    }
}
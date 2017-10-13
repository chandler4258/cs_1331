/**
* Bishop piece is a subclass of Piece
*
* @author cmoeller3
* @version 1.0
*/

public class Bishop extends Piece {

    /**
    * Calls super constructor with param colorIn
    *
    * @param colorIn used in super call
    */
    public Bishop(Color colorIn) {
        super(colorIn);
    }
    /**
    * @return string algebraic name "B"
    */
    public String algebraicName() {
        return "B";
    }
    /**
    * returns upper or lower case based on Color
    * @return string fen name "B" or "b"
    */
    public String fenName() {
        if (getColor() == Color.WHITE) {
            return algebraicName();
        } else {
            return algebraicName().toLowerCase();
        }
    }
    /**
    * finds all squares a bishop could move to from starting square
    * @return Square[] containing all valid moves a bishop could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] fromSquares = new Square[13];
        int fileInt = square.getFileInt();
        int rankInt = square.getRankInt();
        int count = 0;
        String sb = "";

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt + i, fileInt + i)) {
                sb = "" + Square.intToCharFile(fileInt + i)
                    + Square.intToCharRank(rankInt + i);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt - i, fileInt + i)) {
                sb = "" + Square.intToCharFile(fileInt + i)
                    + Square.intToCharRank(rankInt - i);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt - i, fileInt - i)) {
                sb = "" + Square.intToCharFile(fileInt - i)
                    + Square.intToCharRank(rankInt - i);
                fromSquares[count] = new Square(sb);
                count++;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (Square.isInBounds(rankInt + i, fileInt - i)) {
                sb = "" + Square.intToCharFile(fileInt - i)
                    + Square.intToCharRank(rankInt + i);
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
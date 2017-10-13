/**
* King piece is a subclass of Piece
*
* @author cmoeller3
* @version 1.0
*/
public class King extends Piece {

    /**
    * Calls super constructor with param colorIn
    *
    * @param colorIn used in super call
    */
    public King(Color colorIn) {
        super(colorIn);
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
        if (getColor() == Color.WHITE) {
            return algebraicName();
        } else {
            return algebraicName().toLowerCase();
        }
    }
    /**
    * finds all squares a king could move to from starting square
    * @return Square[] containing all valid moves a king could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] fromSquares = new Square[8];
        int fileInt = square.getFileInt();
        int rankInt = square.getRankInt();
        int count = 0;
        String sb = "";

        if (Square.isInBounds(rankInt - 1, fileInt)) {
            sb = "" + Square.intToCharFile(fileInt)
                + Square.intToCharRank(rankInt - 1);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt - 1, fileInt + 1)) {
            sb = "" + Square.intToCharFile(fileInt + 1)
                + Square.intToCharRank(rankInt - 1);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt, fileInt + 1)) {
            sb = "" + Square.intToCharFile(fileInt + 1)
                + Square.intToCharRank(rankInt);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt + 1, fileInt + 1)) {
            sb = "" + Square.intToCharFile(fileInt + 1)
                + Square.intToCharRank(rankInt + 1);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt + 1, fileInt)) {
            sb = "" + Square.intToCharFile(fileInt)
                + Square.intToCharRank(rankInt + 1);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt + 1, fileInt - 1)) {
            sb = "" + Square.intToCharFile(fileInt - 1)
                + Square.intToCharRank(rankInt + 1);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt, fileInt - 1)) {
            sb = "" + Square.intToCharFile(fileInt - 1)
                + Square.intToCharRank(rankInt);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
        }
        if (Square.isInBounds(rankInt - 1, fileInt - 1)) {
            sb = "" + Square.intToCharFile(fileInt - 1)
                + Square.intToCharRank(rankInt - 1);
            fromSquares[count] = new Square(sb);
            count++;
            sb = "";
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
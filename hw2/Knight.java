/**
* King piece is a subclass of Piece
*
* @author cmoeller3
* @version 1.0
*/
public class Knight extends Piece {

    /**
    * Calls super constructor with param colorIn
    *
    * @param colorIn used in super call
    */
    public Knight(Color colorIn) {
        super(colorIn);
    }
    /**
    * @return string algebraic name "N"
    */
    public String algebraicName() {
        return "N";
    }
    /**
    * returns upper or lower case based on Color
    * @return string fen name "N" or "n"
    */
    public String fenName() {
        if (getColor() == Color.WHITE) {
            return algebraicName();
        } else {
            return algebraicName().toLowerCase();
        }
    }
    /**
    * finds all squares a knight could move to from starting square
    * @return Square[] containing all valid moves a knight could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] fromSquares = new Square[8];
        int fileInt = square.getFileInt();
        int rankInt = square.getRankInt();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        if (Square.isInBounds(rankInt - 2, fileInt - 1)) {
            sb = sb.append(Square.intToCharFile(fileInt - 1));
            sb = sb.append(Square.intToCharRank(rankInt - 2));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt - 2, fileInt + 1)) {
            sb = sb.append(Square.intToCharFile(fileInt + 1));
            sb = sb.append(Square.intToCharRank(rankInt - 2));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt - 1, fileInt + 2)) {
            sb = sb.append(Square.intToCharFile(fileInt + 2));
            sb = sb.append(Square.intToCharRank(rankInt - 1));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt + 1, fileInt + 2)) {
            sb = sb.append(Square.intToCharFile(fileInt + 2));
            sb = sb.append(Square.intToCharRank(rankInt + 1));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt + 2, fileInt + 1)) {
            sb = sb.append(Square.intToCharFile(fileInt + 1));
            sb = sb.append(Square.intToCharRank(rankInt + 2));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt + 2, fileInt - 1)) {
            sb = sb.append(Square.intToCharFile(fileInt - 1));
            sb = sb.append(Square.intToCharRank(rankInt + 2));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt + 1, fileInt - 2)) {
            sb = sb.append(Square.intToCharFile(fileInt - 2));
            sb = sb.append(Square.intToCharRank(rankInt + 1));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
        }
        if (Square.isInBounds(rankInt - 1, fileInt - 2)) {
            sb = sb.append(Square.intToCharFile(fileInt - 2));
            sb = sb.append(Square.intToCharRank(rankInt - 1));
            fromSquares[count] = new Square(sb.toString());
            count++;
            sb = sb.delete(0, sb.length());
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
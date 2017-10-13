/**
* Pawn piece is a subclass of Piece
*
* @author cmoeller3
* @version 1.0
*/

public class Pawn extends Piece {

    /**
    * Calls super constructor with param colorIn
    *
    * @param colorIn used in super call
    */
    public Pawn(Color colorIn) {
        super(colorIn);
    }
    /**
    * @return string algebraic name ""
    */
    public String algebraicName() {
        return "";
    }
    /**
    * @return string fen name ""
    */
    public String fenName() {
        return "";
    }
    /**
    * finds all squares a pawn could move to from starting square
    * @return Square[] containing all valid moves a pawn could make
    * @param square is the starting square
    */
    public Square[] movesFrom(Square square) {
        Square[] fromSquares = new Square[4];
        int fileInt = square.getFileInt();
        int rankInt = square.getRankInt();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        if (getColor() == Color.WHITE) {
            if (Square.isInBounds(rankInt + 1, fileInt)) {
                sb = sb.append(Square.intToCharFile(fileInt));
                sb = sb.append(Square.intToCharRank(rankInt + 1));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
            if (Square.isInBounds(rankInt + 1, fileInt + 1)) {
                sb = sb.append(Square.intToCharFile(fileInt + 1));
                sb = sb.append(Square.intToCharRank(rankInt + 1));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
            if (Square.isInBounds(rankInt + 1, fileInt - 1)) {
                sb = sb.append(Square.intToCharFile(fileInt - 1));
                sb = sb.append(Square.intToCharRank(rankInt + 1));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
        } else if (getColor() == Color.BLACK) {
            if (Square.isInBounds(rankInt - 1, fileInt)) {
                sb = sb.append(Square.intToCharFile(fileInt));
                sb = sb.append(Square.intToCharRank(rankInt - 1));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
            if (Square.isInBounds(rankInt - 1, fileInt + 1)) {
                sb = sb.append(Square.intToCharFile(fileInt + 1));
                sb = sb.append(Square.intToCharRank(rankInt - 1));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
            if (Square.isInBounds(rankInt - 1, fileInt - 1)) {
                sb = sb.append(Square.intToCharFile(fileInt - 1));
                sb = sb.append(Square.intToCharRank(rankInt - 1));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
        }
        if (getColor() == Color.WHITE && square.getRank() == '2') {
            if (Square.isInBounds(rankInt + 2, fileInt)) {
                sb = sb.append(Square.intToCharFile(fileInt));
                sb = sb.append(Square.intToCharRank(rankInt + 2));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
            }
        } else if (getColor() == Color.BLACK && square.getRank() == '7') {
            if (Square.isInBounds(rankInt - 2, fileInt)) {
                sb = sb.append(Square.intToCharFile(fileInt));
                sb = sb.append(Square.intToCharRank(rankInt - 2));
                fromSquares[count] = new Square(sb.toString());
                count++;
                sb = sb.delete(0, sb.length());
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
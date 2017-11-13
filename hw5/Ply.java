import java.util.Optional;

/**
* One player’s move in chess. Half a turn of chess.
*
* @author cmoeller3
* @version 1.0
*/
public class Ply {
    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;
    /**
    * Ply constructor that assigns all inputs.
    * @param piece stored in piece
    * @param from stored in from
    * @param to stored in to
    * @param comment stored in comment
    */
    public Ply(Piece piece, Square from, Square to, Optional<String> comment) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.comment = comment;
    }

    /**
    * @return piece
    */
    public Piece getPiece() {
        return piece;
    }

    /**
    * @return from
    */
    public Square getFrom() {
        return from;
    }

    /**
    * @return to
    */
    public Square getTo() {
        return to;
    }

    /**
    * @return comment
    */
    public Optional<String> getComment() {
        return comment;
    }
    /**
    * To String method for testing
    * @return string representation
    */
    public String toString() {
        return piece.toString() + ", " + from.toString() + ", " + to.toString()
            + ", " + (comment.isPresent() ? comment.get() : "null");
    }
}
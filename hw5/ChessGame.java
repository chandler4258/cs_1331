import java.util.stream.Collectors;
import java.util.List;
import java.util.function.Predicate;

/**
* A sequence of Chess moves
* @author cmoeller3
* @version 1.0
*/
public class ChessGame {
    private List<Move> moves;
    /**
    * @param moves stored in this moves
    */
    public ChessGame(List<Move> moves) {
        this.moves = moves;
    }

    /**
    * @return list of moves
    */
    public List<Move> getMoves() {
        return moves;
    }

    /**
    * @param n index of element to return
    * @return the nth moves
    */
    public Move getMove(int n) {
        return moves.get(n);
    }

    /**
    * @param filter predicate to be filtered
    * @return a new List of filtered moves
    */
    public List<Move> filter(Predicate<Move> filter) {
        return moves.stream().filter(filter).collect(Collectors.<Move>toList());
    }

    /**
    * Utilizes a lambda to call filter
    * @return the list of moves that have comments on either play
    */
    public List<Move> getMovesWithComment() {
        return this.filter(move -> move.getWhitePly().getComment().isPresent()
            || move.getBlackPly().getComment().isPresent());
    }

    /**
    * Utilizes an anonymous inner class to call filter
    *@return the list of moves that do not have comments on both plays
    */
    public List<Move> getMovesWithoutComment() {
        return this.filter(new Predicate<Move>() {
            public boolean test(Move move) {
                return (!move.getWhitePly().getComment().isPresent()
                    && !move.getBlackPly().getComment().isPresent());
            }
        });
    }

    /**
    * Utilizes an inner class to call filter
    * @param p type of piece to be included
    * @return the list of moves that use a piece with the same type as p
    */
    public List<Move> getMovesWithPiece(Piece p) {

        class PiecePredicate implements Predicate<Move> {
            private Class c;
            public PiecePredicate(Piece p) {
                c = p.getClass();
            }
            public boolean test(Move m) {
                return (m.getWhitePly().getPiece().getClass().equals(c))
                    || (m.getBlackPly().getPiece().getClass().equals(c));
            }
        }
        PiecePredicate pred = new PiecePredicate(p);
        return this.filter(pred);
    }


}
/**
* One turn of chess. Both White's Ply and Black Ply.
* @author cmoeller3
* @version 1.0
*/
public class Move {
    private Ply whitePly;
    private Ply blackPly;
    /**
    * Move constructor assigning instance variables
    * @param whitePly stored in this whitePly
    * @param blackPly stored in this blackPly
    */
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }
    /**
    * @return Ply whitePly
    */
    public Ply getWhitePly() {
        return whitePly;
    }
    /**
    * @return Ply blackPly
    */
    public Ply getBlackPly() {
        return blackPly;
    }

    /**
    * toString for testing
    * @return string representation
    */
    public String toString() {
        return whitePly.toString() + "\n" + blackPly.toString();
    }
}
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
* Class that holds metadata of a chess game.
* @author Instructor
* @version 1.0
*/
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty opening = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;

    /**
    * Constructor assigns paramaters to instance variables
    * @param event stored in event
    * @param site stored in site
    * @param date stored in date
    * @param white stored in white
    * @param black stored in black
    * @param result stored in result
    */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        this.opening.set("None");
        moves = new ArrayList<>();
    }
    /**
    * Adds a move to the list
    * @param move added to moves array
    */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
    * @return the move at n position
    * @param n used to get the nth -1 move
    */
    public String getMove(int n) {
        return moves.get(n - 1);
    }
    /**
    * @return event
    */
    public String getEvent() {
        return event.get();
    }
    /**
    * @return site
    */
    public String getSite() {
        return site.get();
    }
    /**
    * @return date
    */
    public String getDate() {
        return date.get();
    }
    /**
    * @return white
    */
    public String getWhite() {
        return white.get();
    }
    /**
    * @return black
    */
    public String getBlack() {
        return black.get();
    }
    /**
    * @return result
    */
    public String getResult() {
        return result.get();
    }
    /**
    * @return moves
    */
    public List<String> getMoves() {
        return moves;
    }
    /**
    * @return opening
    */
    public String getOpening() {
        return opening.get();
    }
    /**
    * To be called after all moves added
    * Searches moves to see if a specific opening was used
    */
    public void setOpening() {
        if (moves.size() == 0) {
            return;
        }
        if (moves.get(0).contains("e4 c5")) {
            opening.set("Sicilian Defence");
            return;
        }
        if (moves.get(0).contains("d4 Nf6")) {
            opening.set("Indian Defence");
            return;
        }
        if (moves.size() == 1) {
            return;
        }

        if (moves.get(0).contains("e4 e5")
            && moves.get(1).contains("Nf3 d6")) {
            opening.set("Philidor Defence");
            return;
        }
        if (moves.get(0).contains("d4 d5") && moves.get(1).contains("c4")) {
            opening.set("Queen's Gambit");

        }
        if (moves.size() == 2) {
            return;
        }
        if (moves.get(0).contains("e4 e5") && moves.get(1).contains("Nf3 Nc6")
            && moves.get(2).contains("Bc4 Bc5")) {
            opening.set("Giuoco Piano");
            return;


        }
        if (moves.get(0).contains("e4 e5")
            && moves.get(1).contains("Nf3 Nc6")
            && moves.get(2).contains("Bb5")) {
            opening.set("Ruy Lopez");
            return;
        }
    }

}
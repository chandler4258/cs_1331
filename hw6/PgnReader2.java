import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
* Pgn reader for passed in pgn file
* @author Chandler
* @version 1.0
*/
public class PgnReader2 {
    private String event = new String();
    private String site = new String();
    private String date = new String();
    private String white = new String();
    private String black = new String();
    private String result = new String();
    private String fileContent = new String();
    private List<String> moves = new ArrayList<>();
    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public String tagValue(String tagName, String game) {
        String[] lines = game.split("\n");
        for (int i = 0; i < lines.length; i++) {
            int start = 0;
            int end = 0;
            if (lines[i].contains(tagName)) {
                for (int x = 0; x < lines[i].length(); x++) {
                    if (start == 0 && lines[i].charAt(x) == '\"') {
                        start = x + 1;
                    } else if (lines[i].charAt(x) == '\"') {
                        end = x;
                    }
                }
                return lines[i].substring(start, end);
            }
        }
        return "NOT GIVEN";
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    /**
    * Calls fileContent method with given fileName
    * @param fileName used in fileContent call
    */
    public PgnReader2(String fileName) {
        fileContent = fileContent(fileName);
        event = tagValue("Event", fileContent);
        site = tagValue("Site", fileContent);
        date = tagValue("Date", fileContent);
        white = tagValue("White", fileContent);
        black = tagValue("Black", fileContent);
        result = tagValue("Result", fileContent);
        fileContent = cutTags(fileContent);
        this.addMoves(fileContent);

    }
    /**
    * Cut the tags off the given game
    * @param game given string with file content
    * @return return new game with no tags
    */
    public String cutTags(String game) {
        String gameNoTags;
        gameNoTags = (game.split("]"))[game.split("]").length - 1];
        gameNoTags = gameNoTags.trim();
        return gameNoTags;
    }
    /**
    * @param content split into moves and added to moves List
    */
    public void addMoves(String content) {
        List<String> allMoves =
            new ArrayList<String>(Arrays.asList(content.split(" ")));
        for (int i = 0; i < allMoves.size(); i++) {
            if (allMoves.get(i).contains(".")) {
                allMoves.remove(i);
            }
        }
        if (allMoves.size() % 2 == 1) {
            allMoves.add(null);
        }
        for (int i = 0; i < allMoves.size() / 2; i++) {
            moves.add(allMoves.get(i) + " " + allMoves.get(i + 1));
        }
    }
    /**
    * @return event
    */
    public String getEvent() {
        return event;
    }
    /**
    * @return site
    */
    public String getSite() {
        return site;
    }
    /**
    * @return date
    */
    public String getDate() {
        return date;
    }
    /**
    * @return white
    */
    public String getWhite() {
        return white;
    }
    /**
    * @return black
    */
    public String getBlack() {
        return black;
    }
    /**
    * @return result
    */
    public String getResult() {
        return result;
    }
    /**
    * @return moves
    */
    public List<String> getMoves() {
        return moves;
    }
}
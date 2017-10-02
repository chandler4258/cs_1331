import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {
    private static char[][] board = new char[8][8];
    private static String[] movesArray;
    private static int maxInt = 0;
    private static int minInt = 0;
    //white look for uppercase pieces and black look for lowercase pieces
    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
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
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `Strring` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String finalPosition(String game) {
        boardSetUp();
        String output = "";
        int space = 0;
        String moves;
        moves = cutTags(game);
        setUpMoves(moves);
        movePieces();
        for (int rank = board.length - 1; rank >= 0; rank--) {
            for (int file = 0;
                    file < board[rank].length; file++) {
                if (!Character.isDigit(board[rank][file]) && space == 0) {
                    output += board[rank][file];
                } else if (!Character.isDigit(board[rank][file])
                        && space != 0) {
                    output += space;
                    output += board[rank][file];
                    space = 0;
                } else {
                    space++;
                }
            }
            if (space != 0) {
                output += space;
                space = 0;
            }
            if (rank != 0) {
                output += "/";
            }

        }
        return output;
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
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

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));

    }
    public static char[][] boardSetUp() {
        setMaxInt(board.length - 1);
        for (int rank = 0; rank < board.length; rank++) {
            for (int file = 0; file < board[rank].length; file++) {
                board[rank][file] = '1';
            }
        }
        board[0][0] = 'R';
        board[0][1] = 'N';
        board[0][2] = 'B';
        board[0][3] = 'Q';
        board[0][4] = 'K';
        board[0][5] = 'B';
        board[0][6] = 'N';
        board[0][7] = 'R';
        board[1][0] = 'P';
        board[1][1] = 'P';
        board[1][2] = 'P';
        board[1][3] = 'P';
        board[1][4] = 'P';
        board[1][5] = 'P';
        board[1][6] = 'P';
        board[1][7] = 'P';
        board[7][0] = 'r';
        board[7][1] = 'n';
        board[7][2] = 'b';
        board[7][3] = 'q';
        board[7][4] = 'k';
        board[7][5] = 'b';
        board[7][6] = 'n';
        board[7][7] = 'r';
        board[6][0] = 'p';
        board[6][1] = 'p';
        board[6][2] = 'p';
        board[6][3] = 'p';
        board[6][4] = 'p';
        board[6][5] = 'p';
        board[6][6] = 'p';
        board[6][7] = 'p';
        return board;
    }

    public static String cutTags(String game) {
        String gameNoTags;
        gameNoTags = (game.split("]"))[game.split("]").length - 1];
        gameNoTags = gameNoTags.trim();
        return gameNoTags;
    }

    public static void setUpMoves(String moves) {
        movesArray = moves.split(" ");
        for (int i = 0; i < movesArray.length; i++) {
            movesArray[i].trim();
            int count = 0;
            for (int j = 0; j < movesArray[i].length(); j++) {
                if (movesArray[i].charAt(j) == '.') {
                    count++;
                }
            }
            if (count == 1) {
                movesArray[i] = "";
            }
        }
    }
    //moves the pieces by interpreting the move and
    public static void movePieces() {
        boolean side = true; //true move white, false move black
        for (int i = 0; i < movesArray.length; i++) {
            if (movesArray[i].length() > 0) {
                if (!containsSpecial(movesArray[i])) {
                    movePawn(side, movesArray[i]);
                } else if (movesArray[i].contains("=")) {
                    movePawn(side, movesArray[i]);
                } else if (containsSpecial(movesArray[i])
                    && movesArray[i].contains("R")) {
                    moveLinear(side, movesArray[i], "R");
                } else if (containsSpecial(movesArray[i])
                    && movesArray[i].contains("Q")) {
                    moveLinear(side, movesArray[i], "Q");
                    moveDiag(side, movesArray[i], "Q");
                } else if (containsSpecial(movesArray[i])
                    && movesArray[i].contains("B")) {
                    moveDiag(side, movesArray[i], "B");
                } else if (containsSpecial(movesArray[i])
                    && movesArray[i].contains("K")) {
                    moveLinear(side, movesArray[i], "K");
                    moveDiag(side, movesArray[i], "K");
                } else if (containsSpecial(movesArray[i])
                    && movesArray[i].contains("N")) {
                    moveKnight(side, movesArray[i]);
                } else if (containsSpecial(movesArray[i])
                    && movesArray[i].contains("0")) {
                    castle(side, movesArray[i]);
                }
                side = flipBool(side);
            }
        }
    }
    //flips a boolean
    public static boolean flipBool(boolean side) {
        boolean result;
        result = side ? false : true;
        return result;
    }
    //Checks if move in PGN contains a non-pawn move including castling
    public static boolean containsSpecial(String move) {
        boolean contains = false;
        if (move.contains("N") || move.contains("B") || move.contains("Q")
            || move.contains("R") || move.contains("K") || move.contains("0")) {
            contains = true;
        }
        return contains;
    }
    //moves a pawn for both single move and starting double move
    public static void movePawn(boolean side, String move) {
        int row = -1;
        int col = -1;
        col = convertMoveCol(move);
        row = convertMoveRow(move);
        char piece = side ? 'P' : 'p';
        char oppPiece = side ? 'p' : 'P';
        if (!move.contains("x")) {
            if (board[row - 1][col] == piece) {
                board[row - 1][col] = '1';
                board[row][col] = piece;
            } else if (board[row + 1][col] == piece) {
                board[row + 1][col] = '1';
                board[row][col] = piece;
            } else if (row == 3 && board[row - 2][col] == piece) {
                board[row - 2][col] = '1';
                board[row][col] = piece;
            } else if (row == 4 && board[row + 2][col] == piece) {
                board[row + 2][col] = '1';
                board[row][col] = piece;
            }
        } else if (move.contains("x") && board[row][col] == oppPiece) {
            if (board[row - 1][col - 1] == piece) {
                board[row - 1][col - 1] = '1';
                board[row][col] = piece;
            } else if (board[row - 1][col + 1] == piece) {
                board[row - 1][col + 1] = '1';
                board[row][col] = piece;
            }
        } else if (move.contains("x") && board[row][col] != oppPiece) {
            if (board[row - 1][col - 1] == piece) {
                board[row - 1][col - 1] = '1';
                board[row][col] = piece;
            } else if (board[row - 1][col + 1] == piece) {
                board[row - 1][col + 1] = '1';
                board[row][col] = piece;
            }
            if (side && board[row - 1][col] == oppPiece) {
                board[row - 1][col] = '1';
            } else if (!side && board[row + 1][col] == oppPiece) {
                board[row + 1][col] = '1';
            }
        }
        if (move.contains("=")) {
            char newPiece = 'z';
            for (int i = 0; i < move.length() - 1; i++) {
                if (move.charAt(i) == '=') {
                    newPiece = move.charAt(i + 1);
                }
            }
            board[row][col] = newPiece;
        }
    }
    //moves a knight in its L-shape pattern
    public static void moveKnight(boolean side, String move) {
        int row = -1;
        int col = -1;

        col = convertMoveCol(move);
        row = convertMoveRow(move);
        char piece = 'z';
        piece = side ? 'N' : 'n';

        if (isInBounds(row - 2, col - 1)
            && board[row - 2][col - 1] == piece) {
            board[row - 2][col - 1] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row - 2, col + 1)
            && board[row - 2][col + 1] == piece) {
            board[row - 2][col + 1] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row - 1, col + 2)
            && board[row - 2][col + 1] == piece) {
            board[row - 2][col + 1] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row + 1, col + 2)
            && board[row + 1][col + 2] == piece) {
            board[row + 1][col + 2] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row + 2, col + 1)
            && board[row + 2][col + 1] == piece) {
            board[row + 2][col + 1] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row + 2, col - 1)
            && board[row + 2][col - 1] == piece) {
            board[row + 2][col - 1] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row + 1, col - 2)
            && board[row + 1][col - 2] == piece) {
            board[row + 1][col - 2] = '1';
            board[row][col] = piece;
        } else if (isInBounds(row - 1, col - 2)
            && board[row - 1][col - 2] == piece) {
            board[row - 1][col - 2] = '1';
            board[row][col] = piece;
        }
    }
    //Checks to make sure location is within the board
    public static boolean isInBounds(int row, int col) {
        if (row >= minInt && row <= maxInt && col >= minInt && col <= maxInt) {
            return true;
        }
        return false;
    }
    //moves a piece in a vertical or horizontal line ie. Rooks and Queen
    public static void moveLinear(boolean side,
        String move, String pieceString) {
        int row = -1;
        int col = -1;
        col = convertMoveCol(move);
        row = convertMoveRow(move);
        char piece = side ? pieceString.charAt(0)
            : pieceString.toLowerCase().charAt(0);
        for (int i = 1; (col + i) <= maxInt; i++) {
            if (isInBounds(row, col + i)
                && board[row][col + i] == piece) {
                board[row][col + i] = '1';
                board[row][col] = piece;
            }
        }
        for (int i = 1; (row + i) <= maxInt; i++) {
            if (isInBounds(row + i, col)
                && board[row + i][col] == piece) {
                board[row + i][col] = '1';
                board[row][col] = piece;
            }
        }
        for (int i = 1; (col - i) >= minInt; i++) {
            if (isInBounds(row, col - i)
                && board[row][col - i] == piece) {
                board[row][col - i] = '1';
                board[row][col] = piece;
            }
        }
        for (int i = 1; (row - i) >= minInt; i++) {
            if (isInBounds(row - i, col)
                && board[row - i][col] == piece) {
                board[row - i][col] = '1';
                board[row][col] = piece;
            }
        }
    }
    //moves a piece in a diagnol line ie. Bishops and Queen
    public static void moveDiag(boolean side, String move, String pieceString) {
        int row = -1;
        int col = -1;
        col = convertMoveCol(move);
        row = convertMoveRow(move);
        char piece = side ? pieceString.charAt(0)
            : pieceString.toLowerCase().charAt(0);
        for (int i = 1; (i + col) <= maxInt && (i + row) <= maxInt; i++) {
            if (isInBounds(row + i, col + i)
                && board[row + i][col + i] == piece) {
                board[row + i][col + i] = '1';
                board[row][col] = piece;
            }
        }
        for (int i = 1; (i + col) <= maxInt && (row - i) >= minInt; i++) {
            if (isInBounds(row - i, col + i)
                && board[row - i][col + i] == piece) {
                board[row - i][col + i] = '1';
                board[row][col] = piece;
            }
        }
        for (int i = 1; (col - i) >= minInt && (row - i) >= minInt; i++) {
            if (isInBounds(row - i, col - i)
                && board[row - i][col - i] == piece) {
                board[row - i][col - i] = '1';
                board[row][col] = piece;
            }
        }
        for (int i = 1; (col - i) >= minInt && (i + row) <= maxInt; i++) {
            if (isInBounds(row + i, col - i)
                && board[row + i][col - i] == piece) {
                board[row + i][col - i] = '1';
                board[row][col] = piece;
            }
        }
    }
    public static void castle(boolean side, String move) {
        int count = 0;
        for (int i = 0; i < move.length(); i++) {
            if (move.charAt(i) == '0') {
                count++;
            }
        }
        if (side) {
            if (count == 2) {
                board[0][4] = '1';
                board[0][7] = '1';
                board[0][5] = 'R';
                board[0][6] = 'K';
            } else if (count == 3) {
                board[0][4] = '1';
                board[0][0] = '1';
                board[0][2] = 'K';
                board[0][3] = 'R';
            }
        } else {
            if (count == 2) {
                board[7][4] = '1';
                board[7][7] = '1';
                board[7][5] = 'r';
                board[7][6] = 'k';
            } else if (count == 3) {
                board[7][4] = '1';
                board[7][0] = '1';
                board[7][2] = 'k';
                board[7][3] = 'r';
            }
        }
    }
    //extracts the col in the array from PGN letter format
    public static int convertMoveCol(String move) {
        int location = -1;
        String boardRows = "abcdefgh";
        char col = 'z';
        for (int i = 0; i < move.length() - 1; i++) {
            if (!Character.isDigit(move.charAt(i))
                && Character.isDigit(move.charAt(i + 1))) {
                col = move.charAt(i);
            }
        }
        for (int i = 0; i < boardRows.length(); i++) {
            if (col == boardRows.charAt(i)) {
                location = i;
            }
        }
        return location;
    }
    //extracts the row in the array from the PGN format
    public static int convertMoveRow(String move) {
        int location = -1;
        String boardRows = "abcdefgh";
        for (int i = 0; i < move.length() - 1; i++) {
            if (!Character.isDigit(move.charAt(i))
                && Character.isDigit(move.charAt(i + 1))) {
                location = Character.getNumericValue(move.charAt(i + 1)) - 1;
            }
        }

        return location;
    }
    //sets the maxInt field to newMax
    public static void setMaxInt(int newMax) {
        maxInt = newMax;
    }
}

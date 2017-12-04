import java.util.ArrayList;
import java.util.List;

/**
* Database of chess games
* @author Instructor
* @version 1.0
*/
public class ChessDb {

    private List<ChessGame> games;
    /**
    * Initializes array and adds games
    */
    public ChessDb() {
        games = new ArrayList<>();
        games.add(morphyIsouard());
        games.add(talFischer());
        games.add(this.readerAdd("fegatello.pgn"));
        games.add(this.readerAdd("fools-mate.pgn"));
        games.add(this.readerAdd("french-with-ep.pgn"));
        games.add(this.readerAdd("giuoco-piano.pgn"));
        games.add(this.readerAdd("panandh-amirpb007-2011.pgn"));
        games.add(this.readerAdd("petrov-defense.pgn"));
        games.add(this.readerAdd("petrov-with-ep.pgn"));
        games.add(this.readerAdd("scholars-mate.pgn"));
        games.add(this.readerAdd("ruy-lopez.pgn"));
        games.add(this.readerAdd("ruy-lopez-closed.pgn"));
    }
    /**
    * @return games list
    */
    public List<ChessGame> getGames() {
        return games;
    }
    /**
    * @return morphyIsouard ChessGame
    */
    private ChessGame morphyIsouard() {
        ChessGame game = new ChessGame(
            "A Night at the Opera",
            "Paris Opera House",
            "1958.01.01",
            "Morphy, Paul",
            "Comte Isouard de Vauvenargues and Karl II, Duke of Brunswick",
            "1-0"
        );
        game.addMove("e4 e5");
        game.addMove("Nf3 d6");
        game.addMove("d4 Bg4");
        game.addMove("dxe5 Bxf3");
        game.addMove("Qxf3 dxe5");
        game.addMove("Bc4 Nf6");
        game.addMove("Qb3 Qe7");
        game.addMove("Nc3 c6");
        game.addMove("Bg5 b5");
        game.addMove("Nxb5 cxb5");
        game.addMove("Bxb5+ Nbd7");
        game.addMove("O-O-O Rd8");
        game.addMove("Rxd7 Rxd7");
        game.addMove("Rd1 Qe6");
        game.addMove("Bxd7+ Nxd7");
        game.addMove("Qb8+ Nxb8");
        game.addMove("Rd8#");
        game.setOpening();
        return game;
    }
    /**
    * @return talFisher ChessGame
    */
    private ChessGame talFischer() {
        ChessGame game = new ChessGame(
            "Bled-Zagreb-Belgrade Candidates",
            "Bled, Zagreb & Belgrade YUG",
            "1959.10.11",
            "Tal, Mikhail",
            "Fischer, Robert James",
            "1-0"
        );
        game.addMove("d4 Nf6");
        game.addMove("c4 g6");
        game.addMove("Nc3 Bg7");
        game.addMove("e4 d6");
        game.addMove("Be2 O-O");
        game.addMove("Nf3 e5");
        game.addMove("d5 Nbd7");
        game.addMove("Bg5 h6");
        game.addMove("Bh4 a6");
        game.addMove("O-O Qe8");
        game.addMove("Nd2 Nh7");
        game.addMove("b4 Bf6");
        game.addMove("Bxf6 Nhxf6");
        game.addMove("Nb3 Qe7");
        game.addMove("Qd2 Kh7");
        game.addMove("Qe3 Ng8");
        game.addMove("c5 f5");
        game.addMove("exf5 gxf5");
        game.addMove("f4 exf4");
        game.addMove("Qxf4 dxc5");
        game.addMove("Bd3 cxb4");
        game.addMove("Rae1 Qf6");
        game.addMove("Re6 Qxc3");
        game.addMove("Bxf5+ Rxf5");
        game.addMove("Qxf5+ Kh8");
        game.addMove("Rf3 Qb2");
        game.addMove("Re8 Nf6");
        game.addMove("Qxf6+ Qxf6");
        game.addMove("Rxf6 Kg7");
        game.addMove("Rff8 Ne7");
        game.addMove("Na5 h5");
        game.addMove("h4 Rb8");
        game.addMove("Nc4 b5");
        game.addMove("Ne5 1-0");
        game.setOpening();
        return game;

    }
    /**
    * @return ChessGame from a Pgn file
    */
    private ChessGame readerAdd(String fileName) {
        PgnReader2 reader = new PgnReader2(fileName);
        ChessGame game = new ChessGame(reader.getEvent(), reader.getSite(),
            reader.getDate(), reader.getWhite(), reader.getBlack(),
            reader.getResult());
        for (int i = 0; i < reader.getMoves().size(); i++) {
            game.addMove(reader.getMoves().get(i));
        }
        return game;
    }
}
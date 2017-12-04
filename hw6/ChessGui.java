import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
* Displays ChessGame data base in a GUI
* @author cmoeller3
* @version 1.0
*/
public class ChessGui extends Application {
    /**
    * Displays the ChessDb GUI
    * @param stage displays gui
    */
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        HBox searchBox = new HBox();
        Button view = new Button("View Game");
        Button dismiss = new Button("Dismiss");
        TextField inputField = new TextField();
        Button search = new Button("Search");

        inputField.setPromptText("Search");
        search.disableProperty().bind(
            Bindings.isEmpty(inputField.textProperty()));

        dismiss.setOnAction(event -> stage.close());
        hbox.getChildren().addAll(view, dismiss);
        searchBox.getChildren().addAll(inputField, search);
        ChessDb chessDb = new ChessDb();
        ObservableList<ChessGame> games =
            FXCollections.observableArrayList(chessDb.getGames());
        TableView<ChessGame> table = createTable(games);

        view.disableProperty().bind(
            Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));

        view.setOnAction(event -> {
                this.viewStage(table.
                    getSelectionModel().getSelectedItems().get(0));
            });
        search.setOnAction(searchEvent -> {
                this.searchTable(inputField, table);
            });
        vbox.getChildren().addAll(table, searchBox, hbox);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Chess DB GUI");
        stage.show();
    }

    /**
    * Create table for Gui
    * @param games contains ChessGame's for table
    * @return created table with games as elements
    */
    private TableView<ChessGame> createTable(ObservableList<ChessGame> games) {
        TableView<ChessGame> table = new TableView<>();
        table.setItems(games);

        TableColumn<ChessGame, StringProperty> eventCol =
            new TableColumn<>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("event"));

        TableColumn<ChessGame, StringProperty> siteCol =
            new TableColumn<>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("site"));

        TableColumn<ChessGame, StringProperty> dateCol =
            new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<ChessGame, StringProperty> whiteCol =
            new TableColumn<>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("white"));

        TableColumn<ChessGame, StringProperty> blackCol =
            new TableColumn<>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("black"));

        TableColumn<ChessGame, StringProperty> openingCol =
            new TableColumn<>("Opening");
        openingCol.setCellValueFactory(new PropertyValueFactory("opening"));

        TableColumn<ChessGame, StringProperty> resultCol =
            new TableColumn<>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("result"));


        table.getColumns().setAll(eventCol, siteCol, dateCol, whiteCol,
            blackCol, openingCol, resultCol);

        return table;
    }

    /**
    * Creats the view stage for the
    */
    private void viewStage(ChessGame game) {
        Stage viewStage = new Stage();
        viewStage.setTitle("View: " + game.getEvent());
        Button viewDismiss = new Button("Dismiss");
        viewDismiss.setOnAction(event2 -> viewStage.close());
        VBox viewVBox = new VBox();
        HBox viewHBox = new HBox();
        ObservableList<String> info = FXCollections.observableArrayList(
            game.getEvent(), game.getSite(), game.getDate(),
            game.getWhite(), game.getBlack(), game.getResult());
        ObservableList<String> moves =
            FXCollections.observableArrayList(game.getMoves());
        ListView<String> list = new ListView<>(info);
        ListView<String> movesList = new ListView<>(moves);
        viewHBox.getChildren().addAll(list, movesList);
        viewVBox.getChildren().addAll(viewHBox, viewDismiss);
        Scene scene = new Scene(viewVBox);
        viewStage.setScene(scene);
        viewStage.show();
    }

    /**
    * Search the table for a given value
    */
    private void searchTable(TextField inputField, TableView<ChessGame> table) {
        String input = inputField.getText();
        table.getItems().stream().filter(game ->
            game.getEvent().toLowerCase().contains(input.toLowerCase())
        ).findAny().ifPresent(game -> {
                table.getSelectionModel().select(game);
            });
        if (anySelected(table)) {
            inputField.setText("");
            inputField.requestFocus();
            return;
        }
        table.getItems().stream().filter(game ->
            game.getSite().toLowerCase().contains(input.toLowerCase())
        ).findAny().ifPresent(game -> {
                table.getSelectionModel().select(game);
            });
        if (anySelected(table)) {
            inputField.setText("");
            inputField.requestFocus();
            return;
        }
        table.getItems().stream().filter(game ->
            game.getDate().toLowerCase().contains(input.toLowerCase())
        ).findAny().ifPresent(game -> {
                table.getSelectionModel().select(game);
            });
        if (anySelected(table)) {
            inputField.setText("");
            inputField.requestFocus();
            return;
        }
        table.getItems().stream().filter(game ->
            game.getWhite().toLowerCase().contains(input.toLowerCase())
        ).findAny().ifPresent(game -> {
                table.getSelectionModel().select(game);
            });
        if (anySelected(table)) {
            inputField.setText("");
            inputField.requestFocus();
            return;
        }
        table.getItems().stream().filter(game ->
            game.getBlack().toLowerCase().contains(input.toLowerCase())
        ).findAny().ifPresent(game -> {
                table.getSelectionModel().select(game);
            });
        if (anySelected(table)) {
            inputField.setText("");
            inputField.requestFocus();
            return;
        } else {
            inputField.setText("");
            inputField.requestFocus();
            return;
        }
    }
    /**
    * @return if any item is selected
    */
    private boolean anySelected(TableView<ChessGame> table) {
        return table.getSelectionModel().getSelectedItems() != null;
    }
}
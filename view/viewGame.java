package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.Main;
import model.Board;
import ship.Ship;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Contains all the neccessary views to present through Java FX.
 * This is the constructor for viewGame.
 *
 */
public class viewGame{
    Main main;
    Stage stage;
    Button placementButton, shootingButton, saveButton, restartButton;

    public GridPane grid;

    private Ship[] ships;

    /**
     * The constructor for the viewGame
     * @param stage - takes the current stage
     * @param main - takes the current main
     */
    public viewGame(Stage stage, Main main){
        this.main = main;
        this.stage = stage;
        this.ships = main.getShipsHuman();
    }

    /**
     * The dialog box to show the instructions of where to place the ships before phase 1.
     * @param ships - takes in the number of ships.
     */
    public void instructions1(int ships){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("I UNDERSTAND", ButtonBar.ButtonData.CANCEL_CLOSE));
        dialog.setGraphic(placementIns(ships));
        dialog.getDialogPane().setPadding(new Insets(1, 1, 1, 100));
        dialog.getDialogPane().setMaxSize(1, 2);
        dialog.showAndWait();
    }

    /**
     * The dialog box to show the instructions of how to shoot in during the shooting phase.
     *
     */
    public void instructions2(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("I UNDERSTAND", ButtonBar.ButtonData.CANCEL_CLOSE));
        dialog.setGraphic(shootingIns());
        dialog.getDialogPane().setPadding(new Insets(1, 1, 1, 100));
        dialog.getDialogPane().setMaxSize(1, 2);
        dialog.showAndWait();
    }


    /**
     * The VBox to create titles and texts.
     * @param ships - takes in the number of ships.
     */
    public VBox placementIns(int ships){
        Text title1 = new Text("PLACEMENT PHASE:");
        title1.setFill(Color.RED);
        title1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        Text title2 = new Text("RIGHT CLICK to place horizontally.");
        title2.setFill(Color.BLACK);
        title2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Image image = new Image("horizontal.png");
        Text title3 = new Text("LEFT CLICK to place vertically.");
        title3.setFill(Color.BLACK);
        title3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Image image2 = new Image("vertical.png");
        Text count = new Text("You can place " + ships + " ships!");
        count.setFill(Color.BLACK);
        count.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        VBox vbox = new VBox(10, title1, title2, new ImageView(image), title3, new ImageView(image2), count);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     * The VBox to create titles and texts.
     *
     */
    public VBox shootingIns(){
        Text title1 = new Text("SHOOTING PHASE:");
        title1.setFill(Color.RED);
        title1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        Text title2 = new Text("Click on the opposite grid (Top Grid) to shoot the enemy's ship");
        title2.setFill(Color.BLACK);
        title2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Image image = new Image("miss.png");
        Text title3 = new Text("Means MISS");
        title3.setFill(Color.BLACK);
        title3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Image image2 = new Image("recent.png");
        Text title4 = new Text("Means the most recent move was a MISS");
        title4.setFill(Color.BLACK);
        title4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Image image3 = new Image("hit.png");
        Text title5 = new Text("Means HIT (You are allowed to shoot again until you miss)");
        title5.setFill(Color.BLACK);
        title5.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        VBox vbox = new VBox(10, title1, title2, new ImageView(image), title3, new ImageView(image2),
                title4, new ImageView(image3), title5);

        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     * The VBox to create the buttons, titles and text.
     * @param choice - takes in the choice for the gamemode.
     */
    public VBox leftButtons(int choice){
        Text title1 = new Text("INSTRUCTIONS");
        title1.setFill(Color.GREEN);
        title1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));

        placementButton = new Button("How to place ships?");
        placementButton.setId("placement");
        placementButton.setPrefSize(180, 50);
        placementButton.setFont(new Font(12));
        placementButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        shootingButton = new Button("How to shoot ships?");
        shootingButton.setId("save");
        shootingButton.setPrefSize(180, 50);
        shootingButton.setFont(new Font(12));
        shootingButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        VBox vbox = new VBox(10, title1, placementButton, shootingButton);
        placementButton.setOnAction(e->{
            if (choice == 10){
                instructions1(5);
                this.stage.requestFocus();
            }
            else if (choice == 7){
                instructions1(3);
                this.stage.requestFocus();
            }
        });
        shootingButton.setOnAction(e->{
            if (choice == 10){
                instructions2();
                this.stage.requestFocus();
            }
            else if (choice == 7){
                instructions2();
                this.stage.requestFocus();
            }
        });
        String border = """
            
                -fx-background-color: BURLYWOOD;
                """;
        vbox.setStyle(border);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     * The VBox to create titles, buttons and texts.
     * @param choice - game mode choice made by the player.
     * @param choice2 - accessibility choice made by the player
     */
    public VBox rightInteractive(int choice, int choice2){
        this.grid = createBoard(choice);
        Text title1 = new Text("HP BAR");
        title1.setFill(Color.GREEN);
        title1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        // There is a safe button, settings button and HP counter
        saveButton = new Button("SAVE");
        saveButton.setId("save");
        saveButton.setPrefSize(180, 50);
        saveButton.setFont(new Font(12));
        saveButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        restartButton = new Button("RESTART");
        restartButton.setId("restart");
        restartButton.setPrefSize(180, 50);
        restartButton.setFont(new Font(12));
        restartButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        saveButton.setOnAction(e->{
            // The entire save function for the game.
            Board enemyboard = main.getEnemyBoard();
            Board playerboard = main.getPlayerBoardBoard();
            Ship[] enemyships = main.shipsComputer;
            Ship[] playerships = main.shipsHuman;
            int grid = enemyboard.grid;
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
            String strDate = dateFormat.format(date) + ".txt";
            final File dir = new File("./view/boards");
            try {
                final File file =  new File(dir, strDate);
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(grid + main.computer.getShips() + main.human.getShips() + enemyboard.toString() + playerboard.toString() + "null" +main.choice2);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException ecep) {
                System.out.println("An error occurred.");
                ecep.printStackTrace();
            }
            saveDialog();
        });
        restartButton.setOnAction(e->{
            this.main = new Main();
            Scene scene = new Scene(this.main.createContent(choice, choice2, this.stage));
            this.stage.setTitle("Playing Battleship");
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.show();
        });
        String border = """
                -fx-border-color: red;
                -fx-border-insets: 1;
                -fx-border-width: 3;
                -fx-border-style: dashed;
                """;
        VBox hp = new VBox(this.grid); // add interactive hp bar in the bracket.
        hp.setStyle(border);
        VBox vbox = new VBox(10,title1, hp,saveButton,restartButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 2, 1, 10));
        return vbox;
    }

    /**
     * The GridPane created to show the grid of the game
     * @param choice - game mode choice made by the player.
     *
     */
    public GridPane createBoard(int choice) {
        int grid = 9;
        if (choice == 10){
            grid = 9;
        }
        else{
            grid = 5;
        }
        GridPane hpBoard = new GridPane();
        hpBoard.setPrefSize(20, 20);

        for (int y = 0; y < grid; y++) {
            for (int x = 0; x < 6; x++) {
                Rectangle name = new Rectangle(65, 30);
                name.setFill(Color.BURLYWOOD);
                name.setStroke(Color.BLACK);
                Rectangle tile = new Rectangle(39, 30);
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);
                if (x == 0 & y == 0 & choice == 10){
                    Text text = new Text("Carrier");
                    text.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text), x, y);
                }
                else if (x == 0 & y == 2 & choice == 10){
                    Text text2 = new Text("Cruiser");
                    text2.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text2), x, y);
                }
                else if (x == 0 & y == 4 & choice == 10){
                    Text text3 = new Text("Cruiser");
                    text3.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text3), x, y);
                }
                else if (x == 0 & y == 6 & choice == 10){
                    Text text4 = new Text("Destroyer");
                    text4.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text4), x, y);
                }
                else if (x == 0 & y == 8 & choice == 10){
                    Text text5 = new Text("Destroyer");
                    text5.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text5), x, y);
                }
                else if (x == 0 & y == 0 & choice == 7){
                    Text text3 = new Text("Cruiser");
                    text3.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text3), x, y);
                }
                else if (x == 0 & y == 2 & choice == 7){
                    Text text4 = new Text("Destroyer");
                    text4.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text4), x, y);
                }
                else if (x == 0 & y == 4 & choice == 7){
                    Text text5 = new Text("Destroyer");
                    text5.setFont(Font.font(15));
                    hpBoard.add(new StackPane(name, text5), x, y);
                }
                else{
                    hpBoard.add(new StackPane(tile, new Text("")), x, y);
                }
            }
        }
        painthp(hpBoard, choice);
        return hpBoard;
    }

    /**
     * To paint the interactive hp bar for the player.
     * @param choice - game mode choice made by the player.
     */
    public void painthp(GridPane grid, int choice) {
        for (int y = 0; y < grid.getColumnCount(); y++) {
            for (int x = 0; x < grid.getRowCount(); x++){
                if (choice == 10){
                    if (x == 0 & y > 0 ){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                    if (x == 2 & y > 0 & y < 5 ){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                    if (x == 4 & y > 0 & y < 5){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                    if (x == 6 & y > 0 & y < 4){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                    if (x == 8 & y > 0 & y < 4){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                }
                if (choice == 7){
                    if (x == 0 & y > 0 & y < 5){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                    if (x == 2 & y > 0 & y < 4){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                    if (x == 4 & y > 0 & y < 4){
                        getChildByRowColumn(grid, x, y).getChildren().get(0).setStyle("-fx-fill: grey;");
                    }
                }
            }
        }
    }

    /**
     * A method to determine the health of the player consistently.
     * @param choice - game mode choice made by the player.
     * @param shot - a boolean to check if there was a shot.
     * @param grid - a gridpane to run changes when there is health deducted.
     */
    public void deductHp(boolean shot, GridPane grid, int choice) {
        for (int ship = 0; ship < this.main.getShipsHuman().length; ship += 1) {
            if (choice == 10) {
                if (shot & this.main.getShipsHuman()[ship].getHP() < 5 & ship == 0) {
                    getChildByRowColumn(grid, 0, (5 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
                if (shot & this.main.getShipsHuman()[ship].getHP() < 4 & ship == 1) {
                    getChildByRowColumn(grid, 2, (4 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
                if (shot & this.main.getShipsHuman()[ship].getHP() < 4 & ship == 2) {
                    getChildByRowColumn(grid, 4, (4 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
                if (shot & this.main.getShipsHuman()[ship].getHP() < 3 & ship == 3) {
                    getChildByRowColumn(grid, 6, (3 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
                if (shot & this.main.getShipsHuman()[ship].getHP() < 3 & ship == 4) {
                    getChildByRowColumn(grid, 8, (3 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
            }
            if (choice == 7) {
                if (shot & this.main.getShipsHuman()[ship].getHP() < 4 & ship == 0) {
                    getChildByRowColumn(grid, 0, (4 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
                if (shot & this.main.getShipsHuman()[ship].getHP() < 3 & ship == 1) {
                    getChildByRowColumn(grid, 2, (3 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
                if (shot & this.main.getShipsHuman()[ship].getHP() < 3 & ship == 2) {
                    getChildByRowColumn(grid, 4, (3 - this.main.getShipsHuman()[ship].getHP())).getChildren().get(0).setStyle("-fx-fill: red;");
                }
            }
        }
    }

    /**
     * The getter method to ease the collection of a grid in the hp bar.
     * @param gridPane - the grid where you want to collect the coordinates from.
     * @param x - the x-coordinate to collect from the gridPane.
     * @param y - the y-coordinate to collect from the gridPane.
     */
    public static StackPane getChildByRowColumn(GridPane gridPane, int x, int y){

        for(final Node node : gridPane.getChildren()){
            if (GridPane.getRowIndex(node) == null) continue ;
            if(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) return (StackPane) node;
        }
        return null;
    }

    /**
     * The gridPane created to label the grid of the main game.
     * @param choice - game mode choice made by the player.
     *
     */
    public GridPane layout(int choice){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        HBox hbox1 = null;
        HBox hbox2 = null;
        if (choice == 10){
            hbox1 = new HBox(23,  new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"), new Text("7"), new Text("8"), new Text("9"));
            hbox2 = new HBox(23,  new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"), new Text("7"), new Text("8"), new Text("9"));
            hbox1.setPadding(new Insets(10));
            hbox2.setPadding(new Insets(10));
        } else if (choice == 7) {
            hbox1 = new HBox(23,  new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"));
            hbox2 = new HBox(23,  new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"));
            hbox1.setPadding(new Insets(10));
            hbox2.setPadding(new Insets(10));
        }
        VBox vbox = new VBox(0, hbox1, main.getEnemyBoard(), hbox2, main.getPlayerBoardBoard());
        VBox part1 = null;
        VBox part2 = null;
        if (choice == 10){
            part1 = new VBox(15, new Text("  "), new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"), new Text("7"), new Text("8"), new Text("9"));
            part2 = new VBox(15, new Text("  "), new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"), new Text("7"), new Text("8"), new Text("9"));
        } else if (choice == 7) {
            part1 = new VBox(14.5, new Text("  "), new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"));
            part2 = new VBox(15, new Text(" "), new Text("0"), new Text("1"),new Text("2"),new Text("3"),new Text("4"),new Text("5"),
                    new Text("6"));
        }
        VBox vbox2 = new VBox(5, part1, new Text(" "),part2);
        vbox2.setPadding(new Insets(15, 1, 5, 1));
        HBox hbox = new HBox(10, vbox2, vbox);
        grid.setAlignment(Pos.CENTER);
        grid.add(hbox, 1, 0);
        return grid;
    }

    /**
     * A dialog box created to show that a save has been made
     *
     */
    public void saveDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("CLOSE", ButtonBar.ButtonData.CANCEL_CLOSE));
        dialog.setGraphic(saveStatement());
        dialog.getDialogPane().setPadding(new Insets(1, 1, 1, 100));
        dialog.getDialogPane().setMaxSize(1, 2);
        dialog.showAndWait();
    }
    /**
     * A VBox created to show in the Dialog Box.
     *
     */
    public VBox saveStatement(){
        Text title1 = new Text("Game Saved!");
        title1.setFill(Color.RED);
        title1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        VBox vbox = new VBox(10, title1);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
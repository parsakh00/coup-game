package com.game.coup.gui;

import com.game.coup.HelloApplication;
import com.game.coup.game.Game;
import com.game.coup.model.Action;
import com.game.coup.model.Human;
import com.game.coup.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameSceneController {
    @FXML
    public ListView<String> actionsListView;
    @FXML
    public ChoiceBox<Action> actionChoiceBox;
    @FXML
    public ChoiceBox<String> challengeChoiceBox;
    @FXML
    public GridPane DeskCards;
    @FXML
    public GridPane HumanCard;
    @FXML
    public GridPane FirstBotGrid;
    @FXML
    public GridPane SecondBotGrid;
    @FXML
    public GridPane ThirdBotGrid;
    @FXML
    public ImageView userCoin;
    @FXML
    public ImageView firstBotCoin;
    @FXML
    public ImageView secondBotCoin;
    @FXML
    public ImageView thirdBotCoin;
    @FXML
    public Label userCoins;
    @FXML
    public Label firstBotCoins;
    @FXML
    public Label thirdBotCoins;
    @FXML
    public Label secondBotCoins;
    @FXML
    public Label gameCoin;
    @FXML
    public Button nextRound;
    @FXML
    public Button okAction;
    @FXML
    public Button okChallenge;
    private final Action[] ACTION_LIST = {Action.Income,Action.ForeignAid,Action.Coup
            ,Action.Taxes,Action.Assassinate,Action.Steal,Action.SwapInfluence};
    private final ObservableList<Action> ACTION = FXCollections.observableArrayList(ACTION_LIST);

    private final String[] CHALLENGE_LIST = {"No Challenge","FirstBot","SecondBot","ThirdBot"};
    private final ObservableList<String> CHALLENGE = FXCollections.observableArrayList(CHALLENGE_LIST);
    int eachCardNumber;
    Image Ambassador1 = new Image(String.valueOf(HelloApplication.class.getResource("images/Ambassador.png")));
    Image Assassin1 = new Image(String.valueOf(HelloApplication.class.getResource("images/Assassin.png")));
    Image Captain1 = new Image(String.valueOf(HelloApplication.class.getResource("images/Captain.png")));
    Image Duke1 = new Image(String.valueOf(HelloApplication.class.getResource("images/Duke.png")));
    Image Countess1 = new Image(String.valueOf(HelloApplication.class.getResource("images/Contessa.png")));
    Game game;
    int cnt;
    int position = 0;
    Player player1 = new Player("Parsa","user");
    Player player2 = new Player("Paranoid","FirstBot");
    Player player3 = new Player("CautiousKiller","SecondBot");
    Player player4 = new Player("Greedy","ThirdBot");

//    ArrayList<String> userCards;
//    userCards = new ArrayList<>();
//    desk = (ArrayList<String>) getDeskFile();

    public void initialize(){
        //boolean gameFinished = false;
        game = new Game(player1, player2, player3, player4);

        arrangeDesk();
        arrangeHands();
        putUsersCoin();
        putGameCoin();

        actionChoiceBox.setItems(ACTION);
        challengeChoiceBox.setItems(CHALLENGE);

//        while (!gameFinished){
//            game.player[game.turn].MakeAction(game);
//            gameFinished = game.isGameFinished();
//            game.turn = (game.turn + 1)%4;
//        }

    }

    public Player getUserPlayer(){
        for (int i = 0; i < 4; i++){
            if (Objects.equals(game.player[i].getBotNumber(), "user")) return game.player[i];
        }
        return null;
    }
    public void challengeBtn(ActionEvent actionEvent){
        //ToDO
    }
    public void actionBtn(ActionEvent actionEvent){
        ((Human) getUserPlayer()).setAction(actionChoiceBox.getSelectionModel().getSelectedItem());

    }

    public void nextRoundBtn(ActionEvent actionEvent){
        //ToDo
    }
    public void putUsersCoin(){
        for (int i = 0; i < 4; i++){
            String botNumber = getUserBotNumberFile(game.player[i]);
            int usersCoin = getUserCoinFromFile(game.player[i]);
            switch (botNumber) {
                case "FirstBot" -> firstBotCoins.setText(Integer.toString(usersCoin));
                case "SecondBot" -> secondBotCoins.setText(Integer.toString(usersCoin));
                case "ThirdBot" -> thirdBotCoins.setText(Integer.toString(usersCoin));
                default -> userCoins.setText(Integer.toString(usersCoin));
            }
        }
    }
    public void putGameCoin(){
        gameCoin.setText(Integer.toString(game.coin));
    }
    public void arrangeHands(){
        //for each player hand
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 2; i++) {
                Pane pane = new Pane();
                pane.setId(String.valueOf(i));
                pane.getStyleClass().removeAll();
                //String userName = getUserNameFromFile(game.player[j]);
                String botNumber = getUserBotNumberFile(game.player[j]);
                String handCardName = getUserCardsFromFile(game.player[j], i);
                switch (botNumber) {
                    case "FirstBot" -> {
                        pane.getChildren().add(getImageOfCard(handCardName));
                        FirstBotGrid.add(pane, i, 0, 1, 1);
                    }
                    case "SecondBot" -> {
                        pane.getChildren().add(getImageOfCard(handCardName));
                        SecondBotGrid.add(pane, i, 0, 1, 1);
                    }
                    case "ThirdBot" -> {
                        pane.getChildren().add(getImageOfCard(handCardName));
                        ThirdBotGrid.add(pane, i, 0, 1, 1);
                    }
                    default -> {
                        pane.getChildren().add(getImageOfCard(handCardName));
                        HumanCard.add(pane, i, 0, 1, 1);
                    }
                }
            }
        }
    }
    public int numberOfEachCardInDesk(ArrayList<String> desk, String cardName){
        eachCardNumber = 0;
        for (String element:desk){
            if(Objects.equals(element, cardName)) eachCardNumber += 1;
        }
        return eachCardNumber;
    }
    public void arrangeDesk(){
        for (int i = 0; i < 6; i++) {
                Pane pane = new Pane();
                pane.setId(String.valueOf(i));
                pane.getStyleClass().removeAll();
                if (position < 6) {
                    String cardName = game.desk.get(position);
                    if (Objects.equals(cardName, "Ambassador")) pane.getChildren().add(getImageOfCard("Ambassador"));
                    else if (Objects.equals(cardName, "Assassin")) pane.getChildren().add(getImageOfCard("Assassin"));
                    else if (Objects.equals(cardName, "Countess")) pane.getChildren().add(getImageOfCard("Countess"));
                    else if (Objects.equals(cardName, "Captain")) pane.getChildren().add(getImageOfCard("Captain"));
                    else if (Objects.equals(cardName, "Duke")) pane.getChildren().add(getImageOfCard("Duke"));
                    position += 1;
                    DeskCards.add(pane, 0, i, 1, 1);
                }

        }
    }
    public ImageView getImageOfCard(String cardName){
        if (Objects.equals(cardName, "Duke")){
            ImageView imageView = new ImageView(Duke1);
            imageView.setFitHeight(191);
            imageView.setFitWidth(102);
            return imageView;
        }
        else if (Objects.equals(cardName, "Assassin")){
            ImageView imageView = new ImageView(Assassin1);
            imageView.setFitHeight(191);
            imageView.setFitWidth(102);
            return imageView;
        }
        else if (Objects.equals(cardName, "Countess")){
            ImageView imageView = new ImageView(Countess1);
            imageView.setFitHeight(191);
            imageView.setFitWidth(102);
            return imageView;
        }
        else if (Objects.equals(cardName, "Captain")){
            ImageView imageView = new ImageView(Captain1);
            imageView.setFitHeight(191);
            imageView.setFitWidth(102);
            return imageView;
        }
        else {
            ImageView imageView = new ImageView(Ambassador1);
            imageView.setFitHeight(191);
            imageView.setFitWidth(102);
            return imageView;
        }
    }
    public int getUserCoinFromFile(Player player){

        try {

            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\game\\coup\\database\\" + player.getName() + ".json"));
            JSONObject deskCards = (JSONObject) obj;
            return ((Long) deskCards.get("Coin")).intValue();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getUserCardsFromFile(Player player, int i){
        try {

            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\game\\coup\\database\\" + player.getName() + ".json"));
            JSONObject deskCards = (JSONObject) obj;


            return ((ArrayList<String>) deskCards.get("Cards")).get(i);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getUserBotNumberFile(Player player){
        try {

            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\game\\coup\\database\\" + player.getName() + ".json"));
            JSONObject deskCards = (JSONObject) obj;


            return (String) deskCards.get("BotNumber");

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
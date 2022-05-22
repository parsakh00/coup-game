package com.game.coup.game;

import com.game.coup.model.Card;
import com.game.coup.model.Player;
import com.game.coup.model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game {

    public Player[] player;
    public ArrayList<String> desk;
    public int turn;
    public int coin;

    public Player user;
    public Player firstBot;
    public Player secondBot;
    public Player thirdBot;

    public Game(Player player1, Player player2, Player player3, Player player4) {
        turn = 0;
        this.player = new Player[4];
        player[0] = player1;
        player[1] = player2;
        player[2] = player3;
        player[3] = player4;
        coin = 50;
        desk = new ArrayList<>();
        //desk = (ArrayList<String>) getDeskFile();
        for (int i = 0 ; i < 3; i++){
            Card card1 = Card.Ambassador;
            Card card2 = Card.Assassin;
            Card card3 = Card.Countess;
            Card card4 = Card.Captain;
            Card card5 = Card.Duke;
            desk.add(card1.getCard());
            desk.add(card2.getCard());
            desk.add(card3.getCard());
            desk.add(card4.getCard());
            desk.add(card5.getCard());
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                int random = (int) Math.floor(10*Math.random()) % desk.size();
                player[i].addCardToHand(desk.get(random));
                desk.remove(random);
            }
        }
        MakeCardFile(desk);

        for (int i = 0; i < 4; i++){
            player[i].addCoin(2);
            this.coin -= 2;
            MakeUserFile(player[i].getCardFromHand(), player[i]);
        }

        for (int i = 0; i < 4; i++){
            if (Objects.equals(player[i].getBotNumber(), "FirstBot")) firstBot = player[i];
            else if (Objects.equals(player[i].getBotNumber(), "SecondBot")) secondBot = player[i];
            else if (Objects.equals(player[i].getBotNumber(), "ThirdBot")) thirdBot = player[i];
            else if (Objects.equals(player[i].getBotNumber(), "user")) user = player[i];
        }




    }

    public Player[] getPlayer() {
        return player;
    }

    public void addToDesk(String card){
        desk.add(card);
    }
    public String removeFromDesk(){
        int random = (int) Math.floor(10*Math.random()) % desk.size();
        String tmp = desk.get(random);
        desk.remove(random);
        return tmp;
    }

    public void MakeUserFile(ArrayList<String> hand, Player player){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Cards", hand);
        jsonObject.put("Coin", player.getCoin());
        jsonObject.put("isHuman", player.isHuman());
        jsonObject.put("Name", player.getName());
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\game\\coup\\database\\" + player.getName() + ".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void MakeCardFile(ArrayList<String> desk){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Cards", desk);
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\game\\coup\\database\\" + "cards" + ".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addCoins(int coin)
    {
        this.coin += coin;
    }
    public Object getDeskFile(){
        try {

            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\example\\coup\\database\\" + "cards" + ".json"));
            JSONObject deskCards = (JSONObject) obj;


            return deskCards.get("Cards");

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object getUserFile(String name){
        try {

            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\example\\coup\\database\\" + name + ".json"));
            JSONObject deskCards = (JSONObject) obj;


            return deskCards.get("Cards");

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void start(){
        boolean gameFinished = false;
        while (!gameFinished){
            player[turn].MakeAction(this);
            gameFinished = isGameFinished();
            turn = (turn + 1)%4;
        }
    }

    public boolean isGameFinished(){
        int cnt = 0;
        for (int i = 0; i < 4; i++){
            if (player[i].getNumberOfCards() > 0){
                cnt += 1;
            }
        }
        return cnt <= 1;
    }

    public void setChallenges(Challenge challenge) {
        for (int i = 0 ; i < 4; i++){
            if (!player[i].equals(challenge.getChallenged())){
                if (player[i].sendChallenge(challenge)){
                    break;
                }
            }
        }
    }

    public void setMutualChallenges(MutualChallenge mutualChallenge){
        for (int i = 0 ; i < 4; i++){
            if (!player[i].equals(mutualChallenge.getChallenged())){
                if (player[i].sendMutualChallenge(mutualChallenge)){
                    break;
                }
            }
        }
    }
}

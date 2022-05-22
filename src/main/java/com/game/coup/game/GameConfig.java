package com.game.coup.game;


import com.game.coup.model.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameConfig {
    String name2;
    Player player = null;
    ArrayList<String> desk;
    public GameConfig() {

        desk = new ArrayList<>();
        desk = (ArrayList<String>) getDeskFile();
//        for (int i = 0 ; i < 3; i++){
//            Card card1 = Card.Ambassador;
//            Card card2 = Card.Assassin;
//            Card card3 = Card.Countess;
//            Card card4 = Card.Captain;
//            Card card5 = Card.Duke;
//            desk.add(card1.getCard());
//            desk.add(card2.getCard());
//            desk.add(card3.getCard());
//            desk.add(card4.getCard());
//            desk.add(card5.getCard());
//        }




        System.out.println("Is it human or bot ? 1)true 2)false ");
        Scanner scanner = new Scanner(System.in);
        int isHuman = scanner.nextInt();
        System.out.println("Choose name : 1)yourSpecificName(if is Human) 2)Greedy 3)Paranoid 4)CoupDetat 5)CautiousKiller ");
        int name = scanner.nextInt();
        if (name == 1) {
            System.out.println("Name : ");
            name2 = scanner.next();
        }
        if (name == 1) player = new Player(name2,"user");
        else if (name == 2) player = new Player("Greedy","FirstBot");
        else if (name == 3) player = new Player("Paranoid","SecondBot");
        else if (name == 4) player = new Player("CoupDetat","ThirdBot");
        else if (name == 5) player = new Player("CautiousKiller","FourthBot");
        player.isHuman = isHuman == 1;
        System.out.println("Choose two cards from desk : ");
        System.out.println(getDeskFile());
        int card1 = scanner.nextInt();
        desk.remove(card1 - 1);
        System.out.println(desk);
        int card2 = scanner.nextInt();
        desk.remove(card2 - 1);
        System.out.println(desk);
        player.addCardToHand(desk.get(card1 - 1));
        player.addCardToHand(desk.get(card2 - 1));
        MakeCardFile(desk);
    }

    public Player getPlayer(){
        return player;
    }
    public void MakeCardFile(ArrayList<String> desk){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Cards", desk);
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\example\\coup\\database\\" + "cards" + ".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}

package com.game.coup.model;



import com.game.coup.game.Game;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    protected ArrayList<String> hand;
    public ArrayList<String> lostCards;
    protected String name;
    public int coin;

    protected String botNumber; //if isHuman == true put "user"

    int card1;
    int card2;
    public boolean isHuman;

    public Player(String name, String botNumber) {
        this.name = name;
        hand = new ArrayList<>();
        lostCards = new ArrayList<>();
        this.botNumber = botNumber;
        coin = 0;
    }


    public String getBotNumber() {
        return botNumber;
    }

    public void setCard1(int card1) {
        this.card1 = card1;
    }

    public void setCard2(int card2) {
        this.card2 = card2;
    }

    public int getCard1() {
        return card1;
    }

    public int getCard2() {
        return card2;
    }

    public String getName() {
        return name;
    }

    public void addCoin(int coin) {
        this.coin += coin;
    }

    public int getCoin() {
        return coin;
    }

    public int getNumberOfCards(){
        return hand.size();
    }
    public void addCardToHand(String card){
        hand.add(card);
    }
    public ArrayList<String> getCardFromHand(){
        return hand;
    }
    public boolean isHuman(){
        return isHuman;
    }

    public void MakeAction(Game game){
        if (getNumberOfCards() == 0){
            return;
        }
        // player chooses an action
        Action currentAction = this.ChooseAction();

        // other player challenge the chosen action
        Challenge challenge = new Challenge(currentAction, this, game);
        game.setChallenges(challenge);
        ChallengeStatus challengeStatus = challenge.ActionEvent();

        // if challenge fails then the player's turn is over
        if (challengeStatus == ChallengeStatus.ChallengeLost){
            // if number of cards is zero, then give all the coins to treasury
            if (this.getNumberOfCards() == 0)
                {
                    game.addCoins(this.coin);
                    this.coin = 0;
                }
            return;
        }


        // take money for the action
        this.takeMoneyForAction(currentAction,game);

        // choose player for the action
        Player adversary=  this.choosePlayerForAction(currentAction,game);
        // mutual action from other players
        MutualChallenge mutualChallenge = new MutualChallenge(this,currentAction,adversary);
        game.setMutualChallenges(mutualChallenge);

        if (mutualChallenge.isMutuallyChallenged()) {
            // get challenges to the mutual action
            Challenge challengeMutual = new Challenge(mutualChallenge.mutualAction ,mutualChallenge.action, mutualChallenge.challenger, game);
            game.setChallenges(challengeMutual);
            ChallengeStatus challengeMutualStatus = challengeMutual.ActionEvent();
            // if the mutual challenge wins or is not challenged, then player loses
            if (challengeMutualStatus != ChallengeStatus.ChallengeLost)
            {
                return;
            }
        }
        // do the action
        this.doAction(currentAction,game,adversary);
    }

    public void doAction(Action currentAction, Game game, Player adversary) {
        if (currentAction == Action.Assassinate){
            String card = adversary.chooseCard();
            adversary.removeFromHand(card);
        }
        else if (currentAction == Action.Taxes){
            this.addCoin(3);
            game.addCoins(-3);
        }
        else if (currentAction == Action.Steal){
            adversary.addCoin(-2);
            this.addCoin(2);
        }
        else if (currentAction == Action.ForeignAid){
            game.addCoins(-2);
            playerCoinUpdate(this, 2);
            this.coin += 2;
        }
        else if (currentAction == Action.Coup){
            String card = adversary.chooseCard();
            adversary.removeFromHand(card);
        }
    }


    public  void takeMoneyForAction(Action currentAction, Game game) {
        if (currentAction == Action.Assassinate){
            if (this.coin > 2) {
                game.addCoins(3);
                playerCoinUpdate(this, -3);
                this.coin -= 3;
            }
        }
        else if (currentAction == Action.Income){
            game.addCoins(-1);
            playerCoinUpdate(this, 1);
            this.coin += 1;
        }
        else if (currentAction == Action.ChanceToChange){
            if (this.coin > 0) {
                game.addCoins(-1);
                playerCoinUpdate(this, -1);
                this.coin -= 1;
            }
        }
        else if (currentAction == Action.Taxes){
            game.addCoins(-3);
            playerCoinUpdate(this, 3);
            this.coin += 3;
        }
//        else if (currentAction == Action.Steal){
//            playerCoinUpdate(this, 2);
//            this.coin += 2;
//        }
//        else if (currentAction == Action.ForeignAid){
//            game.addCoins(-2);
//            playerCoinUpdate(this, 2);
//            this.coin += 2;
//        }
        else if (currentAction == Action.Coup){
            if (this.coin > 6) {
                game.addCoins(7);
                playerCoinUpdate(this, -7);
                this.coin -= 7;
            }
        }
    }

    public Player choosePlayerForAction(Action currentAction, Game game){
        if (currentAction == Action.Assassinate){
            return choosePlayer(game);
        }
        else if (currentAction == Action.Coup){
            return choosePlayer(game);
        }
        else if (currentAction == Action.Steal){
            return choosePlayer(game);
        }
        return null;
    }

    public Player choosePlayer(Game game){return null;}

    public void removeFromHand(String card){
        hand.remove(card);
    }
    public void addToLostCards(String card){
        lostCards.add(card);
    }
    public String chooseCard(){return null;}


    public void playerCoinUpdate(Player player, int i){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Cards", player.getCardFromHand());
        jsonObject.put("Coin", player.getCoin() + i);
        jsonObject.put("isHuman", player.isHuman());
        jsonObject.put("Type", player.isHuman());
        jsonObject.put("BotNumber", player.getBotNumber());
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\game\\coup\\database\\" + player.getName() + ".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Action ChooseAction(){return null;}

    public boolean sendChallenge(Challenge challenge){return true;}

    public boolean sendMutualChallenge(MutualChallenge mutualChallenge) {return true;}


}

package com.game.coup.model;


import com.game.coup.game.Game;

import java.util.Objects;
import java.util.Scanner;

public class Human extends Player{
    Action action;

    int index;

    int whichBot;

    String challenge;

    public Human(String name, String botNumber) {
        super(name,botNumber);
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public boolean sendChallenge(Challenge challenge){
        if (Objects.equals(this.challenge, "No Challenge")) {
            return false;
        }
        else {
            challenge.setChallenger(this);
            return true;
        }
    }

    public boolean sendMutualChallenge(MutualChallenge mutualChallenge) {
        System.out.println("Do you wanna prevent action? 1)PreventAssassination 2)PreventSteal 3)PreventForeignAid 4)No");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (Objects.equals(input, "PreventForeignAid")) {
            mutualChallenge.setChallenger(this);
            mutualChallenge.setMutualAction(MutualAction.PreventForeignAid);
            return true;
        }
        if (Objects.equals(input, "PreventAssassination")) {
            mutualChallenge.setChallenger(this);
            mutualChallenge.setMutualAction(MutualAction.PreventAssassination);
            return true;
        }
        if (Objects.equals(input, "PreventSteal")) {
            mutualChallenge.setChallenger(this);
            mutualChallenge.setMutualAction(MutualAction.PreventSteal);
            return true;
        }

        return false;
    }
    @Override
    public Player choosePlayer(Game game){
        if (getWhichBot() == 5) return null;
        else return game.player[getWhichBot()];
    }

    public void setWhichBot(int whichBot) {
        this.whichBot = whichBot;
    }

    public int getWhichBot() {
        return whichBot;
    }

    @Override
    public String chooseCard(){
        // choose a card to give away
        return hand.get(getIndex());
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Action getAction() {
        return action;
    }
    @Override
    public Action ChooseAction(){
        return getAction();
    }



}

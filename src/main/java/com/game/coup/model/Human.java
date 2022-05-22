package com.game.coup.model;


import com.game.coup.game.Game;

import java.util.Objects;
import java.util.Scanner;

public class Human extends Player{


    public Human(String name) {
        super(name,"");
    }
    public boolean sendChallenge(Challenge challenge){
        System.out.println("Do you wanna Challenge?Y/N");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (Objects.equals(input, "Y")) {
            challenge.setChallenger(this);
            return true;
        }
        else return false;
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
    public Player choosePlayer(Game game){
        //TODO override
        // It should choose a player from the game.
        return null;
    }
    public String chooseCard(){
        //ToDo override
        // choose a card to give away
        return null;
    }
    public Action ChooseAction(){
        // TODO override
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which action do you want to do:1)Income 2)ForeignAid 3)Coup 4)Taxes 5)Assassinate 6)Steal 7)SwapInfluence");
        int actionNumber = scanner.nextInt();
        if (actionNumber == 1) return Action.Income;
        else if (actionNumber == 2) return Action.ForeignAid;
        else if (actionNumber == 3) return Action.Coup;
        else if (actionNumber == 4) return Action.Taxes;
        else if (actionNumber == 5) return Action.Assassinate;
        else if (actionNumber == 6) return Action.Steal;
        else return Action.SwapInfluence;
    }



}

package com.game.coup.model;

import java.util.Scanner;

public class CautiousKiller extends Bot{

    boolean paranoid;

    public CautiousKiller(String name, String botNumber) {
        super(name, botNumber);
    }
    public boolean sendChallenge(Challenge challenge) {
        if (paranoid) {
            challenge.setChallenger(this);
            paranoid = false;
            return true;
        }
        paranoid = true;
        return false;
    }
    @Override
    public boolean sendMutualChallenge(MutualChallenge mutualChallenge)
    {
        return false;
    }

    @Override
    public Action ChooseAction(){
        if (!(this.getCardFromHand().contains("Assassin")) && (this.getCardFromHand().contains("Ambassador"))){
            return Action.SwapInfluence;
        }
        else if (!(this.getCardFromHand().contains("Assassin")) && !(this.getCardFromHand().contains("Ambassador")) && this.coin == 1){
            return Action.ChanceToChange;
        }
        else if (this.coin == 0) return Action.ForeignAid;
        return Action.Income;
    }

}

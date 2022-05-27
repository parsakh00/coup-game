package com.game.coup.model;

import java.util.Scanner;

public class Greedy extends Bot{
    public Greedy(String name,String botNumber) {
        super(name, botNumber);
    }
    @Override
    public boolean sendChallenge(Challenge challenge) {
        return false;
    }
    @Override
    public boolean sendMutualChallenge(MutualChallenge mutualChallenge)
    {
        return false;
    }
    @Override
    public Action ChooseAction(){
        if (this.coin <= 10) return Action.Income;
        else return Action.Coup;
    }
}

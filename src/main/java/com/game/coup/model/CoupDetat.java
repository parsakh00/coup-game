package com.game.coup.model;

import java.util.Scanner;

public class CoupDetat extends Bot{

    public CoupDetat(String name, String botNumber) {
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
        if (this.coin < 7) return Action.Income;
        else return Action.Coup;
    }
}

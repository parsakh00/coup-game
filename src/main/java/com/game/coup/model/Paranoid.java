package com.game.coup.model;

public class Paranoid extends Bot{
    boolean paranoid;

    public Paranoid(String name, String botNumber) {
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
        if (this.coin <= 10) return Action.Income;
        else return Action.Coup;
    }

}

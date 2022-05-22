package com.game.coup.model;

public class Paranoid extends Bot{
    boolean paranoid;

    public Paranoid(String name) {
        super(name);
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

    public boolean sendMutualChallenge(MutualChallenge mutualChallenge)
    {
        return false;
    }
    public Action ChooseAction(){
        return Action.Income;
    }

}

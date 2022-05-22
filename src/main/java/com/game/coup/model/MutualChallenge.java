package com.game.coup.model;

public class MutualChallenge {
    Player challenged;
    Player adversary;
    Player challenger;
    Action action;
    MutualAction mutualAction;
    public MutualChallenge(Player challenged, Action action, Player adversary)
    {
        this.challenged = challenged;
        this.action = action;
        this.adversary= adversary;
    }

    public Player getChallenged() {
        return challenged;
    }

    public boolean setChallenger(Player challenger) {
        if (action == Action.Steal && mutualAction == MutualAction.PreventSteal){
            if (challenger == adversary) {
                this.challenger = challenger;
                return true;
            }
            return false;
        }
        else if (action == Action.Assassinate && mutualAction == MutualAction.PreventAssassination){
            if (challenger == adversary) {
                this.challenger = challenger;
                return true;
            }
            return false;
        }
        else if (action == Action.ForeignAid && mutualAction == MutualAction.PreventForeignAid){
            this.challenger = challenger;
            return true;
        }
        return false;
    }

    public void setMutualAction(MutualAction mutualAction) {
        this.mutualAction = mutualAction;
    }

    public boolean isMutuallyChallenged() {

        return this.challenger != null && this.mutualAction != null;
    }
}

package com.game.coup.model;

public enum Card {
    Duke,
    Assassin,
    Countess,
    Captain,
    Ambassador
    ;
    public String getCard() {
        return this.name();
    }

}

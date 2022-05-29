package com.game.coup.model;
import com.game.coup.game.Game;

public class Bot extends Player{

    public Bot(String name, String botNumber) {
        super(name, botNumber);

    }
    @Override
    public Player choosePlayer(Game game){
        return game.player[(game.turn + 1)%4];
    }
    @Override
    public String chooseCard(){
        return hand.get(0);
    }

}

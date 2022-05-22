package com.game.coup.main;


import com.game.coup.game.Game;
import com.game.coup.model.Player;

public class Main {

    public static void main(String[] args) {
//        GameConfig gameConfig1 = new GameConfig();
//        GameConfig gameConfig2 = new GameConfig();
//        GameConfig gameConfig3 = new GameConfig();
//        GameConfig gameConfig4 = new GameConfig();
//        Game game = new Game(gameConfig1.getPlayer(),gameConfig2.getPlayer(),gameConfig3.getPlayer(),gameConfig4.getPlayer());
        Player player1 = new Player("parsa", "user");
        Player player2 = new Player("Paranoid","FirstBot");
        Player player3 = new Player("CoupDetat","SecondBot");
        Player player4 = new Player("Greedy","ThirdBot");
        Game game = new Game(player1, player2, player3, player4);
        game.start();

    }



}

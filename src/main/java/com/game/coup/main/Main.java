package com.game.coup.main;


import com.game.coup.game.Game;
import com.game.coup.model.Player;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("parsa", "user");
        Player player2 = new Player("Paranoid","FirstBot");
        Player player3 = new Player("CoupDetat","SecondBot");
        Player player4 = new Player("Greedy","ThirdBot");
        Game game = new Game(player1, player2, player3, player4);
        game.start();

    }



}

package com.game.coup.model;

import java.util.Scanner;

public class CoupDetat extends Bot{
    public CoupDetat(String name) {
        super(name);
    }
    public Action ChooseAction(){
        // TODO override
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which action do you want to do:1)Income 2)ForeignAid 3)Coup 4)Taxes 5)Assassinate 6)Steal 7)SwapInfluence");
        int actionNumber = scanner.nextInt();
        if (actionNumber == 1) return Action.Income;
        else if (actionNumber == 2) return Action.ForeignAid;
        else if (actionNumber == 3) return Action.Coup;
        else if (actionNumber == 4) return Action.Taxes;
        else if (actionNumber == 5) return Action.Assassinate;
        else if (actionNumber == 6) return Action.Steal;
        else return Action.SwapInfluence;
    }
}

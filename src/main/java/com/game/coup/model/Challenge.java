package com.game.coup.model;


import com.game.coup.game.Game;

public class Challenge {
    Action action;

    MutualAction mutualAction;
    Player challenger;
    Player challenged;

    Game game;

    public Challenge(Action action, Player challenged, Game game) {
        this.action = action;
        this.challenged = challenged;
        this.game = game;
    }

    public Challenge(MutualAction mutualAction, Action action, Player challenged, Game game) {
        this.action = action;
        this.mutualAction = mutualAction;
        this.challenged = challenged;
        this.game = game;
    }

    public Player getChallenged() {
        return challenged;
    }

    public void setChallenger(Player challenger) {
        this.challenger = challenger;
    }

    public ChallengeStatus ActionEvent(){
        if (action == Action.ChanceToChange){
            changeCardOfHand(challenged);
            return ChallengeStatus.NoChallenge;
        }
        if (challenger == null){
            return ChallengeStatus.NoChallenge;
        }
        if (action == Action.Coup){
            return ChallengeStatus.NoChallenge;
        }
        else if (action == Action.Assassinate){
            return getCardName("Assassin");
        }
        else if (action == Action.ForeignAid){
            return ChallengeStatus.NoChallenge;
        }
        else if (action == Action.Income){
            return ChallengeStatus.NoChallenge;
        }
        else if (action == Action.Steal){
            return getCardName("Captain");
        }
        else if (action == Action.SwapInfluence){
            return getCardName("Ambassador");
        }
        else if (action == Action.Taxes){
            return getCardName("Duke");
        }
        else if (mutualAction == MutualAction.PreventAssassination){
            return getMutualCardName("Contessa");
        }
        else if (mutualAction == MutualAction.PreventForeignAid){
            return getMutualCardName("Duke");
        }
        else if (mutualAction == MutualAction.PreventSteal){
            return getMutualCardName("Ambassador","Captain");
        }

        return ChallengeStatus.ChallengeWon;
    }
    public ChallengeStatus getMutualCardName(String card){
        if (challenged.hand.contains(card)) {
            game.addToDesk(card);
            challenged.removeFromHand(card);
            challenged.addCardToHand(game.removeFromDesk());
            String tmp = challenger.chooseCard();
            challenger.addToLostCards(tmp);
            challenger.removeFromHand(tmp);
            return ChallengeStatus.ChallengeWon;
        }
        else {
            String tmp = challenged.chooseCard();
            challenged.addToLostCards(tmp);
            challenged.removeFromHand(tmp);
            return ChallengeStatus.ChallengeLost;
        }
    }
    public ChallengeStatus getMutualCardName(String card, String card2){
        if (challenged.hand.contains(card) || challenged.hand.contains(card2)) {
            game.addToDesk(card);
            challenged.removeFromHand(card);
            challenged.addCardToHand(game.removeFromDesk());
            String tmp = challenger.chooseCard();
            challenger.addToLostCards(tmp);
            challenger.removeFromHand(tmp);
            return ChallengeStatus.ChallengeWon;
        }
        else {
            String tmp = challenged.chooseCard();
            challenged.addToLostCards(tmp);
            challenged.removeFromHand(tmp);
            return ChallengeStatus.ChallengeLost;
        }
    }

    public void changeCardOfHand(Player challenged){
        if(challenged.coin > 0) {
            String tmp = challenged.chooseCard();
            game.addToDesk(tmp);
            challenged.removeFromHand(tmp);
            challenged.addCardToHand(game.removeFromDesk());
        }

    }
    public ChallengeStatus getCardName(String card){
        if (challenged.hand.contains(card)) {
            game.addToDesk(card);
            challenged.removeFromHand(card);
            challenged.addCardToHand(game.removeFromDesk());
            String tmp = challenger.chooseCard();
            challenger.addToLostCards(tmp);
            challenger.removeFromHand(tmp);
            return ChallengeStatus.ChallengeWon;
        }
        else {
            String tmp = challenged.chooseCard();
            challenged.addToLostCards(tmp);
            challenged.removeFromHand(tmp);
            return ChallengeStatus.ChallengeLost;
        }
    }
//    public void coinStolen(Player player, int i){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("Cards", player.getCardFromHand());
//        jsonObject.put("Coin", player.getCoin() + i);
//        jsonObject.put("isHuman", player.isHuman());
//        jsonObject.put("Type", player.isHuman());
//        try {
//            FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\example\\coup\\database\\" + player.getName() + ".json");
//            file.write(jsonObject.toJSONString());
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}

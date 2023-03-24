package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    public int getPlayerTurnId() {
        return playerTurnId;
    }

    public void setPlayerTurnId(int playerTurnId) {
        this.playerTurnId = playerTurnId;
    }

    public int getPlayer0Score() {
        return player0Score;
    }

    public void setPlayer0Score(int player0score) {
        this.player0Score = player0score;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1score) {
        this.player1Score = player1score;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentDiceValue() {
        return currentDiceValue;
    }

    public void setCurrentDiceValue(int currentDiceValue) {
        this.currentDiceValue = currentDiceValue;
    }

    private int playerTurnId;
    private int player0Score;
    private int player1Score;
    private int currentPlayer;
    private int currentDiceValue;

   public PigGameState() {
        playerTurnId = 0;
        player0Score = 0;
        player1Score = 0;
        currentPlayer = 0;
        currentDiceValue = 1;
    }
    public PigGameState(PigGameState pgs) {
        playerTurnId = pgs.getPlayerTurnId();
        player0Score = pgs.getPlayer0Score();
        player1Score = pgs.getPlayer1Score();
        currentPlayer = pgs.getCurrentPlayer();
        currentDiceValue = pgs.getCurrentDiceValue();
    }
}

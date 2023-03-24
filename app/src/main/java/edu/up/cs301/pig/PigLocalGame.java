package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import java.util.Random;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState pgs;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {

        this.pgs = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {

        if (playerIdx == pgs.getPlayerTurnId()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof PigHoldAction) {
            if (pgs.getPlayerTurnId() == 0) {
                pgs.setPlayer0Score(pgs.getCurrentRunningTotal() + pgs.getPlayer0Score());
            } else {
                pgs.setPlayer1Score(pgs.getCurrentRunningTotal() + pgs.getPlayer1Score());
            }
            alternatePlayer();
            pgs.setCurrentRunningTotal(0);
            return true;
        } else if (action instanceof PigRollAction) {
            Random random = new Random();
            pgs.setCurrentDiceValue(random.nextInt(5) + 1);
            if (pgs.getCurrentDiceValue() == 1) {
                pgs.setCurrentRunningTotal(0);
                alternatePlayer();
            } else {
                pgs.setCurrentRunningTotal(pgs.getCurrentRunningTotal() + pgs.getCurrentDiceValue());
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copyPGS = new PigGameState(pgs);
        p.sendInfo(copyPGS);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if (pgs.getPlayer0Score() >= 50) {
            return this.playerNames[0] + " wins! Score:" + pgs.getPlayer0Score();
        } else if (pgs.getPlayer1Score() >= 50) {
            return this.playerNames[1] + " wins! Score:" + pgs.getPlayer1Score();
        } else {
            return null;
        }
    }

    public void alternatePlayer() {
        if (this.players.length == 1) {
            //do nothing
        } else if (this.players.length == 2) {
            if(pgs.getPlayerTurnId() == 0) {
                pgs.setPlayerTurnId(1);
            } else {
                pgs.setPlayerTurnId(0);
            }
        }
    }

}// class PigLocalGame

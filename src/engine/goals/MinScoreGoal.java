// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import engine.game.Player;


/**
 * This class represents a goal that listens to the player and changes a boolean flag if
 * the player's score has reached a certain value.
 * 
 * @author Sierra Smith
 *
 */
public class MinScoreGoal extends PlayerGoal {

    private int myScoreGoal;

    public MinScoreGoal (Player player, int score) {
        super(player);
        myScoreGoal = score;
    }

    @Override
    protected void checkCondition (Player p) {
       setIsSatisfied(p.getScore() >= myScoreGoal);
    }

}

// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import engine.fieldsetting.Settable;
import engine.game.Player;


/**
 * This class represents a goal that listens to the player and changes a boolean flag if
 * the player's score has reached a certain value.
 *
 * @author Sierra Smith
 *
 */
@Settable
public class MinScoreGoal extends PlayerGoal {

    public static final double DEFAULT_SCORE = 1000;

    private double myScoreGoal;

    public MinScoreGoal () {
        super(new Player());
        myScoreGoal = DEFAULT_SCORE;
    }

    public MinScoreGoal (Player player, int score) {
        super(player);
        myScoreGoal = score;
    }

    @Settable
    public void setScoreTarget (int target) {
        myScoreGoal = target;
    }

    @Override
    protected void checkCondition (Player p) {
       setIsSatisfied(p.getScore() >= myScoreGoal);
    }

}

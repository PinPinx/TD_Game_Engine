// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import engine.fieldsetting.Settable;
import engine.game.Player;


/**
 * This class checks the condition that the score of a given player has reached a certain minimum
 * value. Thus, it requires access to the player that it should listen to and a minimum score that
 * it should check for. An example of when to use this class would be if a game designer wanted a
 * level to end as soon as the player reached a certain goal.
 * 
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

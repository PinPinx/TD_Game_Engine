// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import java.util.Observable;
import engine.fieldsetting.Settable;
import engine.game.Player;


/**
 * This class represents the head of the hierarchy of goals that check a condition related to and
 * listen to a Player object, which it depends on. Thus, a class should extend this class when it
 * encapsulates a condition related to the player, and an example can be found by looking at
 * MinScoreGoal.
 * 
 * @author Sierra Smith
 *
 */
public abstract class PlayerGoal extends Goal {

    private Player myPlayer;

    public PlayerGoal (Player player) {
        myPlayer = player;
        myPlayer.addObserver(this);
    }

    /**
     * Called when the Player updates its observers upon a change and updates the condition based on
     * that change.
     */
    @Override
    public void update (Observable o, Object arg) {
        if (myPlayer.equals(o)) {
            checkCondition(myPlayer);
        }
    }

    /**
     * Rechecks the condition and updates the isSatisfied boolean in the Goal class.
     * 
     * @param p
     */
    protected abstract void checkCondition (Player p);

    /**
     * Sets the player passed in as the Player to observe.
     * 
     * @param p
     */
    @Settable
    public void setPlayer (Player p) {
        myPlayer = p;
    }

}

// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import java.util.Observer;


/**
 * This abstract class is designed to set the framework for encapsulating a condition that can be
 * either satisfied or not satisfied. Any class extending it must implement the Observer interface,
 * as Goals are intended to observe various objects to which the condition pertains. There is an
 * assumption that the "isSatisfied" boolean will always be updated and reflect the current status
 * of the condition, as the method to check the status of the condition returns the boolean that
 * stores the current status. An example of how to implement this class can be found by looking at
 * the PlayerGoal and it's child class MinScoreGoal.
 * 
 * 
 * @author Sierra Smith
 * @author Cosette Goldstein
 *
 */

public abstract class Goal implements Observer {

    private boolean isSatisfied;

    /**
     * Returns true if the condition encapsulated in this class is currently met/satisfied, or false
     * if it is not.
     * 
     * @return
     */
    public boolean isSatisfied () {
        return isSatisfied;
    }

    /**
     * Sets the boolean to the received value
     * 
     * @param satisfied
     */
    protected void setIsSatisfied (boolean satisfied) {
        isSatisfied = satisfied;
    }

}

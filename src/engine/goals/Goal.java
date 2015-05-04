// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import java.util.Observer;


/**
 * This abstract class is designed to set the framework for encapsulating a condition that can be
 * either satisfied or not satisfied. It
 * 
 * This class observes some GameObject and changes a field once a particular condition is met
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
     * Sets the boolean 
     * @param satisfied
     */
    protected void setIsSatisfied (boolean satisfied) {
        isSatisfied = satisfied;
    }

}

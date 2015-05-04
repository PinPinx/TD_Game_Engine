// This entire file is part of my masterpiece.
// Sierra Smith

package engine.goals;

import java.util.Observable;


/**
 * This class extends the goal class to implement an empty check condition. It was designed to be
 * used when something depends upon a goal but the designer doesn't have a condition in mind that
 * they want to check, so the condition always evaluates to true.  
 * 
 * @author Sierra Smith
 *
 */
public class NullGoal extends Goal {

    public NullGoal () {
        setIsSatisfied(true);
    }

    @Override
    public void update (Observable o, Object arg) {
        // does nothing, because observes nothing
    }

}

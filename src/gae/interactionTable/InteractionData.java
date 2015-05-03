// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import java.util.List;
import java.util.Map;

import engine.gameobject.labels.Type;
import engine.interactions.Interaction;
import engine.interactions.InteractionEngine;

/**
 * InteractionData represents the interface that will be implemented by a concrete class to hold and
 * handle all of the back end data for an interaction table. This interface is used to define all
 * the public methods that will be used to define InteractionData's interactions with other classes
 * and interfaces.
 * 
 * @author Brandon Choi
 *
 */

public interface InteractionData {
    
    /**
     * returns the user-friendly name of interactions mapped to the actual classes they represent in
     * the engine code
     * 
     * @return
     */
    Map<String, Class<? extends Interaction>> getInteractionMap ();

    /**
     * returns all possible InteractionEngines that the data class is holding in case a user wanted
     * to access all of them at the same time
     * 
     * @return
     */
    List<? extends InteractionEngine> getAllEngines ();

    /**
     * returns the CollisionEngine as an InteractionEngine type
     * 
     * @return
     */
    InteractionEngine getCollisionEngine ();

    /**
     * returns the RangeEngine as an InteractionEngine type
     * 
     * @return
     */
    InteractionEngine getRangeEngine ();

    /**
     * Sets the interaction i between all Types in list1 and list2
     * 
     * @param list1
     * @param i
     * @param list2
     */
    void addInteraction (List<? extends Type> list1, Interaction i, List<? extends Type> list2);
}

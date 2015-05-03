// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import javafx.scene.Node;

/**
 * InteractionUtility represents any helper class used by an InteractionTable or any component of
 * InteractionTable. Objects such as DropDown or ObjectContainer represent implementations of
 * InteractionUtility as they both are classes used to help construct InteractionInstance.
 * 
 * @author Brandon Choi
 *
 */
public interface InteractionUtility {

    /**
     * returns the most outer Node that represents the InteractionUtility
     * 
     * @return
     */
    Node getUtilityObject ();
}

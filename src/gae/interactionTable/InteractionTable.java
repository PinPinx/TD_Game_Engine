// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import javafx.scene.Node;

/**
 * InteractionTable contains multiple instances of InteractionInstances. Each level of the game will
 * have its own InteractionTable in order to customize interactions per level, if desired. 
 * 
 * @author Brandon Choi
 *
 */
public interface InteractionTable {
    /**
     * returns the outermost container node for the entire interaction table
     * 
     * @return
     */
    Node getInteractionTable ();

    /**
     * returns the interaction table's specific InteractionData.
     * 
     * @return
     */
    InteractionData getInteractionData ();
}

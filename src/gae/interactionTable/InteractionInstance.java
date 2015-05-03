// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import javafx.scene.Node;

/**
 * InteractionInstance defines the UI component that handles the creation of a single interaction
 * between two lists of Types. An InteractionTable can hold multiple InteractionInstances when
 * creating more than one interaction.
 * 
 * @author Brandon Choi
 *
 */
public interface InteractionInstance {
    /**
     * returns the outermost Node that contains the entire InteractionInstance
     * 
     * @return
     */
    Node getInteractionInstance ();
}

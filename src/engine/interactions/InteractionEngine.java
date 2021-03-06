package engine.interactions;

import engine.gameobject.GameObject;
import engine.gameobject.labels.Type;
import gameworld.GameWorld;


/**
 *
 * @author Jeremy
 *
 */
public interface InteractionEngine {
    
    /**
     * Gets the first object to act upon the second object
     * @param first The acting object
     * @param second What the first object is acting upon 
     */
    public void interact (GameObject first, GameObject second);

    public void put (Type first,
                     Type second,
                     Interaction interaction);

    public void setWorld (GameWorld world);
}

package engine.interactions;

import engine.gameobject.GameObject;
import engine.gameobject.labels.Label;
import gameworld.GameWorld;


/**
 *
 * @author Jeremy
 *
 */
public interface InteractionEngine {
    public void interact (GameObject first, GameObject second);

    public void put (Label first,
                     Label second,
                     Interaction interaction);

    public void setWorld (GameWorld world);
}

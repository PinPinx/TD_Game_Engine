// This entire file is part of my masterpiece.
// Danny Oh
package engine.gameobject.units;

import engine.gameobject.GameObject;

public interface iBuff {
    
    /**
     * Applies initial effect to myUnit
     *
     * @param myUnit
     */
    public abstract void apply (GameObject myUnit);

    /**
     * Unapplies the initial effect
     *
     * @param myUnit
     */
    public abstract void unapply (GameObject myUnit);

    /**
     * What the buff does each timeunit
     *
     * @param timePassed
     * @param myUnit
     */
    public void advanceTime (int timePassed, GameObject myUnit);
    
}

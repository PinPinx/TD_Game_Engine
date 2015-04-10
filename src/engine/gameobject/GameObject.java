package engine.gameobject;

import engine.gameobject.weapon.Weapon;

import engine.shop.PurchasableGameObject;
import engine.shop.Tag;
import gameworld.ObjectCollection;


/**
 * 
 * @author Jeremy, Kaighn
 *
 */

public interface GameObject extends Movable, Health, Graphical, PurchasableGameObject {
    // public void updateGraphics ();//cannot implement yet
   
    /**
     * Updates the object accordingly within the objectcollection (usually gameworld) given
     * @param world
     */
    public void update(ObjectCollection world);
    
    /**
     * Sets the GameObject's Weapon
     */
    public void setWeapon (Weapon weapon);

    /**
     * Returns the GameObject's Weapon
     */
    public Weapon getWeapon ();

    /**
     * Labels allow the GameEngine to differentiate
     * 
     * @return: A string
     */
    public String getLabel ();

    /**
     * Tags contain important GameObject info (e.g. name, description, image)
     * 
     * @return
     */
    public Tag getTag ();

    /**
     * Creates an identical game object (with different reference).
     */
    public GameObject clone ();

    /**
     * Returns the Cartesian coordinate of the game object.
     */
    public PointSimple getPoint ();

    public void setPoint (PointSimple point);

    /**
     * Sets the GameObject's Weapon
     */
    public void setMover (Mover mover);

    /**
     * Returns object's mover
     */
    public BasicMover getMover ();

}

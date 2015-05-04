// This entire file is part of my masterpiece.
// Danny Oh
package engine.gameobject.units;

import java.awt.Color;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.labels.SimpleType;
import engine.gameobject.labels.Type;
/**
 * Buff that changes the target's type
 * @author myungoh
 *
 */
public class ZombieBuff extends Buff{
    private final int[] RGBVal = {46, 139, 87};
    private Type myType;
    private final static int INFINITE_DURATION = 10000;
    
    public ZombieBuff () {
        super(INFINITE_DURATION);
        myType = new SimpleType();
    }

    public ZombieBuff (Type type){
        this();
        myType = type;
    }
    
    public void setType(Type type){
        myType = type;
    }
    
    @Override
    public void upgrade (Upgrade decorated) {
        //You can't upgrade ZombieBuff?
    }

    @Override
    public void apply (GameObject myUnit) {
        myUnit.setLabel(myType);
        float[] hsbvals = RGBtoHSB(RGBVal);
        adjustEffect(myUnit, -hsbvals[0], -hsbvals[1], -hsbvals[2], 0);
    }

    @Override
    public void unapply (GameObject myUnit) {
        //Do nothing
    }

    protected void changeOverTime(GameObject myUnit){
        myUnit.changeHealth(-1);//Zombies aren't immortal, die over time
    }
    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return true;//ZombieBuffs override each other every time
    }

    @Override
    public Buff clone () {
        return new ZombieBuff(myType);
    }
    
    private float[] RGBtoHSB(int[] RGBVals){
        return Color.RGBtoHSB(RGBVal[0],RGBVal[1],RGBVal[2], null);
    }

}

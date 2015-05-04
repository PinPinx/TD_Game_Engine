// This entire file is part of my masterpiece.
// Danny Oh

package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;

@Settable
public abstract class BasicMover implements Mover {
    private double inherentSpeed;
    private double speedModifier;
    private double myDistance;
    private boolean frozen;

    public BasicMover () {
        this(0);
    }

    public BasicMover (double speed) {
        myDistance = 0;
        inherentSpeed = speed;
        frozen = false;
        speedModifier = 1;
    }

    @Override
    public abstract PointSimple move (PointSimple current) throws EndOfPathException;

    @Settable
    @Override
    public void setSpeed (double speed) {
        inherentSpeed = speed;
    }
    
    public double getSpeed(){
        return inherentSpeed;
    }
    
    public void setFreeze (boolean frozen) {
        this.frozen = frozen;
    }

    public void speedBuff (double percentage) {
        speedModifier = speedModifier + percentage/100;
    }

    @Override
    public abstract Mover clone ();

    protected double currentSpeed () {
        return (frozen ? 0 : 1) * inherentSpeed * speedModifier;
    }
    
    protected double getDistance() {
        return myDistance;
    }
    
    protected void setDistance(double distance) {
        myDistance = distance;
    }
}

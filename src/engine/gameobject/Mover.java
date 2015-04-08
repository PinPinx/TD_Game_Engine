package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;

/**
 * A strategy that is used by an object in cartesian space to find its next desired position.
 * @author Kaighn
 *
 */
public interface Mover {
    public PointSimple move(PointSimple current) throws EndOfPathException;
    
    @Settable
    public void setSpeed(double speed);
}
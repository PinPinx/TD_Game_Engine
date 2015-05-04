// This entire file is part of my masterpiece.
// Danny Oh
package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


@Settable
public class MoverUser extends BasicMover {
    private DirectionHandler myHandler;
    
    public MoverUser(double speed) {
        setSpeed(speed);
        myHandler = new DirectionHandler();
    }

    public MoverUser () {
        this(1);
    }

    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        PointSimple direction = myHandler.getDirection();
        return current.add(direction.multiply(currentSpeed()));
    }

    @Settable
    public void setGraphic (Graphic graphic) {
        myHandler.setGraphic(graphic);
    }

    @Override
    public Mover clone () {
        MoverUser mover =  new MoverUser(getSpeed());
        mover.myHandler = myHandler.clone();
        return mover;
    }
}

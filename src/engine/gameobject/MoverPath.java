package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;
import engine.pathfinding.PathFixed;


/**
 * A mover that moves according to a pathfinder.
 * 
 * @author Kaighn
 *
 */
@Settable
public class MoverPath extends BasicMover {
	Path myPath;
	
	public MoverPath() {
	    super(0);
	    this.setSpeed(1);
	    myPath = new PathFixed();
	}
	
	public MoverPath(Path pf, double speed){
	        super(speed);
		myPath = pf;
	}
	
	 /**
	  * Moves according to path i.e. returns correct point on the path
	  */
	@Override
	public PointSimple move(PointSimple current) throws EndOfPathException{
	        setDistance(getDistance() + currentSpeed());
	        return myPath.getNextLocation(getDistance(), currentSpeed(), current);
	}
	
	@Settable
	public void setPath(Path path) {
	    myPath = path;
	}
	
	@Settable
	public void setDistance(double distance) {
	    super.setDistance(distance);
	}
	
	/**
	 * This sets the mover such that it will pick up on the path the spawner spawned this at. 
	 */
	@Override
	public Mover clone(){
	    MoverPath clone = new MoverPath(myPath, getSpeed());
	    clone.setDistance(this.getDistance());
	    return clone;
	}
	
	@Settable
	@Override
	public void setSpeed(double speed) {
	    super.setSpeed(speed);
	}
	
}

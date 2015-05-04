// This entire file is part of my masterpiece.
// Kaighn Kevlin kgk6

package engine.pathfinding;

import java.util.LinkedList;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;


/**
 * A path to represent a fixed path.
 *
 * @author Kaighn
 *
 */
@Settable
public class PathFixed implements Path {
    private List<PathSegment> myPathSegments;

    public PathFixed () {
        myPathSegments = new LinkedList<>();
    }

    @Override
    public void addPathSegment (PathSegment ps) {
        myPathSegments.add(ps);
    }

    @Override
    public PointSimple getNextLocation (double distance, double speed, PointSimple current, int pathIndex)                                                                                throws EndOfPathException {
        double distanceCount = 0;
        for (PathSegment seg : myPathSegments) {
            distanceCount += seg.getLength();
            if (distanceCount >= distance) {
                return seg.getPoint(distance - (distanceCount - seg.getLength()));
            }
        }
        throw new EndOfPathException();
    }

    @Settable
    public void setPath (LinkedList<PathSegment> pathSegments) {
        myPathSegments = pathSegments;
    }

    @Override
    public void updatePath () {
    }

}

// This entire file is part of my masterpiece.
// Kaighn Kevlin kgk6

package engine.pathfinding;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import engine.gameobject.PointSimple;

public class PathComposite implements Path {
	private List<Path> myPaths;

	public PathComposite() {
		myPaths = new ArrayList<>();
	}

	public void addPath(Path p) {
		myPaths.add(p);
	}

	@Override
	public PointSimple getNextLocation(double distance, double speed,
			PointSimple current, int pathIndex) throws EndOfPathException {
		if(pathIndex < myPaths.size()){
			return myPaths.get(pathIndex).getNextLocation(distance, speed, current, pathIndex);
		}
		else{
			throw new EndOfPathException();
		}

	}

	@Override
	public void addPathSegment(PathSegment ps) {
		if(myPaths.size() > 0){
			myPaths.get(myPaths.size()-1).addPathSegment(ps);
		}
	}

	@Override
	public void updatePath() throws NoPathExistsException {
		for(Path p : myPaths){
			p.updatePath();
		}
	}

}

package engine.pathfinding;

import java.util.List;

import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import voogasalad.util.pathsearch.wrappers.GridWrapper;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.CoordinateTransformer;


public class PathFinder implements Path {
	GridWrapper myAlgorithm;
	List<GridCell> myPath;
	CoordinateTransformer myTrans;

	public PathFinder(CoordinateTransformer cTrans){
		 myAlgorithm = new GridWrapper();
		 myTrans = cTrans;
	}
	
	public void updatePath(GameObject[][] objectMatrix) throws NoPathExistsException{
		myAlgorithm.initializeGraph(objectMatrix, go -> go != null);
		myPath = myAlgorithm.shortestPath(0, 0, 10, 10);
	}
	
	@Override
	public PointSimple getNextLocation(double distance, PointSimple current)
			throws EndOfPathException {
		GridCell currentCell = myTrans.transformWorldToGrid(current);
		for(GridCell c : myPath){
			if()
		}
	}

	@Override
	public void addPathSegment(PathSegment ps) {
		// TODO redo inheritance hierarchy so this isn't a method
	}
	
}

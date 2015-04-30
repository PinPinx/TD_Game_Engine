package gameworld;

import engine.gameobject.PointSimple;
import voogasalad.util.pathsearch.graph.GridCell;

public class GridCellFromPoint extends GridCell {
	CoordinateTransformer myTrans;
	PointSimple myPoint;
	
	public GridCellFromPoint(CoordinateTransformer t, PointSimple p){
		super(t.transformWorldToGrid(p).getRow(), t.transformWorldToGrid(p).getCol());
		myTrans = t;
		myPoint = p;
	}
	
	public GridCellFromPoint(int row, int col) {
		super(row, col);
	}
	
	@Override
	public int getRow(){
		System.out.println(myTrans.transformWorldToGrid(myPoint).getRow());
		System.out.println(myPoint);
		return myTrans.transformWorldToGrid(myPoint).getRow();
	}
	
	@Override
	public int getCol(){
		return myTrans.transformWorldToGrid(myPoint).getCol();
	}
}

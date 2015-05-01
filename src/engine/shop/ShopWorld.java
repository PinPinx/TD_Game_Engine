package engine.shop;

import javafx.scene.Node;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.StructurePlacementException;


public interface ShopWorld {
    public void addObject (GameObject toSpawn, PointSimple pixelCoords)
                                                                       throws StructurePlacementException;

    public boolean isPlaceable (Node n, PointSimple pixelCoords);

    public GameObject getObjectFromNode (Node n);

    public void removeObject (GameObject toRemove);
}

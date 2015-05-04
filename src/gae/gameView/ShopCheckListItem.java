// This entire file is part of my masterpiece.
// NINA SUN

package gae.gameView;

import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
import engine.gameobject.Purchasable;
import gae.backend.Placeable;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * Creates a checklist item that holds a placeable to be added to a ShopCheckList
 * 
 * @author Nina Sun
 */
public class ShopCheckListItem implements CheckListItem {
    public static final int IMAGE_HEIGHT = 50;
    public static final String PRICE_ERROR = "No weapon value found";
    private Placeable placeable;
    private CheckBox checkbox;

    public ShopCheckListItem (Placeable obj) {
        placeable = obj;
        checkbox = new CheckBox();
    }

    /**
     * Returns the node to be added to the checklist, showing a thumbnail, information, and checkbox
     * 
     * @author Nina Sun
     */
    public Node getNode () {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Graphic graphic = placeable.getGraphic().clone();
        graphic.setHeight(IMAGE_HEIGHT);
        Node image = graphic.getResizedGraphic(1);
        hbox.getChildren().addAll(image, createPlaceableInfo(), checkbox);
        return hbox;
    }

    public BooleanProperty getSelectedProperty () {
        return checkbox.selectedProperty();
    }

    /**
     * Returns placeable that the item holds
     * 
     * @return list
     */
    public Placeable getPlaceable () {
        return placeable;
    }

    /**
     * Returns a VBox of information about the placeable for the shop: name, description, price
     * 
     * @return node
     */
    private Node createPlaceableInfo () {
        Label label = new Label(placeable.getName());
        Text description = new Text(placeable.getDescription());
        return new VBox(label, description, createPriceInfo());
    }

    /**
     * Returns the price tag for the item in the shop
     * 
     * @return node
     */
    private Node createPriceInfo () {
        String value;
        try {
            value = Double.toString(placeable.getWeapon().getValue());
        }
        catch (Exception e) {
            value = PRICE_ERROR;
        }
        return new Text(value);
    }

}

// This entire file is part of my masterpiece.
// NINA SUN

package gae.gameView;

import engine.gameobject.labels.Type;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/**
 * CheckListItem that is created with a game engine label to be added to a TypeCheckList
 *
 * @author Nina Sun
 */
public class TypeCheckListItem implements CheckListItem {
    private Type label;
    private CheckBox checkbox;

    public TypeCheckListItem (Type obj) {
        label = obj;
        checkbox = new CheckBox();
    }

    /**
     * Returns an HBox with the name of the label and a checkbox
     * 
     * @return hbox
     */
    @Override
    public Node getNode () {
        HBox root = new HBox();
        Text text = new Text(label.getName());
        root.getChildren().addAll(text, checkbox);
        return root;
    }

    @Override
    public BooleanProperty getSelectedProperty () {
        return checkbox.selectedProperty();
    }

    /**
     * Returns the label that the item holds
     * 
     * @return type
     */
    public Type getLabel () {
        return label;
    }

}

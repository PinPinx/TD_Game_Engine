// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Creates a drop down combobox from a List of options and a label. Used in InteractionTable in
 * InteractionInstance to display all the interactions possible given by the game engine.
 * 
 * @author Brandon Choi
 *
 */

public class DropDown implements InteractionUtility {
    private VBox container;
    private Label label;
    private ComboBox<String> choices;

    public DropDown (String n, List<String> options) {
        container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setId("interactionOptions");
        label = new Label(n);
        choices = new ComboBox<>();
        createDropDown(options);
    }

    @Override
    public Node getUtilityObject () {
        return container;
    }

    /**
     * returns the selected string from the combobox
     * 
     * @return
     */
    public String getSelected () {
        return choices.getSelectionModel().getSelectedItem();
    }

    /**
     * fills the combobox and adds the label and the combobox together to the VBox container
     * 
     * @param options
     */
    private void createDropDown (List<String> options) {
        options.forEach(e -> choices.getItems().add(e));
        container.getChildren().addAll(label, choices);
    }
}

// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import gae.listView.LibraryData;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Table that will allow the user to define specific interactions between game objects such as what
 * happens when a tower and enemy collide, etc.
 *
 * @author Brandon Choi
 *
 */

public class InteractionTableSimple implements InteractionTable {

    private static final int PREF_SPACING = 35;
    private static final String ADD_TEXT = "Add New Interaction";

    private InteractionData myInteractionData;
    private LibraryData myLibraryData;
    private BorderPane container;
    private ScrollPane scroller;
    private VBox content;
    private Button adder;
    private List<InteractionInstance> interactions;

    public InteractionTableSimple () {
        myInteractionData = new InteractionDataSimple();
        myLibraryData = LibraryData.getInstance();
        container = new BorderPane();
        scroller = new ScrollPane();
        container.setCenter(scroller);
        adder = new Button(ADD_TEXT);
        container.setTop(adder);
        interactions = new ArrayList<>();
        content = new VBox(PREF_SPACING);
        scroller.setContent(content);
        setUpButtons();
    }

    @Override
    public Node getInteractionTable () {
        return container;
    }

    @Override
    public InteractionData getInteractionData () {
        return myInteractionData;
    }

    /**
     * Sets up Buttons and their pressed functions. When the add button is pressed, a new
     * InteractionInstance is added to the table.
     */
    private void setUpButtons () {
        adder.setOnMouseClicked(e -> {
            InteractionInstance i = new InteractionInstanceSimple(myInteractionData,
                                                                        myLibraryData);
            interactions.add(i);
            content.getChildren().add(i.getInteractionInstance());
        });
    }
}

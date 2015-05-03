// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import gae.gameView.LabelCheckList;
import gae.listView.LibraryData;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents one side of the InteractionInstance. The types available will be shown in a container
 * node and can be checked to indicate types being used to set the interaction.
 * 
 * @author Brandon Choi
 *
 */
public class TypeContainer implements InteractionUtility {
    private static final String PROMPT_TEXT = "Add Types";
    private static final int PREF_SPACING = 15;
    private static final int PREF_SIZE = 25;
    
    private VBox container;
    private VBox selected;
    private ScrollPane scroller;
    private Button adder;
    private HBox addBox;
    private LabelCheckList myChecker;
    private LibraryData myLibraryData;

    public TypeContainer (LibraryData library) {
        container = new VBox();
        selected = new VBox();
        scroller = new ScrollPane();
        adder = new Button();
        addBox = new HBox(PREF_SPACING);
        Text addText = new Text(PROMPT_TEXT);
        myLibraryData = library;
        myChecker = new LabelCheckList(myLibraryData.getLabelSet());
        container.setId("interactionBox");
        addBox.getChildren().addAll(addText, adder);
        setUpAddButton();
        createObjectContainer();
    }

    @Override
    public Node getUtilityObject () {
        return scroller;
    }

    /**
     * Modifies the graphic on the add button and the button size
     */
    private void setUpAddButton () {
        ImageView buttonGraphic = new ImageView("/images/plus_sign.jpg");
        adder.setGraphic(buttonGraphic);
        buttonGraphic.setFitWidth(PREF_SIZE);
        buttonGraphic.setFitHeight(PREF_SIZE);
    }
    
    /**
     * returns the label check list in the container
     * 
     * @return
     */
    public LabelCheckList getCheckList () {
        return myChecker;
    }

    /**
     * creates the object container by adding nodes to the VBox and setting up functionalities such
     * as button pressing
     */
    private void createObjectContainer () {
        scroller.setContent(container);
        adder.setOnMouseClicked(e -> {
            myChecker.showCheckList();
        });
        container.getChildren().addAll(selected, addBox);
    }
}

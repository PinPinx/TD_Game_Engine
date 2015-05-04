// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package gae.listView;

import java.util.function.Consumer;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;


/**
 * Concrete implementation of IPaneListView. Implements front-end features to new TitledPanes that
 * come in and returns the higher-level TitledPane (there are clickable-TitledPanes under
 * TitledPanes for PaneList).
 * 
 * @author Kei
 *
 */
public class PaneListView implements IPaneListView {
    private Group root;
    private Pane pane;
    private String type;
    private ObservableList<TitledPane> paneList;
    private Consumer<TitledPane> consumer;

    /**
     * @param root: Group that all the placed objects exist
     * @param pane: Pane that the group along with other front-end components reside in
     * @param scene: The current scene
     * @param type: The type of object these PaneList classes are dealing with (ie Tower).
     * @param consumer: Takes in a TitledPane and passes in the Authorable object to the back-end
     */
    public PaneListView (Group root,
                         Pane pane,
                         Scene scene,
                         String type, Consumer<TitledPane> consumer) {
        this.root = root;
        this.pane = pane;
        this.type = type;
        root.setManaged(false);
    }

    /**
     * Removing root from the pane
     */
    @Override
    public void removeRoot () {
        pane.getChildren().remove(root);
    }

    /**
     * Adding root to the pane
     */
    @Override
    public void addRoot () {
        if (!pane.getChildren().contains(root)) {
            pane.getChildren().add(root);
        }
    }

    /**
     * Returning the TitledPane that the outside classes need
     */
    @Override
    public TitledPane getTitledPane () {
        TitledPane pane = new TitledPane();
        pane.setText(type);
        Accordion accordion = new Accordion();
        pane.setContent(accordion);
        paneList = accordion.getPanes();
        return pane;
    }

    /**
     * Updates the higher-level TitledPane observable list, as well as implement a feature that
     * allows for the creation of a new instance by right-clicking on the TitledPane. Uses the
     * consumer to notify the back-end that an instance had been added.
     */
    @Override
    public void updateTitledPaneList (TitledPane pane) {
        pane.getStyleClass().add("inner-titled-pane");
        pane.setOnMousePressed(me -> {
            if (me.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("New");
                item.setOnAction(ae -> {
                    consumer.accept(pane);
                });
                contextmenu.getItems().add(item);
                contextmenu.show(pane, me.getSceneX(), me.getSceneY());
            }
        });
        paneList.add(pane);
    }

}

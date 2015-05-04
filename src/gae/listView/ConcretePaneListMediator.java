// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package gae.listView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import gae.gridView.ContainerWrapper;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;


/**
 * This specific concrete Mediator class is specifically for the communication between the front-end
 * and back-end of the PaneList, which displays different types of objects in an accordion that
 * contains click-able TitledPanes. The front and back-end classes do not know about each other's
 * existence and only interacts with the mediator. This mediator also prevents other classes from
 * accessing any other features and information as they are encapsulated. Used as a way to control
 * data flow between the two classes and to the outside.
 * 
 * @author Kei
 *
 */
public class ConcretePaneListMediator implements IListMediator {
    private IPaneListView view;
    private IPaneListData backend;
    private Scene scene;
    private Map<TitledPane, Authorable> infoPairMap;

    /**
     * Takes in the necessary information to set up the front and back end components
     * 
     * @param root: Group that the placed objects are in
     * @param pane: Pane that the group along with other front-end components reside in scene
     * @param scene
     * @param observableList: ObservableList of Authorable objects that are externally manipulated
     * @param type: The type of object these PaneList classes are dealing with (ie Tower).
     */
    public ConcretePaneListMediator (Group root,
                                     Pane pane,
                                     Scene scene,
                                     ObservableList<Authorable> observableList,
                                     String type) {
        view = new PaneListView(root, pane, scene, type, addToInstanceList());
        backend = new PaneListData(observableList, type, setTitledPaneList());
        this.scene = scene;
        infoPairMap = new HashMap<>();
    }

    /**
     * Given an Authorable and a list of Authorable instances, it calls the front-end to set up
     * TitledPanes. The BiConsumer removes the need for the back-end to have access to the mediator.
     * Notice that the front-end only receives a front-end object.
     * 
     * @return: BiConsumer
     */

    private BiConsumer<Authorable, ObservableList<Authorable>> setTitledPaneList () {
        return ( (authorable, list) -> {
            TitledPane newPane = new TitledPane();
            newPane.setText(authorable.getType());
            newPane.setContent(ListViewUtilities.createList(list, scene, "Editable"));
            infoPairMap.put(newPane, authorable);
            view.updateTitledPaneList(newPane);
        });
    }

    /**
     * Takes a TitledPane object and adds it to an instance list. Uses a map to to find the
     * Authorable object that corresponds to the TitledPane. The consumer removes the need for
     * the back-end to have access to the mediator. Notice that the back-end only receives a
     * back-end object.
     */

    private Consumer<TitledPane> addToInstanceList () {
        return (titledPane -> {
            backend.addToInstanceList(infoPairMap.get(titledPane));
        });
    }

    /**
     * Gives outside classes access to the TitledPane, which is the only thing they would need.
     */
    @Override
    public TitledPane getTitledPane () {
        return view.getTitledPane();
    }

    /*
     * (non-Javadoc)
     * 
     * @see gae.masterpiece.IListMediator#addRoot()
     */
    @Override
    public void addRoot () {
        view.addRoot();
    }

    /*
     * (non-Javadoc)
     * 
     * @see gae.masterpiece.IListMediator#removeRoot()
     */
    @Override
    public void removeRoot () {
        view.removeRoot();
    }
}

// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package gae.listView;

import java.util.Map;
import java.util.function.BiConsumer;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


/**
 * Concrete implementation of IPaneListData. This class keeps all the data and listens to any
 * changes made externally to its lists. If an object is added, it creates a new instance list for
 * the object and only passes out the necessary information, notifying the Mediator (which notifies
 * the front-end class). As far as this class is concerned, it doesn't know that the front-end class
 * exists.
 * 
 * @author Kei
 *
 */
public class PaneListData implements IPaneListData {
    private ObservableList<Authorable> observableList;
    private Map<Authorable, ObservableList<Authorable>> instancesAuthorableMap;
    private String type;
    private BiConsumer<Authorable, ObservableList<Authorable>> biConsumer;

    /**
     * @param observableList: ObservableList of Authorables that is stored externally
     * @param type: The type of the object that this class is storing
     * @param biConsumer: Takes in the Authorable and a new instanceList, which the mediator turns
     *        into a TitledPane to pass to the front-end
     */
    public PaneListData (ObservableList<Authorable> observableList,
                         String type,
                         BiConsumer<Authorable, ObservableList<Authorable>> biConsumer) {
        this.observableList = observableList;
        this.type = type;
        this.biConsumer = biConsumer;
        setUpObservableList();
    }

    /**
     * Given the external observableList, this class listens to see if any change had been made. If
     * an object is added, it calls setUpNewInstanceList.
     */
    private void setUpObservableList () {
        for (Authorable previous : observableList) {
            setUpNewInstanceList(previous);
        }
        observableList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Authorable added = change.getAddedSubList().get(0);
                    setUpNewInstanceList(added);
                }
            }
        });
    }

    /**
     * This method takes in the newly added Authorable object and checks if the type of this class'
     * list matches the Authorable's type. If so, it creates a new instance list and adds to a map.
     * It also passes it to a BiConsumer that the Mediator has, which takes the information and
     * passes only the necessary information to the front-end.
     * 
     * @param authorable: The newly added Authorable object
     */
    private void setUpNewInstanceList (Authorable authorable) {
        if (authorable.getType().equals(type)) { // if object is equal to the same generic type
            ObservableList<Authorable> instanceList =
                    FXCollections.observableArrayList();
            instancesAuthorableMap.put(authorable, instanceList);
            biConsumer.accept(authorable, instanceList);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gae.masterpiece.IPaneListData#addToInstanceList(gae.listView.Authorable)
     */
    @Override
    public void addToInstanceList (Authorable authorable) {
        instancesAuthorableMap.get(authorable).add(authorable.makeNewInstance());
    }
}

// This entire file is part of my masterpiece.
// NINA SUN

package gae.gameView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.labels.Type;
import gae.backend.Placeable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import engine.gameobject.labels.Type;


/**
 * Checklist for types, shown in separate window
 * 
 * @author Nina Sun
 */
public class TypeCheckList extends CheckList {

    private ObservableSet<Type> myObjects;
    private Stage myStage;
    private Scene myScene;

    public TypeCheckList (ObservableSet<Type> objects) {
        super();
        myObjects = objects;
        myStage = new Stage();
        myScene = new Scene((Parent) getCheckList());
        //populate list
        myObjects.forEach(e -> {
            createCheckOption(new TypeCheckListItem(e));
        });     
        addSetListener();
    }

    /**
     * Called by outside class to display the check list
     */
    public void showCheckList () {
        myStage.setScene(myScene);
        myStage.show();
        myStage.centerOnScreen();
    }

    /**
     * Returns the list of labels that has been selected in the checklist
     *
     * @return list of types
     */
    public List<Type> getSelectedLabels () {
        List<Type> list = new ArrayList<>();
        getSelectedItems().forEach(e -> list.add(((TypeCheckListItem) e).getLabel()));
        return list;
    }
    
    /**
     * Updates checklist when new types are added
     *
     */
    private void addSetListener(){
        myObjects.addListener((SetChangeListener.Change<? extends Type> change)->{
            createCheckOption(new TypeCheckListItem(change.getElementAdded()));
            for (CheckListItem key : getMap().keySet()) {
              if (((TypeCheckListItem) key).getLabel().equals(change.getElementRemoved())) {
                  getCheckList().getChildren().remove(key.getNode());
              }
          }
        });
    }

}

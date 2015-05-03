// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import engine.interactions.Interaction;
import gae.listView.LibraryData;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Defines a single interaction between game objects. Concrete implementation of
 * InteractionInstance.
 *
 * @author Brandon Choi
 *
 */

public class InteractionInstanceSimple implements InteractionInstance {

    private static final String CHOOSER_TEXT = "CHOOSE INTERACTION";
    private static final String CREATE_TEXT = "CREATE";
    private static final int PREF_SPACING = 80;
    private InteractionData myInteractionData;
    private HBox container;
    private DropDown interactionType;
    private TypeContainer box1, box2;
    private Button create;

    public InteractionInstanceSimple (InteractionData data, LibraryData library) {
        myInteractionData = data;
        container = new HBox(PREF_SPACING);
        interactionType = new DropDown(CHOOSER_TEXT, getInteractions());
        box1 = new TypeContainer(library);
        box2 = new TypeContainer(library);
        create = new Button(CREATE_TEXT);
        createButtonFunction();
        createInteraction();
    }

    @Override
    public Node getInteractionInstance () {
        return container;
    }

    /**
     * Takes the interaction map from myInteractionData and uses the keyset to pull all of the
     * interaction labels
     *
     * @return
     */
    private List<String> getInteractions () {
        List<String> interactions = new ArrayList<>();
        myInteractionData.getInteractionMap().keySet().forEach(e -> {
            interactions.add(e);
        });
        return interactions;
    }

    /**
     * when create is pressed, the interaction is added to the interaction data by sending the data
     * the two lists of checked types and desired interaction
     */
    private void createButtonFunction () {
        create.setOnMousePressed(e -> {
            Interaction i;
            try {
                i = (Interaction) myInteractionData.getInteractionMap()
                        .get(interactionType.getSelected()).newInstance();

                myInteractionData.addInteraction(box1.getCheckList().getSelectedLabels(), i, box2
                        .getCheckList().getSelectedLabels());
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * adds the two TypeContainers, the DropDown, and the create button into one container node.
     */
    private void createInteraction () {
        container.getChildren().addAll(box1.getUtilityObject(), interactionType.getUtilityObject(),
                                       box2.getUtilityObject(), create);
    }
}

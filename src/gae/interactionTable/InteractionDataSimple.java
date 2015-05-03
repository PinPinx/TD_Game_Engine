// This entire file is part of my masterpiece.
// Brandon Choi

package gae.interactionTable;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import engine.gameobject.labels.Type;
import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;
import engine.interactions.Interaction;
import engine.interactions.InteractionEngine;
import engine.interactions.NoInteraction;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;

/**
 * Concrete implementation of InteractionData. Contains a few helper private methods.
 *
 * @author Brandon Choi
 *
 */

public class InteractionDataSimple implements InteractionData {

    private static final String INTERACTION_PROPERTIES = "gae.interactionTable.interaction_table";

    private List<? extends InteractionEngine> myInteractionEngines;
    private InteractionEngine myCollisions;
    private InteractionEngine myShoots;
    private Map<String, Class<? extends Interaction>> interactionMap;

    public InteractionDataSimple () {
        myCollisions = new CollisionEngine();
        myShoots = new RangeEngine();
        myInteractionEngines = Arrays.asList(myCollisions, myShoots);
        interactionMap = new HashMap<>();
        fillMap();
    }

    @Override
    public Map<String, Class<? extends Interaction>> getInteractionMap () {
        return interactionMap;
    }

    @Override
    public List<? extends InteractionEngine> getAllEngines () {
        return myInteractionEngines;
    }

    @Override
    public InteractionEngine getCollisionEngine () {
        return myCollisions;
    }

    @Override
    public InteractionEngine getRangeEngine () {
        return myShoots;
    }

    @Override
    public void addInteraction (List<? extends Type> list1,
                                Interaction i,
                                List<? extends Type> list2) {
        if (i instanceof BuffImparter) {
            massPut(list1, i, list2, myCollisions);
        }
        else if (i instanceof ShootAt) {
            massPut(list1, i, list2, myShoots);
        }
        else {
            massPut(list1, new NoInteraction(), list2, myCollisions);
            massPut(list2, new NoInteraction(), list2, myShoots);
        }
    }

    /**
     * places all the interactions between list one and two by iterating through each of them and
     * setting all iterations
     *
     * @param one
     * @param i
     * @param two
     * @param engine
     */
    private void massPut (List<? extends Type> one,
                          Interaction i,
                          List<? extends Type> two,
                          InteractionEngine engine) {
        one.forEach(e -> {
            two.forEach(f -> {
                engine.put(e, f, i);
            });
        });
    }

    /**
     * fills the map with the correct corresponding values between strings and interactions by
     * pulling data from interaction_table.properties
     */
    private void fillMap () {
        ResourceBundle rb = ResourceBundle.getBundle(INTERACTION_PROPERTIES);
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String nextString = keys.nextElement();
            interactionMap.put(rb.getString(nextString), deriveClass(nextString));
        }
    }

    /**
     * derives the Class<? extends Interaction> object from a string so that it could later be
     * initialized
     * 
     * @param s
     * @return
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Interaction> deriveClass (String s) {
        Class<? extends Interaction> c = null;
        try {
            c = (Class<? extends Interaction>) Class.forName(s);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
}

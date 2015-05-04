// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package gae.listView;

import javafx.scene.control.TitledPane;


/**
 * An interface designed to be a mediator for the front-end and back-end components of lists used in
 * the Authoring Environment. It encapsulates the way the front-end and the back-end interact with
 * one another.
 * 
 * @author Kei
 *
 */
public interface IListMediator {

    /**
     * Gives other classes the ability to get a TitledPane given the input information. Information
     * are independently processed in the front and back-end classes.
     * 
     * @return
     */
    TitledPane getTitledPane ();

    /**
     * Allows outside classes to add the group (which contains the placed objects) to the grid's
     * pane
     */
    void addRoot ();

    /**
     * Allows outside classes to remove the group (which contains the placed objects) from the
     * grid's pane
     */
    void removeRoot ();
}

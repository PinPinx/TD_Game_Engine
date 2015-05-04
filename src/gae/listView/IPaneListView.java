// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package gae.listView;

import javafx.scene.control.TitledPane;


/**
 * Interface for the view (front-end) of PaneList. Updates the view and returns a TitledPane for
 * external purposes. Encapsulates all other information.
 * 
 * @author Kei
 *
 */
public interface IPaneListView {
    /**
     * Given a TitledPane, this method will add it to an existing list and add front-end features to
     * it
     * 
     * @param pane: a new TitledPane
     */
    void updateTitledPaneList (TitledPane pane);

    /**
     * 
     * @return: TitledPane that holds all the front-end features of the PaneList class
     */
    TitledPane getTitledPane ();

    /**
     * adds the group onto the view
     */
    void addRoot ();

    /**
     * removes the group from the view
     */
    void removeRoot ();
}

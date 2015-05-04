// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package gae.listView;

/**
 * Interface for the data of PaneLists, which contain each type of Authorables (Tower, Enemy, etc.)
 * and displays them in a list
 * 
 * @author Kei
 *
 */
public interface IPaneListData {
    /**
     * Method that adds an Authorable object's new instance to its corresponding list
     */
    void addToInstanceList (Authorable authorable);
}

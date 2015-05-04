// This entire file is part of my masterpiece.
// COSETTE GOLDSTEIN

package player.gamePlayer;

import javafx.scene.Scene;


/**
 * GameScreen is an interface that represents any of the possible screens that can occur during a
 * game such as the opening view, pause screen, and end game screen.
 *
 * @author Brandon Choi, Sierra Smith, Cosette Goldstein
 *
 */
public interface GameScene {
    /**
     * returns the Scene for the stage to show
     *
     * @return
     */
    Scene getScene ();
}

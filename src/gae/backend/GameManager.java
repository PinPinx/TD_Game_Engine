// This entire file is part of my masterpiece.
// John Gilhuly

package gae.backend;

import java.lang.reflect.InvocationTargetException;
import engine.game.Game;


/**
 * Managers the Game instance for the CentralTabView
 * 
 * @author JohnGilhuly
 *
 */

public class GameManager extends Invoker {
    private Game myGame;

    public GameManager (Game gameIn) {
        myGame = gameIn;
    }

    public Game getGame () {
        return myGame;
    }

    public void invokeSettableGameMethod (String string, Object ... parameters)
                                                                               throws ClassNotFoundException,
                                                                               IllegalAccessException,
                                                                               IllegalArgumentException,
                                                                               InvocationTargetException {
        invokeSettableMethod(myGame.getClass(), string, parameters);
    }
}

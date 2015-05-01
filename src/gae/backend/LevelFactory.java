// This entire file is part of my masterpiece.
// John Gilhuly

package gae.backend;

import java.lang.reflect.InvocationTargetException;
import engine.game.Level;
import engine.game.StoryBoard;
import engine.shop.ShopModel;
import gae.editor.EditingParser;
import gameworld.GameWorld;


/**
 * Contains the code to create a new level in the GAE Backend
 * 
 * @author JohnGilhuly
 *
 */

public class LevelFactory extends Invoker {

    public LevelFactory () {
    }

    public Level makeLevel (GameWorld nextWorld, boolean shopSet, ShopModel shopModel, StoryBoard sb)
                                                                                                     throws InstantiationException,
                                                                                                     IllegalAccessException,
                                                                                                     ClassNotFoundException,
                                                                                                     IllegalArgumentException,
                                                                                                     InvocationTargetException {
        Level levelData = null;
        levelData = (Level) Class
                .forName(EditingParser
                        .getInterfaceClasses("engine.fieldsetting.implementing_classes")
                        .get("Level").get(0)).newInstance();

        invokeSettableMethod(levelData.getClass(), "setStoryBoard", sb);
        invokeSettableMethod(levelData.getClass(), "setGameWorld", nextWorld);

        if (!shopSet) {
            invokeSettableMethod(shopModel.getClass(), "setGameWorld", nextWorld);
            shopSet = true;
        }

        return levelData;
    }
}

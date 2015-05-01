// This entire file is part of my masterpiece.
// John Gilhuly

package gae.tabView;

import engine.game.Game;
import engine.game.Level;
import engine.game.StoryBoard;
import engine.shop.ShopModel;
import gae.backend.GameManager;
import gae.backend.LevelFactory;
import gae.gameView.InteractionTable;
import gae.gameWorld.FixedGameWorldFactory;
import gae.gameWorld.FreeGameWorldFactory;
import gae.gameWorld.GameWorldFactory;
import gae.gridView.LevelView;
import gae.levelPreferences.LevelPreferencesEditor;
import gae.listView.LibraryData;
import gae.openingView.UIObject;
import gae.waveeditor.WaveEditor;
import gameworld.AbstractWorld;
import gameworld.FreeWorld;
import gameworld.GameWorld;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import voogasalad.util.pathsearch.graph.GridCell;


/**
 * Central container for the central tab view in the gae editor, and creator of the Game instance
 * for xstreaming
 * 
 * @author JohnGilhuly
 *
 */

public class CentralTabView implements UIObject {
    private BooleanProperty isFreeWorld = new SimpleBooleanProperty();
    private boolean editorInstantiated;
    private boolean shopSet;
    private int levelCount;

    private VBox baseNode;
    private TabPane tabView;
    private Scene scene;
    private ITab hudTab, shopTab, playerTab;
    private LevelView levelView;

    private LibraryData libraryData;
    private GameWorldFactory gameWorldFactory;
    private FreeWorld freeworld;
    private ShopModel shopModel;
    private GameWorld nextWorld;

    private GameManager gameManager;
    private LevelFactory levelFactory;

    public CentralTabView (Scene sceneIn, Game gameIn, String gameTypeIn) {
        scene = sceneIn;
        gameManager = new GameManager(gameIn);

        try {
            initialize(gameTypeIn);
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void initialize (String gameTypeIn) throws ClassNotFoundException,
                                               IllegalAccessException, IllegalArgumentException,
                                               InvocationTargetException {
        levelFactory = new LevelFactory();
        libraryData = LibraryData.getInstance();
        levelCount = 1;
        shopSet = false;
        gameWorldFactory = createGameWorldFactory(gameTypeIn);

        createUI();
        shopModel = ((ShopTab) shopTab).getShop();
        gameManager.invokeSettableGameMethod("setShop", shopModel);
        gameManager.invokeSettableGameMethod("setPlayer", ((PlayerTab) playerTab).getPlayer());
    }

    /**
     * Creates central UI Elements
     */
    private void createUI () {
        baseNode = new VBox();
        tabView = new TabPane();

        shopTab = new ShopTab();
        hudTab = new HudEditorTab(null);
        playerTab = new PlayerTab();

        tabView.getTabs().addAll(shopTab.getBaseTabNode(), hudTab.getBaseTabNode(),
                                 playerTab.getBaseTabNode());

        Button newLevel = new Button("Add Level");
        newLevel.setOnAction(e -> createLevelButtonPressed());

        baseNode.getChildren().addAll(newLevel, tabView);
    }

    /**
     * Called when the createLevel Button is pressed
     */
    private void createLevelButtonPressed () {
        if (!editorInstantiated) {
            GameObjectEditorTab gameObjectTab = new GameObjectEditorTab(scene, getConsumer(),
                                                                        getBiconsumer());
            tabView.getTabs().add(gameObjectTab.getBaseTabNode());
            editorInstantiated = true;
        }

        try {
            createNewLevel();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Creates the GameWorldFactory that will be used for creating GameWorlds later on
     * 
     * @param gameTypeIn
     * @return
     */
    private GameWorldFactory createGameWorldFactory (String gameTypeIn) {
        if (gameTypeIn != null && gameTypeIn.equals("Free Path")) {
            return new FreeGameWorldFactory();
        }
        else {
            return new FixedGameWorldFactory();
        }
    }

    /**
     * Creates a new level using reflection and sets all of the necessary dependencies
     * 
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void createNewLevel () throws InstantiationException, IllegalAccessException,
                                  ClassNotFoundException, IllegalArgumentException,
                                  InvocationTargetException {
        GameWorld newWorld = createWorld();

        Tab newTab = new Tab("Level:" + levelCount++);
        newTab.setContent(createLevelTabSet(newWorld).getBaseNode());
        newTab.setClosable(false);

        tabView.getTabs().add(newTab);
        ((HudEditorTab) hudTab).setBackgroundImage(new Image(levelView.getBackgroundImagePath()
                .getValue()));
    }

    /**
     * Creates a new LevelTabSet
     * 
     * @param newWorld
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     */
    private LevelTabSet createLevelTabSet (GameWorld newWorld) throws InstantiationException,
                                                              IllegalAccessException,
                                                              ClassNotFoundException,
                                                              InvocationTargetException {
        LevelPreferencesEditor prefs = new LevelPreferencesEditor();
        WaveEditor waves = createLevelAndWaveObjects(newWorld, prefs);
        InteractionTable iTable = new InteractionTable();

        ((AbstractWorld) newWorld).setCollisionEngine(iTable.getData().getCollisionEngine());
        ((AbstractWorld) newWorld).setRangeEngine(iTable.getData().getRangeEngine());

        LevelTabSet newLevel =
                new LevelTabSet(levelView.getBorder(scene), waves.getObject(), iTable.getTable(),
                                prefs.getObject());
        return newLevel;
    }

    /**
     * Uses the gameWorldFactory to create a new World and initialize it
     * 
     * @return
     */
    private GameWorld createWorld () {
        isFreeWorld.set(false);
        levelView = new LevelView(setSpawnPoints(), setWalkable(), isFreeWorld);
        gameWorldFactory.bindGridSize(levelView.getGridDimensionProperty());
        GameWorld returnWorld = gameWorldFactory.createGameWorld();
        if (returnWorld instanceof FreeWorld) {
            freeworld = (FreeWorld) returnWorld;
            LibraryData.getInstance().addFreeWorldPath(freeworld.getPath());
            isFreeWorld.set(true);
        }
        return nextWorld = returnWorld;
    }

    /**
     * Creates a level and wave object and links them
     * 
     * @param nextWorld
     * @param prefs
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private WaveEditor createLevelAndWaveObjects (GameWorld nextWorld, LevelPreferencesEditor prefs)
                                                                                                    throws InstantiationException,
                                                                                                    IllegalAccessException,
                                                                                                    ClassNotFoundException,
                                                                                                    IllegalArgumentException,
                                                                                                    InvocationTargetException {
        StoryBoard sb = new StoryBoard();
        Level levelData = levelFactory.makeLevel(nextWorld, shopSet, shopModel, sb);

        prefs.setLevel(levelData);
        levelView.setLevel(levelData);
        gameManager.getGame().getLevelBoard().addLevel(levelData);
        return new WaveEditor(sb, levelData.getGameWorld());
    }

    @Override
    public Node getObject () {
        return baseNode;
    }

    /**
     * Returns a consumer
     * 
     * @return
     */
    public Consumer<Object> getConsumer () {
        return e -> libraryData.addGameObjectToList(e);
    }

    /**
     * Returns a biconsumer
     * 
     * @return
     */
    public BiConsumer<Class<?>, Object> getBiconsumer () {
        BiConsumer<Class<?>, Object> biConsumer = (klass, o) -> {
            libraryData.addCreatedObjectToList(klass, o);
        };
        return biConsumer;
    }

    public BiConsumer<List<GridCell>, List<GridCell>> setWalkable () {
        return (tower, enemy) -> {
            ((AbstractWorld) nextWorld).setObstacles(enemy);
            ((AbstractWorld) nextWorld).setTowerObstacles(tower);
        };
    }

    public BiConsumer<List<GridCell>, List<GridCell>> setSpawnPoints () {
        return (start, end) -> {
            freeworld.setSpawnPoints(start);
            freeworld.setEndPoints(end);
        };
    }
}

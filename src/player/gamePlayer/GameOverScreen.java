// This entire file is part of my masterpiece.
// COSETTE GOLDSTEIN

package player.gamePlayer;

import engine.game.Game;
import voogasalad.util.highscore.HighScoreController;
import voogasalad.util.highscore.HighScoreException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class has the ending scene for the Game after it's winning or losing conditions have been
 * met. Holds capacity to restart the game, select a new game, and view high scores. Implements the
 * Game Scene interface, so that all the GamePlayer Screens share the ability to get the scene from
 * the next screen and pass the current scene forwards.
 * 
 * @author Cosette Goldstein
 *
 */
public class GameOverScreen implements GameScene {

    private static final double BUTTON_RESIZE_SCALE_FACTOR = 1.25;
    private static final double HEADER_TEXT_RESIZE_SCALE_FACTOR = 3.5;
    private static final double RESULTS_TEXT_RESIZE_SCALE_FACTOR = 6.5;
    private static final Integer[] HIGH_SCORE_DISPLAY_DIMENSIONS = { 500, 500 };

    private static final String PLAY_AGAIN_BUTTON_TEXT = "Play Again";
    private static final String NEW_GAME_BUTTON_TEXT = "Select New Game";
    private static final String HIGH_SCORES_BUTTON_TEXT = "View High Scores";

    private Stage myStage;
    private Scene myPreviousScene;
    private Scene myScene;
    private Scene myGameSelectScene;
    private Game myGame;
    private Text myResultsText;

    /**
     * Constructor that holds the scenes for the other game player screens to return to scenes using
     * buttons
     * 
     * @param stage passed in to maintain the same window as previous screens
     * @param previousScene main menu screen
     * @param gameSelectionScene scene to select a new game
     * @param game initialized game
     */
    public GameOverScreen (Stage stage, Scene previousScene, Scene gameSelectionScene, Game game) {
        myStage = stage;
        myPreviousScene = previousScene;
        myGameSelectScene = gameSelectionScene;
        myGame = game;
        myResultsText = new Text("");
        makeScene();
    }

    /**
     * Adds elements (buttons and texts, specifically) to GridPane of a scene to add to stage.
     */
    private void makeScene () {
        GridPane grid = new GridPane();
        myScene = new Scene(grid);
        VBox vbox = new VBox(45);

        Text headerText = formatText("GAME OVER", HEADER_TEXT_RESIZE_SCALE_FACTOR);
        myResultsText = formatText(getResultsText(), RESULTS_TEXT_RESIZE_SCALE_FACTOR);
        vbox.getChildren().addAll(headerText, myResultsText);
        addCustomButtons(vbox);

        vbox.setAlignment(Pos.CENTER);
        grid.add(vbox, 0, 0);
        grid.setAlignment(Pos.CENTER);
        myStage.setScene(myScene);
    }

    /**
     * Custom buttons for this screen, with options to play again, go to the main menu to select a
     * new game, or go to the high score display
     * 
     * @param vbox display of text and buttons on screen
     */
    private void addCustomButtons (VBox vbox) {
        Button playBtn = formatButton(PLAY_AGAIN_BUTTON_TEXT, BUTTON_RESIZE_SCALE_FACTOR);
        playBtn.setOnAction(e -> myStage.setScene(myPreviousScene));
        Button newGameBtn = formatButton(NEW_GAME_BUTTON_TEXT, BUTTON_RESIZE_SCALE_FACTOR);
        newGameBtn.setOnAction(e -> myStage.setScene(myGameSelectScene));
        Button highScoreBtn = formatButton(HIGH_SCORES_BUTTON_TEXT, BUTTON_RESIZE_SCALE_FACTOR);
        highScoreBtn.setOnAction(e -> displayScores());

        vbox.getChildren().addAll(playBtn, newGameBtn, highScoreBtn);
    }

    /**
     * Create and makes specific style changes to buttons including changing size and text color.
     * 
     * @param text String to be on button
     * @param scaleFactor amount for button size to be scaled by
     * @return button to be added to Scene in makeScene
     */
    private Button formatButton (String text, double scaleFactor) {
        Button btn = new Button(text);
        btn.setTextFill(Color.RED);
        btn.setScaleX(scaleFactor);
        btn.setScaleY(scaleFactor);
        return btn;
    }

    /**
     * Create and make specific style changes to Texts including changing size.
     * 
     * @param exclamation String to be displayed as a Text
     * @param scaleFactor amount for Text size to be scaled by
     * @return Text to be added to Scene in makeScene
     */
    private Text formatText (String exclamation, double scaleFactor) {
        Text text = new Text(exclamation);
        text.setScaleX(scaleFactor);
        text.setScaleY(scaleFactor);
        return text;
    }

    /**
     * Returns the String version of the Text.
     * 
     * @return string text
     */
    private String getResultsText () {
        return myResultsText.toString();
    }

    /**
     * Method to be called from View class to set the new scene.
     */
    public void setScene () {
        myStage.setScene(myScene);
    }

    /**
     * HighScoreController instance created to display the list of high scores for the specific
     * game.
     * 
     * @throws HighScoreException
     */
    public void displayScores () {
        HighScoreController scores = HighScoreController.getController();
        try {
            scores.displayHighScores(myGame.getGameName(), "Score:",
                                     HIGH_SCORE_DISPLAY_DIMENSIONS[0],
                                     HIGH_SCORE_DISPLAY_DIMENSIONS[1]);
        }
        catch (HighScoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Called by View to set specific text depending on if the game is won or lost.
     * 
     * @param text String declaring game won or lost
     */
    public void setResultsText (String text) {
        myResultsText.setText(text);
    }

    /**
     * Returns the Scene to be displayed in the stage.
     */
    @Override
    public Scene getScene () {
        return myScene;
    }
}

package com.github.hanyaeger.tutorial;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.tutorial.entities.ball.Ball;
import com.github.hanyaeger.tutorial.entities.overlay.Overlay;
import com.github.hanyaeger.tutorial.entities.paddle.Paddle;
import com.github.hanyaeger.tutorial.entities.text.OverlayText;
import com.github.hanyaeger.tutorial.scenes.dynamicscenes.Level;
import com.github.hanyaeger.tutorial.scenes.staticscenes.GameOverScene;
import com.github.hanyaeger.tutorial.scenes.staticscenes.MenuScene;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for making the objects, levels, scenes, overlay and text for the brickanoid game.
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class Brickanoid extends YaegerGame {
    private final int AMOUNT_OF_LEVELS = 3;
    private final int STARTING_AMOUNT_OF_LIVES = 3;
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 800;
    private final int STARTING_AMOUNT_OF_BRICKS = 15;
    private final int LEVEL_BRICK_MULTIPLIER = 5;

    private Paddle player;
    private Level level1;
    private Level level2;
    private Level level3;
    private Ball ball;
    private boolean isGameOngoing = false;
    private boolean playerWon = true;

    public static int score;
    public static int lives;
    public static int highscore;
    public static OverlayText liveText;
    public static OverlayText scoreText;
    public static OverlayText highscoreText;
    private Overlay overlay;

    public static Level currentLevel;
    private int currentLevelCount = 1;
    private ArrayList<Level> levelsList = new ArrayList<>();
    public static ArrayList<Level> allLevels = new ArrayList<>();
    private int[] bricksInLevels = new int[AMOUNT_OF_LEVELS];

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            allLevels.add(null);
        }
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Brickanoid");
        setSize(new Size(SCREEN_WIDTH, SCREEN_HEIGHT));

        getHighscoreFromText();
    }

    @Override
    public void setupScenes() {
        addScene(0, new MenuScene(this));
        addScene(AMOUNT_OF_LEVELS + 1, new GameOverScene(this));
    }

    @Override
    public void setActiveScene(int id) {
        Brickanoid.currentLevel = allLevels.get(id);
        super.setActiveScene(id);
    }

    /**
     * Returns if the game is ongoing
     *
     * @return wheter the game is ongoing or not
     */
    public boolean getIsGameOngoing() {
        return isGameOngoing;
    }

    /**
     * Sets the isGameOngoing boolean either on true or false
     *
     * @param isGameOngoing true or false
     */
    public void setIsGameOngoing(boolean isGameOngoing) {
        this.isGameOngoing = isGameOngoing;
    }

    /**
     * Creates an instance of player and ball
     */
    // Methodes die alles te maken hebben met het instantieëren van objecten die nodig zijn voor het spel
    public void instantiateGameObjects() {
        final double PLAYER_START_X = 300;
        final double PLAYER_START_Y = 700;
        final double BALL_START_X = SCREEN_WIDTH / 2.0;
        final double BALL_START_Y = SCREEN_HEIGHT / 2.0 + (SCREEN_HEIGHT / 4.0);
        final int BALL_STARTING_SPEED = 7;
        final int BALL_STARTING_DIRECTION = 145;

        this.player = new Paddle(new Coordinate2D(PLAYER_START_X, PLAYER_START_Y), this, lives);
        this.ball = new Ball(new Coordinate2D(BALL_START_X, BALL_START_Y), this, BALL_STARTING_SPEED, BALL_STARTING_DIRECTION);
    }

    /**
     * Creates an instance of the levels
     */
    public void instantiateLevels() {
        resetLevelsArray();

        this.level1 = new Level(this, player, ball, overlay, bricksInLevels[0], "backgrounds/level1bg.PNG");
        this.level2 = new Level(this, player, ball, overlay, bricksInLevels[1], "backgrounds/level2bg.PNG");
        this.level3 = new Level(this, player, ball, overlay, bricksInLevels[2], "backgrounds/level3bg.PNG");

//        for (int i = 1; i < 4; i ++) {
//            String levelPath = "backgrounds/level" + i % 4 + "bg.PNG";
//            Level level = new Level(this, player, ball, overlay, bricksInLevels[i - 1], levelPath);
//            levelsList.add(level);
//        }
    }

    /**
     * Creates an instance of overlay and text
     */
    public void instantiateOverlayAndText() {
        final double OVERLAY_TEXT_Y = 10;
        final double LIVE_TEXT_X = 120;
        final double SCORE_TEXT_X = SCREEN_WIDTH / 2.0;
        final double HIGHSCORE_TEXT_X = SCREEN_WIDTH - 120;
        final double OVERLAY_X = 0;
        final double OVERLAY_Y = 0;

        // instantieer alle text en stop deze in de constructor van de overlay
        liveText = new OverlayText(new Coordinate2D(LIVE_TEXT_X, OVERLAY_TEXT_Y), "Lives", STARTING_AMOUNT_OF_LIVES);
        scoreText = new OverlayText(new Coordinate2D(SCORE_TEXT_X, OVERLAY_TEXT_Y), "Score", score);
        highscoreText = new OverlayText(new Coordinate2D(HIGHSCORE_TEXT_X, OVERLAY_TEXT_Y), "Highscore", highscore);
        overlay = new Overlay(new Coordinate2D(OVERLAY_X, OVERLAY_Y), this, liveText, scoreText, highscoreText);
    }

    /**
     * Adds all the levels to the game
     */
    public void resetLevelsArray() {
        for (int i = 0; i < AMOUNT_OF_LEVELS; i++) {
            bricksInLevels[i] = STARTING_AMOUNT_OF_BRICKS + (i * LEVEL_BRICK_MULTIPLIER);
        }
    }

    /**
     * Add all newly instantiated levels to the static allLevels ArrayList, these levels can be accessed by game objects if new objects need to be added to the current level
     */
    public void addLevelsToGame() {
//        for(int i = 1; i < 4; i++) {
//            Level l = levelsList.get(i - 1);
//            addScene(i, l);
//            Brickanoid.allLevels.set(i, l);
//        }
        addScene(1, level1);
        Brickanoid.allLevels.set(1, level1);
        addScene(2, level2);
        Brickanoid.allLevels.set(2, level2);
        addScene(3, level3);
        Brickanoid.allLevels.set(3, level3);
    }

    /**
     * Removes all the player and ball objects
     */
    public void removeGameObjects() {
        player.remove();
        ball.remove();
    }

    /**
     * Resets the score and lives
     */
    public void resetGameVariables() {
        score = 0;
        lives = STARTING_AMOUNT_OF_LIVES;
        playerWon = true;
        currentLevelCount = 1;
    }

    /**
     * Restarts the score and lives, create the instance of the game objects and create the instance of the overlay and text
     */
    // Methodes om het spel op te zetten
    public void restartGameState() {
        resetGameVariables();
        instantiateGameObjects();
        instantiateOverlayAndText();
    }

    /**
     * Starts a new game; the game state restarts, game variables get reset and all levels get reinstantiated
     * Get´s called in the PlayButton class when the player clicks to start a new game
     */
    public void startNewGame() {
        restartGameState();
        restartLevels();
        setActiveScene(1);
        setIsGameOngoing(true);

        SoundClip startnewgame = new SoundClip("audio/start_game2.mp3", 1);
        startnewgame.setVolume(0.15);
        startnewgame.play();
    }

    /**
     * Creates the levels and add them to the game
     */
    public void restartLevels() {
        instantiateLevels();
        addLevelsToGame();
    }

    /**
     * Updates current level counter, sets active scene to the next scene, resets ball and player attributes
     */
    public void skipToNextLevel() {
        currentLevelCount++;
        updateHighScore();
        setActiveScene(currentLevelCount);
        scoreText.setOverlayText(score);
        highscoreText.setOverlayText(highscore);
        Ball.amountOfBallsOnScreen = 1;
        lives = STARTING_AMOUNT_OF_LIVES;
        player.resetGravityAndFriction();
        ball.resetBall();
    }


    /**
     * Updates the highscore when score is higher than highscore
     */
    public static void updateHighScore() {
        if (score > highscore) {
            highscore = score;
            highscoreText.setOverlayText(highscore);
            replaceHighscore(String.valueOf(highscore));
        }
    }


    public void endGameAfterLoss() {
        SoundClip gameover = new SoundClip("audio/game_over.mp3", 1);
        gameover.setVolume(0.15);
        gameover.play();

        setPlayerWon(false);
        Ball.amountOfBallsOnScreen = 0;
        removeGameObjects();
        setIsGameOngoing(false);
        setActiveScene(AMOUNT_OF_LEVELS + 1);
    }

    /**
     * Reads the highscore from the text file and prints an error if there is nothing in the file, or no file
     */
    public void getHighscoreFromText() {
        // Try catch block om de highscores text file in te lezen en wanneer er een error is dan wordt een error in de console geschreven
        try {
            // Instantiëer een scanner en lees hiermee de highscore uit.
            File highscores = new File("src/main/resources/highscoretext/highscores.txt");
            Scanner scanner = new Scanner(highscores);

            while (scanner.hasNextLine()) {
                highscore = Integer.parseInt(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured reading highscores text file.");
            e.printStackTrace();
        }
    }

    /**
     * Replaces the old highscore with the new highscore and prints an error if there is no file
     *
     * @param replaceWith the new highscore that is replacing the old highscore
     */
    public static void replaceHighscore(String replaceWith) {
        // Controleert of de spel bezig, zo niet dan wordt de highscore overschreven
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("src/main/resources/highscoretext/highscores.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                line = replaceWith; // Hier wordt de regel vervangen door de nieuwe doorgegeven parameter waarde
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // Schrijft een nieuwe string met het vervangen regel OVER dezelfde file
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/highscoretext/highscores.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

            // Error message
        } catch (Exception e) {
            System.out.println("Problem reading file: " + e.getMessage());
        }
    }

    /**
     * Returns the screen width
     *
     * @return the width of the screen
     */
    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    /**
     * Returns the screen height
     *
     * @return the height of the screen
     */
    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    /**
     * Returns if the player has won the game
     *
     * @return true or false
     */
    public boolean getPlayerWon() {
        return playerWon;
    }

    /**
     * Sets the playerWon variable on true or false
     *
     * @param playerWon true or false based on if the player won the game or not
     */
    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }
}

package com.github.hanyaeger.tutorial.scenes.dynamicscenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.ball.Ball;
import com.github.hanyaeger.tutorial.entities.brick.Brick;
import com.github.hanyaeger.tutorial.entities.map.BrickTileMap;
import com.github.hanyaeger.tutorial.entities.map.GenerateBrickTileMap;
import com.github.hanyaeger.tutorial.entities.overlay.Overlay;
import com.github.hanyaeger.tutorial.entities.paddle.Paddle;

/**
 * This class is responsible for making all the levels in the Brickanoid game
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */
public class Level extends DynamicScene implements TileMapContainer {
    public Ball ball;
    public Brickanoid brickanoid;
    private Paddle player;
    private Overlay overlay;
    private int aantalBricks;
    private BrickTileMap tiles;
    private int ballsOnScreen;
    private String backgroundImage;

    public Level(Brickanoid brickanoid, Overlay overlay, int aantalBricks, String backgroundImage) {
        final double PLAYER_START_X = 300;
        final double PLAYER_START_Y = 700;
        final double BALL_START_X = brickanoid.getSCREEN_WIDTH() / 2.0;
        final double BALL_START_Y = brickanoid.getSCREEN_HEIGHT() / 2.0 + (brickanoid.getSCREEN_HEIGHT() / 4.0);
        final int BALL_STARTING_SPEED = 7;
        final int BALL_STARTING_DIRECTION = 145;

        this.brickanoid = brickanoid;
        this.player = new Paddle(new Coordinate2D(PLAYER_START_X, PLAYER_START_Y), brickanoid, Brickanoid.lives);
        this.ball = new Ball(new Coordinate2D(BALL_START_X, BALL_START_Y), brickanoid, BALL_STARTING_SPEED, BALL_STARTING_DIRECTION, this);
        this.overlay = overlay;
        this.aantalBricks = aantalBricks;
        this.backgroundImage = backgroundImage;
    }

    @Override
    public void setupScene() {
        setBackgroundAudio("audio/game_theme.mp3");
        setBackgroundAudioVolume(0.2);
        setBackgroundImage(backgroundImage);
    }

    @Override
    public void setupEntities() {
        addEntity(player);
        addEntity(ball);
        addEntity(overlay.getScoreText());
        addEntity(overlay.getLifeText());
        addEntity(overlay.getHighscoreText());
    }

    @Override
    public void setupTileMaps() {
        final double TILEMAP_STARTX = getWidth() / 25;
        final double TILEMAP_STARTY = getHeight() / 20;
        final double TILEMAP_WIDTH = getWidth() - getWidth() / 20;
        final double TILEMAP_HEIGHT = getHeight() / 2 + getHeight() / 4;

        this.tiles = new GenerateBrickTileMap(new Coordinate2D(TILEMAP_STARTX, TILEMAP_STARTY), new Size(TILEMAP_WIDTH, TILEMAP_HEIGHT), aantalBricks);
        addTileMap(tiles);
    }

    /**
     * Gets the remaining brick count that are left over in the brick tile map
     *
     * @return the amount of bricks that are remaining
     */
    public int getRemainingBrickCount() {
        int amountOfBricks = 0;
        //if( tiles.getInstanceMap() == null ) { return -1; }
        YaegerEntity[][] entityMap = tiles.getInstanceMap();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (entityMap[i][j] != null) {
                    amountOfBricks++;
                }
            }
        }

        return amountOfBricks;
    }

    /**
     * Determines whether the current level still has bricks, if it does the currentScene stays the same.
     * If there are no bricks left over in the level, the current level will be ended.
     *
     * @param brick the brick which gets collided by a ball
    */
    public void determineLevelStatus(Brick brick) {
        if (brick.getHitPoints() == 0 && Brickanoid.currentLevel.getRemainingBrickCount() == 0) {
            // Ends the current level when there are no bricks left
            brickanoid.skipToNextLevel();
        }
    }

    @Override
    public void addEntity(YaegerEntity yaegerEntity) {
        super.addEntity(yaegerEntity);
    }

    /**
     * Gets the amount of balls
     *
     * @return the amount of balls on the screen
     */
    public int getBallsOnScreen() {
        return ballsOnScreen;
    }

    /**
     * Sets the amount of balls
     *
     * @param ballsOnScreen the amount of balls that you want to have updated
     */
    public void setBallsOnScreen(int ballsOnScreen) {
        this.ballsOnScreen = ballsOnScreen;
    }
}

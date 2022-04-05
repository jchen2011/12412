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
 * This class is responsible for making the levels in the brickanoid game
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

    private String backgroundImage;
    //public static int aantalBricks = 5;

    public Level(Brickanoid brickanoid, Paddle player, Ball ball, Overlay overlay, int aantalBricks, String backgroundImage) {
        this.brickanoid = brickanoid;
        this.player = player;   // in deze klasse creeeren
        this.ball = ball;       // in deze klasse creeeren
        this.overlay = overlay;
        this.aantalBricks = aantalBricks;  // afleiden uit tilemap.getInstanceMap
        this.backgroundImage = backgroundImage;
    }

    @Override
    public void setupScene() {
        setBackgroundAudio("audio/game_theme.mp3");
        setBackgroundAudioVolume(0);
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
        // fix hardcoded coordinaten
        this.tiles = new GenerateBrickTileMap(new Coordinate2D(getWidth() / 25, getHeight() / 20), new Size(getWidth() - getWidth() / 20, getHeight() / 2 + getHeight() / 4), aantalBricks);
        addTileMap(tiles);
    }

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
     * Determines whether the current level still has bricks, if it does the currentscene stays the same, if it doesn't have any more bricks the next level get's set to the currentscene
     * @param brick the brick which gets collided by a ball
    */
    public void determineLevelStatus(Brick brick) {
        if (brick.getHitPoints() == 0 && Brickanoid.currentLevel.getRemainingBrickCount() == 0) {
            // Eindig het huidige level wanneer er geen bricks meer over zijn
            brickanoid.skipToNextLevel();
        }
    }

    @Override
    public void addEntity(YaegerEntity yaegerEntity) {
        super.addEntity(yaegerEntity);
    }
}

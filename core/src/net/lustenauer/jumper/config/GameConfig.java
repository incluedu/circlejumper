package net.lustenauer.jumper.config;

public class GameConfig {


    /* CONSTANTS */
    public static final float WIDTH = 480f; //pixels
    public static final float HEIGHT = 800f; // pixels

    public static final float HUD_WIDTH = 480f; // world units
    public static final float HUD_HEIGHT = 800f;

    public static final float WORLD_WIDTH = 16f; // world units
    public static final float WORLD_HEIGHT = 24f; // world units

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2;  // world units
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2; // world units

    public static final int CELL_SIZE = 1;

    public static final float PLANET_SIZE = 9f; // world units
    public static final float PLANET_HALF_SIZE = PLANET_SIZE / 2f; // world units

    public static final float MONSTER_SIZE = 1f; // world units
    public static final float MONSTER_HALF_SIZE = MONSTER_SIZE / 2; // world units

    public static final float MONSTER_START_ANG_SPEED = 2f;
    public static final float START_ANGLE = -90f;
    public static final float MONSTER_MAX_SPEED = 2f;
    public static final float MONSTER_START_ACC = 4f;

    public static final float COIN_SIZE = 1f; // world units
    public static final float COIN_HALF_SIZE = COIN_SIZE / 2f;
    public static final float COIN_SPAWN_TIME = 1.25f;
    public static final float MAX_COINS = 2;


    private GameConfig() {
    }
}

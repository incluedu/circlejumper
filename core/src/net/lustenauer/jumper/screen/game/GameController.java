package net.lustenauer.jumper.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import net.lustenauer.jumper.config.GameConfig;
import net.lustenauer.jumper.entity.Coin;
import net.lustenauer.jumper.entity.Monster;
import net.lustenauer.jumper.entity.Obstacle;
import net.lustenauer.jumper.entity.Planet;

public class GameController {

    /* CONSTANTS */
    private final Logger logger = new Logger(GameController.class.getName(), Logger.DEBUG);

    /* ATTRIBUTES */
    private Planet planet;
    private Monster monster;

    private float monsterStartX;
    private float monsterStartY;

    private final Array<Coin> coins = new Array<Coin>();
    private final Pool<Coin> coinPool = Pools.get(Coin.class, 10);
    private float coinTimer;

    private final Array<Obstacle> obstacles = new Array<Obstacle>();
    private final Pool<Obstacle> obstaclePool = Pools.get(Obstacle.class, 10);
    private float obstacleTimer;


    /* CONSTRUCTORS */

    public GameController() {
        init();
    }

    /* INIT */
    private void init() {
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        monsterStartX = GameConfig.WORLD_CENTER_X - GameConfig.MONSTER_HALF_SIZE;
        monsterStartY = GameConfig.WORLD_CENTER_Y + GameConfig.PLANET_HALF_SIZE;

        monster = new Monster();
        monster.setPosition(
                monsterStartX,
                monsterStartY
        );
    }

    /* PUBLIC METHODS */
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && monster.isWalking()) {
            monster.jump();
        }

        monster.update(delta);

        spawnObstacles(delta);
        spawnCoins(delta);

    }


    public Planet getPlanet() {
        return planet;
    }

    public Monster getMonster() {
        return monster;
    }

    public Array<Coin> getCoins() {
        return coins;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    /* PRIVATE METHODS */
    private void spawnCoins(float delta) {
        coinTimer += delta;

        // only max coins allowed
        if (coins.size >= GameConfig.MAX_COINS) {
            coinTimer = 0;
            return;
        }

        if (coinTimer >= GameConfig.COIN_SPAWN_TIME) {
            coinTimer = 0;
            Coin coin = coinPool.obtain();
            float randomAngle = MathUtils.random(360);
            coin.setAngleDeg(randomAngle);
            coins.add(coin);
        }
    }

    private void spawnObstacles(float delta) {
        obstacleTimer += delta;

        // only max obstacles allowed
        if (obstacles.size >= GameConfig.MAX_OBSTACLE) {
            obstacleTimer = 0;
            return;
        }

        if (coinTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            obstacleTimer = 0;
            Obstacle obstacle = obstaclePool.obtain();
            float randomAngle = MathUtils.random(360);
            obstacle.setAngleDeg(randomAngle);
            obstacles.add(obstacle);
        }
    }

}

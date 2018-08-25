package net.lustenauer.jumper.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import net.lustenauer.jumper.common.GameManager;
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

    private float startWaitTimer = GameConfig.START_WAIT_TIME;


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
        if (startWaitTimer > 0) {
            startWaitTimer -= delta;
            return;
        }

        GameManager.INSTANCE.updateDisplayScore(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && monster.isWalking()) {
            monster.jump();
        }

        monster.update(delta);

        spawnObstacles(delta);
        spawnCoins(delta);

        checkCollision();

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

    public float getStartWaitTimer() {
        return startWaitTimer;
    }

    /* PRIVATE METHODS */
    private void spawnCoins(float delta) {
        coinTimer += delta;

        if (coinTimer < GameConfig.COIN_SPAWN_TIME) {
            return;
        }

        coinTimer = 0;

        if (coins.size == 0) {
            addCoins();
        }
    }

    private void addCoins() {
        int count = MathUtils.random(GameConfig.MAX_COINS);

        for (int i = 0; i < count; i++) {
            float randomAngle = MathUtils.random(360);

            boolean canSpawn = !isCoinNearBy(randomAngle)
                    && !isMonsterNearBy(randomAngle);

            if (canSpawn) {
                Coin coin = coinPool.obtain();

                if (isObstacleNearBy(randomAngle)) {
                    coin.setOffset(true);
                }

                coin.setAngleDeg(randomAngle);
                coins.add(coin);
            }
        }
    }

    private boolean isCoinNearBy(float angle) {
        //check that there are now coins nearby min dist
        for (Coin coin : coins) {
            float angleDeg = coin.getAngleDeg();
            float diff = Math.abs(Math.abs(angleDeg) - Math.abs(angle));
            if (diff < GameConfig.MIN_ANG_DIST) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterNearBy(float angle) {
        //check that there are now Monster nearby min dist
        float diff = Math.abs(Math.abs(monster.getAngleDeg()) - Math.abs(angle));
        if (diff < GameConfig.MIN_ANG_DIST) {
            return true;
        }
        return false;
    }

    private boolean isObstacleNearBy(float angle) {
        //check that there are now obstacles nearby min dist
        for (Obstacle obstacle : obstacles) {
            float angleDeg = obstacle.getAngleDeg();
            float diff = Math.abs(Math.abs(angleDeg) - Math.abs(angle));
            if (diff < GameConfig.MIN_ANG_DIST) {
                return true;
            }
        }
        return false;
    }

    private void spawnObstacles(float delta) {
        obstacleTimer += delta;

        if (obstacleTimer < GameConfig.OBSTACLE_SPAWN_TIME) {
            return;
        }

        obstacleTimer = 0;

        if (obstacles.size == 0) {
            addObstacles();
        }
    }

    private void addObstacles() {
        int count = MathUtils.random(2, GameConfig.MAX_OBSTACLES);

        for (int i = 0; i < count; i++) {
            float randomAngle = monster.getAngleDeg() - i * GameConfig.MIN_ANG_DIST - MathUtils.random(60, 80);

            boolean canSpawn = !isObstacleNearBy(randomAngle)
                    && !isCoinNearBy(randomAngle)
                    && !isMonsterNearBy(randomAngle);

            if (canSpawn) {
                Obstacle obstacle = obstaclePool.obtain();
                obstacle.setAngleDeg(randomAngle);
                obstacles.add(obstacle);
            }
        }
    }

    private void checkCollision() {
        // player <--> coins
        for (int i = 0; i < coins.size; i++) {
            Coin coin = coins.get(i);

            if (Intersector.overlaps(monster.getBounds(), coin.getBounds())) {
                GameManager.INSTANCE.addScore(GameConfig.COIN_SCORE);
                coinPool.free(coin);
                coins.removeIndex(i);
            }
        }

        // player <--> obstacle
        for (int i = 0; i < obstacles.size; i++) {
            Obstacle obstacle = obstacles.get(i);

            if (Intersector.overlaps(monster.getBounds(), obstacle.getSensor())) {
                GameManager.INSTANCE.addScore(GameConfig.OBSTACLE_SCORE);
                obstaclePool.free(obstacle);
                obstacles.removeIndex(i);
            } else if (Intersector.overlaps(monster.getBounds(), obstacle.getBounds())) {
                restart();
            }

        }
    }

    private void restart() {
        coinPool.freeAll(coins);
        coins.clear();

        obstaclePool.freeAll(obstacles);
        obstacles.clear();

        monster.reset();
        monster.setPosition(monsterStartX, monsterStartY);

        GameManager.INSTANCE.reset();
        startWaitTimer = GameConfig.START_WAIT_TIME;

    }

}

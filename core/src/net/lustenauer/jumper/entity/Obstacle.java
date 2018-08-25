package net.lustenauer.jumper.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.gdx.util.entity.EntityBase;
import net.lustenauer.jumper.config.GameConfig;

public class Obstacle extends EntityBase implements Pool.Poolable {

    /* CONSTANTS */

    /* ATTRIBUTES */
    private float angleDeg;
    private Rectangle sensor = new Rectangle();
    private float sensorAngleDeg;


    /* CONSTRUCTORS */
    public Obstacle() {
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }
    /* INIT */

    /* PUBLIC METHODS */
    @Override
    public void reset() {
        angleDeg = 0;
        sensorAngleDeg = 0;
    }

    public void setAngleDeg(float value) {
        angleDeg = value % 360;
        sensorAngleDeg = angleDeg + 20f;

        // obstacle
        float radius = GameConfig.PLANET_HALF_SIZE;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);

        //sensor
        float sensorX = originX + MathUtils.cosDeg(-sensorAngleDeg) * radius;
        float sensorY = originY + MathUtils.sinDeg(-sensorAngleDeg) * radius;

        sensor.set(sensorX, sensorY, getWidth(), getHeight());
    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public float getSensorAngleDeg() {
        return sensorAngleDeg;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    /* PRIVATE METHODS */


}

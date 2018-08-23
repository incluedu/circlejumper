package net.lustenauer.jumper.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.gdx.util.entity.EntityBase;
import net.lustenauer.jumper.config.GameConfig;

public class Coin extends EntityBase implements Pool.Poolable {

    /* ATTRIBUTES */
    private float angleDeg;

    /* CONSTRUCTORS */
    public Coin() {
        setSize(GameConfig.CELL_SIZE, GameConfig.CELL_SIZE);
    }

    /* PUBLIC METHODS */
    public void setAngleDeg(float value) {
        angleDeg = value % 360;

        float radius = GameConfig.PLANET_HALF_SIZE;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg)*radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg)*radius;

        setPosition(newX, newY);

    }

    public float getAngleDeg() {
        return angleDeg;
    }

    @Override
    public void reset() {

    }
}

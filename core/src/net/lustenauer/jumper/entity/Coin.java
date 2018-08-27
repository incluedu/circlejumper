package net.lustenauer.jumper.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.gdx.util.entity.EntityBase;
import net.lustenauer.jumper.config.GameConfig;

public class Coin extends EntityBase implements Pool.Poolable {

    /*CONSTANTS */
    public static final float SCALE_MAX = 1.0f;

    /* ATTRIBUTES */
    private float angleDeg;
    private boolean offset;
    private float scale;

    /* CONSTRUCTORS */
    public Coin() {
        setSize(GameConfig.CELL_SIZE, GameConfig.CELL_SIZE);
    }

    /* PUBLIC METHODS */
    public void update(float delta) {
        if (scale < SCALE_MAX) {
            scale += delta;
        }
    }

    public void setAngleDeg(float value) {
        angleDeg = value % 360;

        float radius = GameConfig.PLANET_HALF_SIZE;

        if (offset) {
            radius += GameConfig.CELL_SIZE;
        }

        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);

    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public void reset() {
        offset = false;
        scale = 0.0f;
    }
}

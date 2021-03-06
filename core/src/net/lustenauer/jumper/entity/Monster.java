package net.lustenauer.jumper.entity;

import com.badlogic.gdx.math.MathUtils;
import net.lustenauer.gdx.util.entity.EntityBase;
import net.lustenauer.jumper.config.GameConfig;

public class Monster extends EntityBase {
    /* ATTRIBUTES */
    private float angleDeg = GameConfig.START_ANGLE;
    private float angleDegSpeed = GameConfig.MONSTER_START_ANG_SPEED;
    private float speed = 0;
    private float acceleration = GameConfig.MONSTER_START_ACC;
    private MonsterState state = MonsterState.WALKING;

    /* CONSTRUCTORS */
    public Monster() {
        setSize(GameConfig.MONSTER_SIZE, GameConfig.MONSTER_SIZE);
    }

    /* PUBLIC METHODS */
    public void update(float delta) {

        if (state.isJumping()) {
            speed += acceleration * delta;

            // when reached max speed switch state to falling
            if (speed >= GameConfig.MONSTER_MAX_SPEED) {
                fall();
            }
        } else if (state.isFalling()) {
            // falling down
            speed -= acceleration * delta;

            // when speed is zero switch state back to walking
            if (speed <= 0) {
                speed = 0;
                walk();
            }
        }

        angleDeg += angleDegSpeed * delta;
        angleDeg = angleDeg % 360;

        float radius = GameConfig.PLANET_HALF_SIZE + speed;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);

    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public void jump() {
        state = MonsterState.JUMPING;
    }

    public boolean isWalking() {
        return state.isWalking();
    }

    public void reset(){
        angleDeg = GameConfig.START_ANGLE;
        speed = 0;
        walk();
    }


    /* PRIVATE METHODS */
    private void fall() {
        state = MonsterState.FALLING;
    }

    private void walk() {
        state = MonsterState.WALKING;
    }


}

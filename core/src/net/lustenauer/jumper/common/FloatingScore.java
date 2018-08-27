package net.lustenauer.jumper.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.jumper.config.GameConfig;

public class FloatingScore implements Pool.Poolable {
    /* CONSTANTS */

    /* ATTRIBUTES */
    private Color color = Color.WHITE.cpy();

    private int score;
    private float startX;
    private float startY;
    private float x, y;
    private float timer;


    /* CONSTRUCTORS */
    public FloatingScore() {
    }

    /* INIT */

    /* PUBLIC METHODS */
    public void update(float delta) {
        if (isFinished()) {
            return;
        }
        timer += delta;

        float percentage = timer / GameConfig.FLOATING_DURATION;
        float alpha = MathUtils.clamp(1.0f - percentage, 0f, 1f);

        x = startX;
        y = startY + (percentage) * 60;
        color.a = alpha;
    }

    public boolean isFinished() {
        return timer >= GameConfig.FLOATING_DURATION;
    }

    public void setStartPosition(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void reset() {
        score = 0;
        startX = 0;
        startY = 0;
        timer = 0;
        x = 0;
        y = 0;
        color.a = 1.0f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public String getScoreString() {
        return Integer.toString(score);
    }

    public Color getColor() {
        return color;
    }

    /* PRIVATE METHODS */
}



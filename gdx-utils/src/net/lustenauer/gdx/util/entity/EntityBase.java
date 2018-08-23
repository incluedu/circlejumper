package net.lustenauer.gdx.util.entity;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Patric Hollenstein on 04.02.18.
 *
 * @author Patric Hollenstein
 */
public abstract class EntityBase {

    /*
     * ATTRIBUTES
     */
    protected float x;
    protected float y;

    protected float width = 1;
    protected float height = 1;

    protected Rectangle bounds;


    /*
     * CONSTRUCTORS
     */

    protected EntityBase() {
        bounds = new Rectangle(x, y, width, height);
    }

    /*
     * PUBLIC METHODES
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /*
     * PRIVATE METHODES
     */
    protected void updateBounds() {
        bounds.setPosition(x, y);
        bounds.setSize(width, height);
    }

}

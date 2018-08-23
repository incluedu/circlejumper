package net.lustenauer.jumper.entity;

import com.badlogic.gdx.math.Circle;
import net.lustenauer.jumper.config.GameConfig;

public class Planet {

    /* ATTRIBUTES */
    private float x;
    private float y;

    private float width = 1;
    private float height = 1;

    private Circle bounds;

    /* CONSTRUCTORS */
    public Planet(){
        bounds = new Circle(x,y,GameConfig.PLANET_HALF_SIZE);
    }

    /* PUBLIC METHODS */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float height){
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        updateBounds();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        updateBounds();
    }

    public Circle getBounds() {
        return bounds;
    }


    public void updateBounds(){
        bounds.setPosition(x,y);
    }

    /* PRIVATE METHODS */




}

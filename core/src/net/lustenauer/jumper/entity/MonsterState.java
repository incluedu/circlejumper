package net.lustenauer.jumper.entity;

public enum MonsterState {
    WALKING,
    JUMPING,
    FALLING;

    /* PUBLIC METHODS */

    public boolean isWalking() {
        return this == WALKING;
    }

    public boolean isJumping() {
        return this == JUMPING;
    }

    public boolean isFalling() {
        return this == FALLING;
    }

}

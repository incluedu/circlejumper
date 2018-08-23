package net.lustenauer.jumper;

import net.lustenauer.gdx.util.game.GameBase;
import net.lustenauer.jumper.screen.game.GameScreen;

public class CircleJumperGame extends GameBase {


    @Override
    public void postCreate() {
        setScreen(new GameScreen(this));
    }
}

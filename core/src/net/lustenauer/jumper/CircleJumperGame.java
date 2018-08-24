package net.lustenauer.jumper;

import net.lustenauer.gdx.util.game.GameBase;
import net.lustenauer.jumper.screen.game.GameScreen;
import net.lustenauer.jumper.screen.loading.LoadingScreen;

public class CircleJumperGame extends GameBase {


    @Override
    public void postCreate() {
        setScreen(new LoadingScreen(this));
    }
}

package net.lustenauer.jumper.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import net.lustenauer.gdx.util.GdxUtils;
import net.lustenauer.gdx.util.game.GameBase;

public class GameScreen extends ScreenAdapter {

    /* ATTRIBUTE */
    private final GameBase game;
    private final AssetManager assetManager;

    private GameController controller;
    private GameRenderer renderer;

    /* CONTROLLERS */
    public GameScreen(GameBase game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    /* PUBLIC METHODS */

    @Override
    public void show() {
        controller = new GameController();
        renderer = new GameRenderer(controller);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width,height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}

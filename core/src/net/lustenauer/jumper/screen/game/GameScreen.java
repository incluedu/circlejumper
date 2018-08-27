package net.lustenauer.jumper.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import net.lustenauer.gdx.util.GdxUtils;
import net.lustenauer.gdx.util.game.GameBase;
import net.lustenauer.jumper.assets.AssetDescriptors;
import net.lustenauer.jumper.common.SoundListener;

public class GameScreen extends ScreenAdapter {

    /* ATTRIBUTE */
    private final GameBase game;
    private final AssetManager assetManager;
    private final SoundListener listener;

    private GameController controller;
    private GameRenderer renderer;

    private Sound coinSound;
    private Sound jumpSound;
    private Sound loseSound;

    /* CONTROLLERS */
    public GameScreen(GameBase game) {
        this.game = game;
        this.assetManager = game.getAssetManager();

        listener = new SoundListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void jump() {
                jumpSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };
    }

    /* PUBLIC METHODS */

    @Override
    public void show() {
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        jumpSound = assetManager.get(AssetDescriptors.JUMP_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);

        controller = new GameController(listener);
        renderer = new GameRenderer(controller, game.getBatch(), assetManager);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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

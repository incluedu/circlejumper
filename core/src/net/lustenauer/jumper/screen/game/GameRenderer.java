package net.lustenauer.jumper.screen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.jumper.config.GameConfig;
import net.lustenauer.gdx.util.ViewportUtils;
import net.lustenauer.gdx.util.debug.DebugCameraController;
import net.lustenauer.jumper.entity.Coin;
import net.lustenauer.jumper.entity.Planet;
import net.lustenauer.jumper.entity.Monster;

public class GameRenderer implements Disposable {

    /* ATTRIBUTES */
    private final GameController controller;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private DebugCameraController debugCameraController;

    /* CONSTRUCTORS */
    public GameRenderer(GameController controller) {
        this.controller = controller;
        init();
    }

    /* INIT */
    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }


    /* PUBLIC METHODS */
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        rendererDebug();

    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();

    }


    /* PRIVATE METHODS */

    private void rendererDebug() {
        ViewportUtils.drawGrid(viewport, renderer, GameConfig.CELL_SIZE);

        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.setAutoShapeType(true);
        renderer.begin();

        drawDebug();

        renderer.end();
    }

    private void drawDebug() {
        // planet
        renderer.setColor(Color.RED);
        Planet planet = controller.getPlanet();
        Circle planetBounds = planet.getBounds();
        renderer.circle(planetBounds.x, planetBounds.y, planetBounds.radius, 30);

        // monster
        renderer.setColor(Color.BLUE);
        Monster monster = controller.getMonster();
        Rectangle monsterBounds = monster.getBounds();
        renderer.rect(
                monsterBounds.x, monsterBounds.y,
                0, 0,
                monsterBounds.width, monsterBounds.height,
                1, 1,
                GameConfig.START_ANGLE - monster.getAngleDeg()
        );


        // coins
        renderer.setColor(Color.YELLOW);

        for (Coin coin : controller.getCoins()) {
            Rectangle coinBounds = coin.getBounds();
            renderer.rect(
                    coinBounds.x, coinBounds.y,
                    0, 0,
                    coinBounds.width, coinBounds.height,
                    1, 1,
                    GameConfig.START_ANGLE - coin.getAngleDeg()
            );
        }


    }
}

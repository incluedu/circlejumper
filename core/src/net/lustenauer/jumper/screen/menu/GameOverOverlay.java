package net.lustenauer.jumper.screen.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.lustenauer.jumper.assets.ButtonStyleNames;
import net.lustenauer.jumper.assets.RegionNames;
import net.lustenauer.jumper.common.GameManager;

public class GameOverOverlay extends Table {

    /* ATTRIBUTES */
    private final OverlayCallback callback;

    private Label scoreLabel;
    private Label highScoreLabel;

    /* CONSTRUCTORS */
    public GameOverOverlay(Skin skin, OverlayCallback callback) {
        super(skin);
        this.callback = callback;
        init();
    }

    /* INIT */
    private void init() {
        defaults().pad(20);

        Image gameOverImage = new Image(getSkin(), RegionNames.GAME_OVER);

        // score table
        Table scoreTable = new Table(getSkin());
        scoreTable.defaults().pad(10);
        scoreTable.setBackground(RegionNames.PANEL);

        scoreTable.add("SCORE: ").row();
        scoreLabel = new Label("",getSkin());
        scoreTable.add(scoreLabel).row();

        scoreTable.add("HIGH SCORE: ").row();
        highScoreLabel = new Label("",getSkin());
        scoreTable.add(highScoreLabel);

        scoreTable.center();

        // button table
        Table buttonTable = new Table();

        ImageButton homeButton = new ImageButton(getSkin(), ButtonStyleNames.HOME);
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.home();
            }
        });

        ImageButton restartButton = new ImageButton(getSkin(),ButtonStyleNames.RESTART);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.ready();
            }
        });

        buttonTable.add(homeButton).left().expandX();
        buttonTable.add(restartButton).right().expandX();

        add(gameOverImage).row();
        add(scoreTable).row();
        add(buttonTable).grow();

        center();
        setFillParent(true);
        pack();

        updateLabels();
    }

    /* PUBLIC METHODS */
    public void updateLabels(){
        scoreLabel.setText(""+ GameManager.INSTANCE.getScore());
        highScoreLabel.setText(""+ GameManager.INSTANCE.getHighScore());
    }

    /* PRIVATE METHODS */

}

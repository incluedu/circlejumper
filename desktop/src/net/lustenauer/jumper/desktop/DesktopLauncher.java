package net.lustenauer.jumper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.lustenauer.jumper.CircleJumperGame;
import net.lustenauer.jumper.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = (int) GameConfig.WIDTH;
		config.height = (int) GameConfig.HEIGHT;

		new LwjglApplication(new CircleJumperGame(), config);
	}
}

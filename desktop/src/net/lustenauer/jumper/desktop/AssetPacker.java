package net.lustenauer.jumper.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {
    /* CONSTANTS */
    public static final String RAW_ASSETS_PATH = "desktop/assets-raw";
    public static final String ASSETS_PATH = "android/assets";

    /* ATTRIBUTES */

    /* CONSTRUCTORS */
    public AssetPacker() {
    }


    /* INIT */

    /* PUBLIC METHODS */

    /* PRIVATE METHODS */

    /* MAIN */
    public static void main(String[] args) {
        TexturePacker.process(
                RAW_ASSETS_PATH + "/gameplay",
                ASSETS_PATH + "/gameplay",
                "gameplay"
        );

        TexturePacker.process(
                RAW_ASSETS_PATH + "/ui",
                ASSETS_PATH + "/ui",
                "skin"
        );

    }

}

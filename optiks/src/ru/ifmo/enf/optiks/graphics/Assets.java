package ru.ifmo.enf.optiks.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.ifmo.enf.optiks.phisycs.object.ObjectType;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 24.07.12
 */
public class Assets extends AssetManager {

    private static Assets instance = new Assets();

    /* TODO move it in  property file */
    public final static String GAME_OBJECTS_PACK = "gfx/game_objects/pack";
    public final static String EDITOR_BACKGROUND_TEXTURE = "gfx/editor/background.png";
    /* TODO create one atlas from little textures */
    public final static String EDITOR_BUTTONS_PACK = "gfx/editor/buttons/pack";
    public final static String EDITOR_GAME_OBJECTS_BTN = "gfx/editor/buttons/game_objects.png";
    public final static String EDITOR_GAME_OBJECTS_BG = "gfx/editor/buttons/background.png";


    public static Assets inst() {
        if (instance == null) instance = new Assets();
        return instance;
    }

    private Assets() {
        /* TODO create separate methods to load splash, game and other textures */
    }

    public static TextureRegion getTextureRegion(final ObjectType type) {
       return instance.get(GAME_OBJECTS_PACK, TextureAtlas.class).findRegion(type.toString());
    }

    public Animation getAnimation(final ObjectType type) {
        return null;
    }

    @Override
    public synchronized void dispose() {
        super.dispose();
        instance = null;
    }
}

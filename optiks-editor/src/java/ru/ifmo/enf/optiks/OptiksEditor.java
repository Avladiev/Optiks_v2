package ru.ifmo.enf.optiks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.physics.WorldFactory;
import ru.ifmo.enf.optiks.screen.EditorScreen;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class OptiksEditor extends Game {

    private World world;
    private WorldFactory factory;
    private OrthographicCamera camera;
    public boolean isLoaded = false;

    @Override
    public void create() {
        /* Camera settings */
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /* Loading assets */
        Assets.inst().load(Assets.GAME_OBJECTS_PACK, TextureAtlas.class);
        Assets.inst().load(Assets.EDITOR_BACKGROUND_TEXTURE, Texture.class);
        Assets.inst().load(Assets.EDITOR_GAME_OBJECTS_BTN, Texture.class);
        Assets.inst().load(Assets.EDITOR_GAME_OBJECTS_BG, Texture.class);

        /* Physics world */
        world = new World(new Vector2(0, -10), true);
        factory = new WorldFactory(world);
    }

    @Override
    public void render() {
        if (isLoaded) {
            if (getScreen() == null) {
                setScreen(new EditorScreen(this));
            }
            getScreen().render(Gdx.graphics.getDeltaTime());
        } else {
            if (Assets.inst().getProgress() < 1) {
                Assets.inst().update();
            } else {
                isLoaded = true;
            }
        }
    }

    public World getWorld() {
        return world;
    }

    public WorldFactory getFactory() {
        return factory;
    }

    public Camera getCamera() {
        return camera;
    }
}

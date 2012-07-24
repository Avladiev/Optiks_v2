package ru.ifmo.enf.optiks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.screen.EditorScreen;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class OptiksEditor extends Game {

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

        setScreen(new EditorScreen(this));
    }

    @Override
    public void render() {
        if (isLoaded) {
            getScreen().render(Gdx.graphics.getDeltaTime());
        } else {
            if (Assets.inst().getProgress() < 1) {
                Assets.inst().update();
            } else {
                isLoaded = true;
            }
        }
    }

    public Camera getCamera() {
        return camera;
    }
}

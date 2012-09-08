package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.ifmo.enf.optiks.OptiksEditor;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.phisycs.object.ObjectType;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorScreen implements Screen {

    private final OptiksEditor editor;

    private final Camera camera;

    private final SpriteBatch batch;

    public EditorScreen(final OptiksEditor optiksEditor) {
        this.editor = optiksEditor;
        this.camera = optiksEditor.getCamera();
        this.batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(final float delta) {
        camera.update();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        if (editor.isLoaded) {
            batch.begin();
            batch.draw(Assets.inst().get(Assets.EDITOR_BACKGROUND_TEXTURE, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.draw(Assets.getTextureRegion(ObjectType.AIM), 40, 40, 100, 100);
            batch.draw(Assets.inst().get(Assets.EDITOR_GAME_OBJECTS_BTN, Texture.class), Gdx.graphics.getWidth()- 100, 30, 70, 70);
            batch.end();
        }
    }

    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}

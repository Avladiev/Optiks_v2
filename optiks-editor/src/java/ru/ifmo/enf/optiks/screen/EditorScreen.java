package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Screen;
import ru.ifmo.enf.optiks.OptiksEditor;
import ru.ifmo.enf.optiks.OptiksGame;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorScreen implements Screen {

    private final OptiksEditor editor;

    public EditorScreen(final OptiksEditor optiksEditor) {
        this.editor = optiksEditor;
    }

    @Override
    public void render(final float delta) {
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

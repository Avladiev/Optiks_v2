package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.OptiksGame;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class MenuScreen implements Screen {

    public MenuScreen(final OptiksGame optiksGame) {
        //todo
    }

    @Override
    public void render(final float delta) {
        //todo
    }

    @Override
    public void resize(final int width, final int height) {
        //todo
    }

    @Override
    public void show() {
        //todo
    }

    @Override
    public void hide() {
        //todo
    }

    @Override
    public void pause() {
        //todo
    }

    @Override
    public void resume() {
        //todo
    }

    @Override
    public void dispose() {
        //todo
    }

    private static class MyGestureListener implements GestureDetector.GestureListener {

        @Override
        public boolean touchDown(final int x, final int y, final int pointer) {
            //todo
            return false;
        }

        @Override
        public boolean tap(final int x, final int y, final int count) {
            //todo
            return false;
        }

        @Override
        public boolean longPress(final int x, final int y) {
            //todo
            return false;
        }

        @Override
        public boolean fling(final float velocityX, final float velocityY) {
            //todo
            return false;
        }

        @Override
        public boolean pan(final int x, final int y, final int deltaX, final int deltaY) {
            //todo
            return false;
        }

        @Override
        public boolean zoom(final float originalDistance, final float currentDistance) {
            //todo
            return false;
        }

        @Override
        public boolean pinch(final Vector2 initialFirstPointer, final Vector2 initialSecondPointer, final Vector2 firstPointer, final Vector2 secondPointer) {
            //todo
            return false;
        }
    }
}

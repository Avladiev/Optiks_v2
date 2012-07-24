package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class LaserListener implements GestureDetector.GestureListener {
    private GameObject laser; /// mabe type LaserObject

    public void setLaser(final GameObject laser) {
        this.laser = laser;
    }

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
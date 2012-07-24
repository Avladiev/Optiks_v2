package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import ru.ifmo.enf.optiks.object.PhysicsAnimatedGameObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Dudko Alex (dududko@gmail.com)
 */

/**
 * Gesture Listener for active mirrors
 */
public class MirrorListener implements GestureDetector.GestureListener {
    private List<PhysicsAnimatedGameObject> mirrors;
    private final World world;

    private MouseJoint mouseJoint;
    private RevoluteJoint revoluteJoint;

    public MirrorListener(final World world) {
        this.world = world;
        mirrors = new ArrayList<PhysicsAnimatedGameObject>();
    }

    public void addMirror(final PhysicsAnimatedGameObject mirror) {
        mirrors.add(mirror);
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

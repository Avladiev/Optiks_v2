package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class RectangleListener implements GestureDetector.GestureListener {
    World world;
    MouseJoint joint;

    public RectangleListener(final World world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer) {
        joint = null;
        world.QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(final Fixture fixture) {
                MouseJointDef mouseJointDef = new MouseJointDef();
                mouseJointDef.bodyA = fixture.getBody();
                joint = (MouseJoint) world.createJoint(mouseJointDef);
                return false;
            }
        }, -1, -1, 1, 1);
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
        joint.setTarget(new Vector2(x, y));
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

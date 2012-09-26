package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.object.Wall;

import java.util.Iterator;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class RotationDragListenerSpider implements GestureDetector.GestureListener {
    final World world;
    BodyTouchQuery bodyTouchQuery;
    private MouseJoint mouseJoint;

    public RotationDragListenerSpider(final World world) {
        bodyTouchQuery = new BodyTouchQuery(world);
        this.world = world;
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer) {

        final Vector2 vector = toPhysicsVector(x, y);
        @NotNull final GameObject object = bodyTouchQuery.getQueryBody(vector.x, vector.y, false);

        if (object == null) {
            return true;
        }

        final boolean isRotate = bodyTouchQuery.isRotate(object.getBody().getWorldCenter(), vector);

        //todo
        object.getBody().setGravityScale(0);
//        object.getBody().setLinearVelocity(0, 0);
        final MouseJointDef mouseJointDef = new MouseJointDef();

        Body body = null;
        Iterator<Body> bodies = world.getBodies();
        while (bodies.hasNext()) {
            body = bodies.next();
            System.out.println(body.getUserData().toString());
            if (body.getUserData() instanceof Wall) {
                System.out.println("ok");
                break;
            }
        }

        System.out.println(((GameObject) body.getUserData()).toString());
        mouseJointDef.bodyA = body;
        mouseJointDef.bodyB = object.getBody();
        mouseJointDef.dampingRatio = 90f;
        mouseJointDef.frequencyHz = 300;
        mouseJointDef.maxForce = 100000f;
        mouseJointDef.collideConnected = true;
        mouseJointDef.target.set(vector);
        mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);
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
        mouseJoint.setTarget(toPhysicsVector(x, y));
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

    private Vector2 toPhysicsVector(final float x, final float y) {
        final float pointX = (x - Gdx.graphics.getWidth() / 2) / 5;
        final float pointY = -(y - Gdx.graphics.getHeight() / 2) / 5;
        return new Vector2(pointX, pointY);
    }

    public MouseJoint getMouseJoint() {
        return mouseJoint;
    }
}

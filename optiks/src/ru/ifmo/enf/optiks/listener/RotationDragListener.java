package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.physics.object.Wall;
import ru.ifmo.enf.optiks.physics.object.state.State;
import ru.ifmo.enf.optiks.physics.object.state.StateFactory;
import ru.ifmo.enf.optiks.util.Converter;

import java.util.Iterator;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public abstract class RotationDragListener extends InputAdapter {

    final World world;

    BodyTouchQuery bodyTouchQuery;
    private MouseJoint mouseJoint;
    private GameObject activeObject;
    private State activeObjectState;
    private boolean isRotate;
    private final StateFactory stateFactory;

    public RotationDragListener(final World world, final StateFactory stateFactory) {
        bodyTouchQuery = new BodyTouchQuery(world);
        this.world = world;
        this.stateFactory = stateFactory;
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer, final int button) {
        switch (pointer) {
            case 0:
                final Vector2 vector = Converter.toPhysicsVector(x, y);
                activeObject = bodyTouchQuery.getQueryBody(vector.x, vector.y, false);

                if (activeObject == null) {
                    return false;
                }

                isRotate = bodyTouchQuery.isRotate(activeObject.getBody().getWorldPoint(activeObject.getRotationCenter()), vector);

                activeObjectState = stateFactory.createActiveObjectState(activeObject, isRotate);
                activeObjectState.setPreState();

                final MouseJointDef mouseJointDef = new MouseJointDef();

                Body body = null;
                Iterator<Body> bodies = world.getBodies();

                // get ground body
                while (bodies.hasNext()) {
                    body = bodies.next();
                    if (body.getUserData() instanceof Wall) {
                        break;
                    }
                }

                mouseJointDef.bodyA = body;
                mouseJointDef.bodyB = activeObject.getBody();
                mouseJointDef.dampingRatio = 7;
                mouseJointDef.frequencyHz = 10;
                mouseJointDef.maxForce = 100000f;
                mouseJointDef.collideConnected = true;
                mouseJointDef.target.set(vector);
                mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);
                //todo
                return false;
            default:
                return false;
        }
    }

    @Override
    public boolean touchUp(final int x, final int y, final int pointer, final int button) {
        switch (pointer) {
            case 0:
                if (mouseJoint != null) {
                    world.destroyJoint(mouseJoint);
                    activeObject.stopBody();

                    activeObjectState.setPostState();

                    activeObjectState = null;
                    activeObject = null;
                    mouseJoint = null;
                }
                //todo
                return false;
            default:
                return false;
        }
    }

    @Override
    public boolean touchDragged(final int x, final int y, final int pointer) {
        switch (pointer) {
            case 0:
                if (mouseJoint == null) {
                    return true;
                }
                mouseJoint.setTarget(Converter.toPhysicsVector(x, y));
                //todo
                return false;
            default:
                return false;
        }
    }

    public MouseJoint getMouseJoint() {
        return mouseJoint;
    }
}


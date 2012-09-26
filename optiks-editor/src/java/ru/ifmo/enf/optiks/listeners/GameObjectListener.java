package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import ru.ifmo.enf.optiks.joint.EditorMouseJointDef;
import ru.ifmo.enf.optiks.listener.BodyTouchQuery;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.object.state.State;
import ru.ifmo.enf.optiks.phisycs.object.state.StateFactoryPlay;
import ru.ifmo.enf.optiks.phisycs.util.Calculate;
import ru.ifmo.enf.optiks.screen.EditorScreen;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.09.12
 */
public class GameObjectListener extends InputAdapter {

    private final EditorScreen editorScreen;
    private final World world;
    private final BodyTouchQuery bodyTouchQuery;
    private final GameObject wall;

    private final GestureDetector.GestureListener gestureListener;

    private GameObject activeObject;
    private State activeObjectState;
    private RevoluteJoint revoluteJoint;

    public GameObjectListener(final EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
        world = editorScreen.getWorld();
        bodyTouchQuery = new BodyTouchQuery(world);
        wall = editorScreen.getWall();

        /* Gesture listener */
        gestureListener = new GestureDetector.GestureAdapter() {
            @Override
            public boolean longPress(int x, int y) {
                if (activeObject != null) {
                    if (activeObject.hasPrevious()) {
                        activeObject.setPrevious(null);
                        activeObject.setJoint(null);
                        world.destroyJoint(activeObject.getJoint());
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer, final int button) {
        switch (pointer) {
            case 0:
                final Vector2 vector = Calculate.toPhysicsVector(x, y);
                activeObject = bodyTouchQuery.getQueryBody(vector.x, vector.y, true);


                if (activeObject == null) {
                    return false;
                }

                final boolean isRotate = bodyTouchQuery.isRotate(vector, activeObject.getBody().getWorldPoint(activeObject.getRotationCenter()));

                activeObject.getBody().setType(BodyDef.BodyType.DynamicBody);
                activeObjectState = new StateFactoryPlay().createActiveObjectState(activeObject, isRotate);
                activeObjectState.setPreState();

                if (isRotate) {
                    RevoluteJointDef jointDef = new RevoluteJointDef();
                    jointDef.initialize(wall.getBody(), activeObject.getBody(), new Vector2());

                    Vector2 globalRotationPoint = activeObject.getBody().getWorldPoint(activeObject.getRotationCenter());

                    jointDef.localAnchorA.set(globalRotationPoint);
                    jointDef.localAnchorB.set(activeObject.getRotationCenter());

                    revoluteJoint = (RevoluteJoint) world.createJoint(jointDef);
                }

                final MouseJointDef mouseJoint = new EditorMouseJointDef(wall, activeObject, vector);
                editorScreen.setMouseJoint((MouseJoint) world.createJoint(mouseJoint));

                /* Hide objects panel */
                editorScreen.getObjectsPanel().hide();
        }
        return false;
    }

    @Override
    public boolean touchDragged(final int x, final int y, final int pointer) {
        if (editorScreen.getMouseJoint() != null) {
            editorScreen.getMouseJoint().setTarget(Calculate.toPhysicsVector(x, y));
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(final int x, final int y, final int pointer, final int button) {
        if (editorScreen.getMouseJoint() != null) {
            editorScreen.getMouseJoint().getBodyB().setType(BodyDef.BodyType.StaticBody);

            /* Destroy joint */
            editorScreen.getWorld().destroyJoint(editorScreen.getMouseJoint());
            editorScreen.setMouseJoint(null);

            /* Show objects panel */
            editorScreen.getObjectsPanel().show();

            if (activeObjectState != null) {
                activeObjectState.setPostState();
            }
        }
        if (revoluteJoint != null) {
            world.destroyJoint(revoluteJoint);
            revoluteJoint = null;
        }
        return false;
    }

    public GestureDetector.GestureListener getGestureListener() {
        return gestureListener;
    }
}

package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import ru.ifmo.enf.optiks.joint.EditorMouseJointDef;
import ru.ifmo.enf.optiks.listener.BodyTouchQuery;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.utils.Calculate;
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

    private GameObject activeObject;

    public GameObjectListener(final EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
        world = editorScreen.getWorld();
        bodyTouchQuery = new BodyTouchQuery(world);
        wall = editorScreen.getWall();
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer, final int button) {
        switch (pointer) {
            case 0:
                final Vector2 vector = Calculate.toPhysicsVector(x, y);
                activeObject = bodyTouchQuery.getQueryBody(vector.x, vector.y);

                if (activeObject == null) {
                    return false;
                }

                final MouseJointDef mouseJoint = new EditorMouseJointDef(wall, activeObject);
                editorScreen.setMouseJoint((MouseJoint) world.createJoint(mouseJoint));
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
            editorScreen.getWorld().destroyJoint(editorScreen.getMouseJoint());
            editorScreen.setMouseJoint(null);
        }
        return false;
    }
}

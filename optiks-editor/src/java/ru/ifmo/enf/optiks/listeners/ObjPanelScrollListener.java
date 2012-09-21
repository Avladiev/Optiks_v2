package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import ru.ifmo.enf.optiks.joint.EditorMouseJointDef;
import ru.ifmo.enf.optiks.panel.ObjPanelItem;
import ru.ifmo.enf.optiks.panel.ObjectsPanel;
import ru.ifmo.enf.optiks.phisycs.GameObjectFactory;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.object.ObjectType;
import ru.ifmo.enf.optiks.phisycs.object.container.SimpleObjectСontainer;
import ru.ifmo.enf.optiks.phisycs.object.state.State;
import ru.ifmo.enf.optiks.phisycs.object.state.StateFactory;
import ru.ifmo.enf.optiks.phisycs.object.state.StateFactoryPlay;
import ru.ifmo.enf.optiks.phisycs.utils.Calculate;
import ru.ifmo.enf.optiks.screen.EditorScreen;
import ru.ifmo.enf.optiks.util.OverlapTester;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 15.09.12
 */
public class ObjPanelScrollListener extends GestureDetector.GestureAdapter {

    private final EditorScreen editorScreen;
    private final World world;
    private final ObjectsPanel objectsPanel;
    private final Camera camera;
    private final GameObjectFactory factory;
    private final GameObject wall;
    @SuppressWarnings("FieldCanBeLocal")
    private Vector3 touchPoint;
    private GameObject currentObject;

    public ObjPanelScrollListener(final EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
        this.objectsPanel = editorScreen.getObjectsPanel();
        this.world = editorScreen.getWorld();
        this.camera = editorScreen.getCamera();
        factory = new GameObjectFactory(world);
        wall = editorScreen.getWall();
    }

    @Override
    public boolean pan(final int x, final int y, final int deltaX, final int deltaY) {
        if (editorScreen.getMouseJoint() != null) {
            return false;
        }
        touchPoint = new Vector3(x, y, 0);
        camera.unproject(touchPoint);
        if (OverlapTester.pointInRectangle(objectsPanel.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
            /* Panel scroll */
            if (Math.abs(deltaX) > 1.5 * Math.abs(deltaY)) {
                for (final ObjPanelItem item : objectsPanel.getItems()) {
                    item.setX(item.getX() + deltaX);
                }
                return true;
            }
            /* Game Object drag */
            else {
                ObjectType objectType = null;
                /* Check if we caught game object */
                for (final ObjPanelItem item : objectsPanel.getItems()) {
                    if (OverlapTester.pointInRectangle(item.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
                        objectType = item.getType();
                        break;
                    }
                }
                /* Create mouse joint for it */
                if (objectType != null) {
                    currentObject = factory.createGameObject(new SimpleObjectСontainer(new Vector2(Calculate.toPhysicsVector(x, y)), 0, objectType));
                    currentObject.setActive(true);
                    currentObject.getBody().setType(BodyDef.BodyType.DynamicBody);
                    currentObject.setFixtureProperties();

                    final MouseJointDef mouseJoint = new EditorMouseJointDef(wall, currentObject);
                    editorScreen.setMouseJoint((MouseJoint) world.createJoint(mouseJoint));
                    return true;
                }
            }
        }
        return false;
    }
}
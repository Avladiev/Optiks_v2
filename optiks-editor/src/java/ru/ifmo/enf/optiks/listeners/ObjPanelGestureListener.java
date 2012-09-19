package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import ru.ifmo.enf.optiks.panel.ObjPanelItem;
import ru.ifmo.enf.optiks.panel.ObjectsPanel;
import ru.ifmo.enf.optiks.util.OverlapTester;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 15.09.12
 */
public class ObjPanelGestureListener extends GestureDetector.GestureAdapter {

    private final ObjectsPanel objectsPanel;
    private final Camera camera;
    @SuppressWarnings("FieldCanBeLocal")
    private Vector3 touchPoint;

    public ObjPanelGestureListener(final ObjectsPanel objectsPanel, final Camera camera) {
        this.objectsPanel = objectsPanel;
        this.camera = camera;
    }

    @Override
    public boolean pan(final int x, final int y, final int deltaX, final int deltaY) {
        // TODO if x, y owned by ObjPanel
        touchPoint = new Vector3(x, y, 0);
        camera.unproject(touchPoint);
        if (OverlapTester.pointInRectangle(objectsPanel.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
            for (final ObjPanelItem item : objectsPanel.getItems()) {
                item.setX(item.getX() + deltaX);
            }
        }
        return false;
    }
}

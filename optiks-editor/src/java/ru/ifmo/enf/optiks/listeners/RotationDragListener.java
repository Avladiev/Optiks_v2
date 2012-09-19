package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 18.09.12
 */
public class RotationDragListener extends GestureDetector.GestureAdapter {

    private final World world;
    private MouseJoint mouseJoint;

    @Override
    public boolean pan(final int x, final int y, final int deltaX, final int deltaY) {
        return super.pan(x, y, deltaX, deltaY);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RotationDragListener(final World world) {
        this.world = world;
    }
}

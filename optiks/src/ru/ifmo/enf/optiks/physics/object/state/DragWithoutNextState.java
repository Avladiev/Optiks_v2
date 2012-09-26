package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.joint.RevoluteJointBehavior;
import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class DragWithoutNextState extends State {

    public DragWithoutNextState(final GameObject gameObject) {
        super(gameObject);
        if (gameObject.hasPrevious()) {
            if (gameObject.getPrevious().hasPrevious()) {
                previous = new DragWithoutNextState(gameObject.getPrevious());
            }
        }
    }

    @Override
    protected void setPreProperties() {
        final float angle = RevoluteJointBehavior.countJointAngle(gameObject, gameObject.getPrevious());
        gameObject.getJoint().setLimits(-angle, angle);
    }

    @Override
    protected void setPostProperties() {
        final float angle = gameObject.getJoint().getJointAngle();
        gameObject.getJoint().setLimits(angle, angle);
    }
}

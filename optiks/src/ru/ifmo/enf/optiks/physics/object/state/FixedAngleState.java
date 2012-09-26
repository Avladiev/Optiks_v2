package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
@Deprecated

public class FixedAngleState extends State {
    private float lowerAngle;
    private float upperAngle;

    public FixedAngleState(final GameObject gameObject) {
        super(gameObject);
        lowerAngle = gameObject.getJoint().getLowerLimit();
        upperAngle = gameObject.getJoint().getUpperLimit();
        if (gameObject.hasNext()) {
            next = new FixedAngleState(gameObject.getNext());
        }
    }

    @Override
    protected void setPreProperties() {
        float angle = gameObject.getJoint().getJointAngle();
        gameObject.getJoint().setLimits(angle, angle);
    }

    @Override
    protected void setPostProperties() {
        gameObject.getJoint().setLimits(lowerAngle, upperAngle);
    }
}

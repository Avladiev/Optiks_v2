package ru.ifmo.enf.optiks.phisycs.joint;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class RevoluteJointBehavior {
    public static JointDef createRevoluteJoint(final GameObject objectA, final GameObject objectB, final boolean collideConnected) {
        final RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(objectA.getBody(), objectB.getBody(), new Vector2());
        jointDef.localAnchorA.set(objectA.getAnchorA());
        jointDef.localAnchorB.set(objectB.getAnchorB());
        jointDef.collideConnected = collideConnected;

        jointDef.enableLimit = true;
//        final float angle = objectA.getJointAngle() + objectB.getJointAngle();

//        jointDef.lowerAngle = (float) Math.toRadians(-angle);
//        jointDef.upperAngle = (float) Math.toRadians(angle);

        jointDef.lowerAngle = (float) 0;
        jointDef.upperAngle = (float) 0;

        jointDef.enableMotor = true;
        jointDef.maxMotorTorque = 0.01f;
        return jointDef;
    }

    public static float countJointAngle(final GameObject objectA, final GameObject objectB) {
        final float angle = objectA.getJointAngle() + objectB.getJointAngle();
        return MathUtils.degreesToRadians * angle;
    }
}

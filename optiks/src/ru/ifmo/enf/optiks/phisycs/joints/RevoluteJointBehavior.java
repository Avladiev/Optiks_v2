package ru.ifmo.enf.optiks.phisycs.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import ru.ifmo.enf.optiks.object.GameObject;

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
        return jointDef;
    }
}

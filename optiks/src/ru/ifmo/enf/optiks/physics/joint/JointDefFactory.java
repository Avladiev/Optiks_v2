package ru.ifmo.enf.optiks.physics.joint;

import com.badlogic.gdx.physics.box2d.JointDef;
import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class JointDefFactory {
    public static JointDef createJointDef(final GameObject objectA, final GameObject objectB) {

        JointDef jointDef = null;
        // todo implement me !
        jointDef = RevoluteJointBehavior.createRevoluteJoint(objectA, objectB, false);
        return jointDef;
    }
}

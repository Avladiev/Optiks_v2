package ru.ifmo.enf.optiks.joint;

import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 21.09.12
 */
public class EditorMouseJointDef extends MouseJointDef {
    public EditorMouseJointDef(final GameObject border, final GameObject object) {
        super();
        this.bodyA = border.getBody();
        this.bodyB = object.getBody();
        this.dampingRatio = 7;
        this.frequencyHz = 10;
        this.maxForce = 100000f;
        this.collideConnected = true;
        this.target.set(object.getWorldCenter());
    }
}

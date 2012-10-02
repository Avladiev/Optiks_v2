package ru.ifmo.enf.optiks.physics.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public abstract class GameObject {

    private GameObject previous;
    private GameObject next;

    private GameObject attachZone;

    private Body body;

    /* revolute joint with next */
    private RevoluteJoint joint;

    /* anchors for Joints */
    private final Vector2 anchorA;
    private final Vector2 anchorB;

    /* sometimes matches with center of Body mass */
    private final Vector2 rotationCenter;

    /*need for revoluteJoint limit*/
    private final float jointAngle;

    /* can player move this game object*/
    protected boolean isMovable;

    private final float gravityScale;
    private final float sizeScale;

    public static float density;
    public static float friction;
    public static float restitution;

    private Vector2 originPosition;
    private float originAngle;

    private ObjectType objectType;

    protected GameObject(final Vector2 anchorA, final Vector2 anchorB, final Vector2 rotationCenter, final float gravityScale, final float sizeScale, final float jointAngle) {
        this.anchorA = anchorA;
        this.anchorB = anchorB;

        this.rotationCenter = rotationCenter;

        this.jointAngle = jointAngle;

        this.gravityScale = gravityScale;
        this.sizeScale = sizeScale;
    }

    protected GameObject(final Vector2 anchorA, final Vector2 anchorB, final float gravityScale, final float sizeScale, final float jointAngle) {
        this(anchorA, anchorB, new Vector2(0, 0), gravityScale, sizeScale, jointAngle);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(final Body body) {
        this.body = body;
        body.setUserData(this);
    }

    public Vector2 getAnchorA() {
        return anchorA;
    }

    public Vector2 getAnchorB() {
        return anchorB;
    }

    public void setFixtureProperties() {
        for (final Fixture fixture : body.getFixtureList()) {
            fixture.setDensity(density);
            fixture.setFriction(friction);
            fixture.setRestitution(restitution);
        }
        body.resetMassData();
    }

    public void setPrevious(final GameObject previous) {
        this.previous = previous;
    }

    public void setNext(final GameObject next) {
        this.next = next;
    }

    public GameObject getPrevious() {
        return previous;
    }

    public GameObject getNext() {
        return next;
    }

    public float getSizeScale() {
        return sizeScale;
    }

    /**
     * reaction on the bullet hit
     *
     * @param bullet
     * @param fixtureA
     */
    public abstract void bulletHitReaction(final Bullet bullet, final Fixture fixtureA);

    public void gravityOn() {
        getBody().setGravityScale(gravityScale);
        next.gravityOn();
    }

    public Vector2 getWorldCenter() {
        return body.getWorldCenter();
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(final boolean movable) {
        isMovable = movable;
    }

    public void stopBody() {
        body.setAngularVelocity(0);
        body.setLinearVelocity(0, 0);
        body.setLinearDamping(0);
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void setJoint(final Joint joint) {
        this.joint = (RevoluteJoint) joint;
    }

    public RevoluteJoint getJoint() {
        return joint;
    }

    /**
     * @return jointAngle of this object
     * maximum jointAngle of revoluteJoint is sum of joined bodies jointAngles
     */
    public float getJointAngle() {
        return jointAngle;
    }

    /* activate & deactivate body in physics world */
    public void setActive(final boolean isActive) {
        body.setActive(isActive);
        if (hasNext()) {
            getNext().setActive(isActive);
        }
    }

    public Vector2 getRotationCenter() {
        return rotationCenter;
    }

    public void setAttachZone(final GameObject attachZone) {
        this.attachZone = attachZone;
    }

    public GameObject getAttachZone() {
        return attachZone;
    }

    public boolean hasAttachZone() {
        return attachZone != null;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(final ObjectType objectType) {
        this.objectType = objectType;
    }

    public void setOrigins(final Vector2 originPosition, final float originAngle) {
        this.originPosition = originPosition;
        this.originAngle = originAngle;
    }

    public Vector2 getOriginPosition() {
        return originPosition;
    }

    public float getOriginAngle() {
        return originAngle;
    }
}


package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public abstract class GameObject {
    public static float density;
    public static float friction;
    public static float restitution;

    protected boolean isMovable;

    // anchors for Joints
    private final Vector2 anchorA;
    private final Vector2 anchorB;
    private final Vector2 rotationCenter;
    private final float gravityScale;
    private final float sizeScale;
    private boolean canTouch;

    private Body body;

    private GameObject previous;
    private GameObject next;

    private float jointAngle;

    private RevoluteJoint joint;

    protected GameObject(final Vector2 anchorA, final Vector2 anchorB, final Vector2 rotationCenter, final float gravityScale, final float sizeScale, final float jointAngle) {
        this.anchorA = anchorA;
        this.anchorB = anchorB;
        this.rotationCenter = rotationCenter;
        this.gravityScale = gravityScale;
        this.sizeScale = sizeScale;
        this.canTouch = true;
        this.jointAngle = jointAngle;
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

//            fixture.getShape().setRadius(0.1f);

            final Filter filter = new Filter();
            filter.categoryBits = 1;
            filter.groupIndex = 1;
//            fixture.setFilterData(filter);
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

    public boolean isCanTouch() {
        return canTouch;
    }

    public void setBodyType(final BodyDef.BodyType type) {
        body.setType(type);
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

    public float getJointAngle() {
        return jointAngle;
    }

    public void setActive(final boolean isActive) {
        body.setActive(isActive);
        if (hasNext()) {
            getNext().setActive(isActive);
        }
    }

    public Vector2 getRotationCenter() {
        return rotationCenter;
    }
}


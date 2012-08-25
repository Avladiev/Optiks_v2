package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public abstract class GameObject {
    public static float density;
    public static float friction;
    public static float restitution;

    // anchors for Joints
    private final Vector2 anchorA;
    private final Vector2 anchorB;
    private final float gravityScale;
    private final float sizeScale;
    private boolean canTouch;

    private Body body;

    private GameObject previous;
    private GameObject next;

    protected GameObject(final Vector2 anchorA, final Vector2 anchorB, final float gravityScale, final float sizeScale) {
        this.anchorA = anchorA;
        this.anchorB = anchorB;
        this.gravityScale = gravityScale;
        this.sizeScale = sizeScale;
        this.canTouch = true;
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
            Filter filter = new Filter();
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
     * @param bullet
     */
    public abstract void bulletHitReaction(final Bullet bullet);

    public void gravityOn() {
        getBody().setGravityScale(gravityScale);
        next.gravityOn();
    }

    public boolean isCanTouch() {
        return canTouch;
    }
}


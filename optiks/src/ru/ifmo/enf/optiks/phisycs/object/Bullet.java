package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import ru.ifmo.enf.optiks.phisycs.utils.Calculate;

import java.util.ArrayList;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Bullet extends GameObject{
    private final Body body;
    private final ArrayList<Vector2> collisionPoints;

    public Bullet(final Body body) {
        super(null, null, 0, 0, 0);
        this.body = body;
        body.setBullet(true);
        body.setActive(false);

        collisionPoints = new ArrayList<Vector2>();
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
    }

    public void stop() {
        body.setLinearVelocity(0, 0);
        body.setActive(false);

        addPoint(body.getWorldCenter());
    }

    public void shoot(final Vector2 position, final Vector2 vector) {
        collisionPoints.clear();
        addPoint(body.getWorldCenter());

        body.setTransform(position, 0);
        body.setActive(true);

        final float multiply = 10000 / (vector.x * vector.x + vector.y * vector.y);
        body.setLinearVelocity(vector.mul(multiply));
    }

    public void continueShoot() {
        addPoint(body.getWorldCenter());
    }

    public void addPoint(final Vector2 vector2) {
        collisionPoints.add(Calculate.toGraphicsVector(vector2));
    }
}

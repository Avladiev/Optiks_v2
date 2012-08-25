package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Bullet {
    private final Body body;

    public Bullet(final Body body) {
        this.body = body;
        body.setBullet(true);
        body.setActive(false);
    }

    public Body getBody() {
        return body;
    }

    public void stop() {
        body.setLinearVelocity(0, 0);
        body.setActive(false);
    }

    public void shoot(final Vector2 position, final Vector2 vector) {
        body.setTransform(position, 0);
        body.setActive(true);

        final float multiply = 10000 / (vector.x * vector.x + vector.y * vector.y);
        body.setLinearVelocity(vector.mul(multiply));
    }

    public void continueShoot() {

    }
}

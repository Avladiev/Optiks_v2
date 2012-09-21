package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Mirror extends GameObject {

    public Mirror() {
        super(new Vector2(), new Vector2(), 1, 20, 0);
        density = 1;
        friction = 0;
        restitution = 1;

        setMovable(true);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
        if (getBody().getFixtureList().get(4) == fixtureA) {
            bullet.continueShoot();
        } else {
            bullet.stop();
        }
    }
}

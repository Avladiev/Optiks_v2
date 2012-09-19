package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Rope extends GameObject {
    public Rope() {
        super(new Vector2(1, 1), new Vector2(1, 1), 0.7f, 16, 90);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
        getBody().getWorld().destroyBody(getBody());
        gravityOn();
        bullet.continueShoot();
    }
}

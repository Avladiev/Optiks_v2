package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Aim extends GameObject {

    public Aim() {
        super(new Vector2(0, 0), new Vector2(0, 0), 1, 15, 45);
        density = 1;
        friction = 1;
        restitution = 1;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
        bullet.stop();
    }
}
